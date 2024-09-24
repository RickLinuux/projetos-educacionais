package com.br.shakalinux.mariasaaplicacao.dao;

import com.br.shakalinux.mariasaaplicacao.model.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDao {

    public void cadastrarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement pstPessoa = null;
        PreparedStatement pstCliente = null;
        try {
            conn = BaseConexao.getConexao();
            conn.setAutoCommit(false);

            pstPessoa = conn.prepareStatement("INSERT INTO pessoa (nome, cpf, telefone) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstPessoa.setString(1, cliente.getNome());
            pstPessoa.setString(2, cliente.getCpf());
            pstPessoa.setString(3, cliente.getTelefone());
            pstPessoa.executeUpdate();

            ResultSet rs = pstPessoa.getGeneratedKeys();
            Long idPessoa = null;
            if (rs.next()) {
                idPessoa = rs.getLong(1);
            }

            pstCliente = conn.prepareStatement("INSERT INTO clientes (idPessoa, email) VALUES (?, ?)");
            pstCliente.setLong(1, idPessoa);
            pstCliente.setString(2, cliente.getEmail());
            pstCliente.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstCliente != null) {
                    pstCliente.close();
                }
                if (pstPessoa != null) {
                    pstPessoa.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cliente> listarClientes() {
        Connection conn = null;
        PreparedStatement pstPessoa = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = BaseConexao.getConexao();
            pstPessoa = conn.prepareStatement("SELECT " +
                    "    c.idCliente, " +
                    "    c.email, " +
                    "    p.nome, " +
                    "    p.cpf, " +
                    "    p.telefone " +
                    "FROM " +
                    "    clientes c " +
                    "JOIN " +
                    "    pessoa p ON c.idPessoa = p.idPessoa;");

            rs = pstPessoa.executeQuery();

            while (rs.next()) {
                Long idCliente = rs.getLong("idCliente");
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                Long idPessoa = null;
                Cliente cliente = new Cliente(idPessoa, nome, cpf, telefone, idCliente, email);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) rs.close();
                if (pstPessoa != null) pstPessoa.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }


    





}


