package com.shakalinux.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDao {

    public void listarCriancaPorTurma() {
    try (Connection conn = BaseConnection.getConnection()) {
        String listarCriancaTurma = 
            "SELECT Turma.nome AS Turma, Crianca.horario AS Horario, " + 
            "Pessoa.nome AS Crianca " + 
            "FROM Crianca " +
            "JOIN Pessoa ON Crianca.id = Pessoa.id " +
            "JOIN Turma ON Crianca.turma = Turma.nome " +
            "ORDER BY Turma.nome, Pessoa.nome";

        PreparedStatement stmt = conn.prepareStatement(listarCriancaTurma);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String turma = rs.getString("Turma");
            String horario = rs.getString("Horario");
            String crianca = rs.getString("Crianca");
            System.out.println();
            System.out.println("Turma: " + turma + ", Horário: " + horario + ", Criança: " + crianca);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    

    
}
