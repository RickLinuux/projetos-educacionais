package com.br.shakalinux.mariasaaplicacao.dao;

import com.br.shakalinux.mariasaaplicacao.model.Funcionario;
import com.br.shakalinux.mariasaaplicacao.model.Area;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioDao {

    public void cadastrarFuncionario(Funcionario funcionario) {
        Connection conn = null;
        PreparedStatement pstPessoa = null;
        PreparedStatement pstFuncionario = null;

        try {
            conn = BaseConexao.getConexao();
            conn.setAutoCommit(false);

           
            pstPessoa = conn.prepareStatement("INSERT INTO pessoa (nome, cpf, telefone) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstPessoa.setString(1, funcionario.getNome());
            pstPessoa.setString(2, funcionario.getCpf());
            pstPessoa.setString(3, funcionario.getTelefone());
            pstPessoa.executeUpdate();

            Long idPessoa = null;
            var rs = pstPessoa.getGeneratedKeys();
            if (rs.next()) {
                idPessoa = rs.getLong(1);
            }
            pstFuncionario = conn.prepareStatement("INSERT INTO funcionarios (idPessoa, idArea, salario, matricula) VALUES (?, ?, ?, ?)");
            pstFuncionario.setLong(1, idPessoa);

            Area area = funcionario.getArea();
            if (area != null && area.getIdArea() != null) {
                pstFuncionario.setLong(2, area.getIdArea());
            } else {
                throw new IllegalArgumentException("A área do funcionário não pode ser nula.");
            }

            pstFuncionario.setDouble(3, funcionario.getSalario());
            pstFuncionario.setString(4, funcionario.getMatricula());
            pstFuncionario.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstPessoa != null) pstPessoa.close();
                if (pstFuncionario != null) pstFuncionario.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Funcionario> listarFuncionarios() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conn = BaseConexao.getConexao();
            pst = conn.prepareStatement("SELECT f.idFuncionario, f.matricula, f.salario, p.nome, p.cpf, p.telefone, a.nome AS area " +
                    "FROM funcionarios f " +
                    "JOIN pessoa p ON f.idPessoa = p.idPessoa " +
                    "JOIN areas a ON f.idArea = a.idArea");
            rs = pst.executeQuery();


            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("idFuncionario"));
                funcionario.setMatricula(rs.getString("matricula"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));

                Area area = new Area();
                area.setNome(rs.getString("area"));
                funcionario.setArea(area);

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                if (pst != null) pst.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcionarios;
    }
}
