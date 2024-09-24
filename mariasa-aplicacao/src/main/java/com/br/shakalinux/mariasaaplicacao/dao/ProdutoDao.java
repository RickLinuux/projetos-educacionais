package com.br.shakalinux.mariasaaplicacao.dao;

import com.br.shakalinux.mariasaaplicacao.model.Produto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoDao {


    public void cadastrarProduto(Produto produto) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = BaseConexao.getConexao();
            String sql = "INSERT INTO produtos (nome, preco, categoria, descricao) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getPreco());
            pst.setString(3, produto.getCategoria());
            pst.setString(4, produto.getDescricao());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = BaseConexao.getConexao();
            String sql = "SELECT idProduto, nome, preco, categoria, descricao FROM produtos";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Long idProduto = rs.getLong("idProduto");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");

                Produto produto = new Produto(idProduto, nome, preco, categoria, descricao);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return produtos;
    }
}
