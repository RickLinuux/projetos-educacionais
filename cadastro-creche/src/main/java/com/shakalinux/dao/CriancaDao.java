package com.shakalinux.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.shakalinux.modelo.Crianca;

public class CriancaDao {
    private Scanner read = new Scanner(System.in);
    Crianca crianca = new Crianca();

    public void cadastrarCrianca() {
        System.out.print("Nome completo da criança: ");
        String nome = read.nextLine();
        System.out.print("Insira CPF do responsável (apenas números): ");
        String cpf = read.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = read.nextLine();
        System.out.print("Endereço: ");
        String endereco = read.nextLine();
        System.out.print("Data de Nascimento da Criança (YYYY-MM-DD): ");
        String dataNascimento = read.nextLine();
        
        
        if (!validarCampos(nome, cpf, sexo, endereco, dataNascimento)) {
            System.out.println("Verifique os campos, não podem ser vazios ou inválidos.");
        }
        
        int idade = crianca.calcularIdade(dataNascimento);
        String turma = escolherTurma();
        String horario = escolherHorario();
        
        
        try (Connection conn = BaseConnection.getConnection()) {
            String queryPessoa = "INSERT INTO Pessoa (nome, cpf, sexo, endereco, data_nascimento)" +
                                 "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstPessoa = conn.prepareStatement(queryPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            pstPessoa.setString(1, nome);
            pstPessoa.setString(2, cpf);
            pstPessoa.setString(3, sexo);
            pstPessoa.setString(4, endereco);
            pstPessoa.setString(5, dataNascimento);
            pstPessoa.executeUpdate();

            int pessoaId = 0;
            var rs = pstPessoa.getGeneratedKeys();
            if (rs.next()) {
                pessoaId = rs.getInt(1);
            }

            String queryCrianca = "INSERT INTO Crianca(id, turma, horario) VALUES (?, ?, ?)";
            PreparedStatement pstCrianca = conn.prepareStatement(queryCrianca);
            pstCrianca.setInt(1, pessoaId);
            pstCrianca.setString(2, turma);
            pstCrianca.setString(3, horario);
            pstCrianca.executeUpdate();

            System.out.println( "A criança " + nome + " foi matriculada na turma " + turma + " no turno da " + horario);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "Erro ao cadastrar Criança: " + e.getMessage());
        }
    }

    private boolean validarCampos(String nome, String cpf, String sexo, 
                                  String endereco, String dataNascimento) {
        if (nome.isEmpty() || cpf.isEmpty() || sexo.isEmpty() || endereco.isEmpty() 
            || dataNascimento.isEmpty()) {
            System.out.println("Erro: Todos os campos devem ser preenchidos.");
            return false;
        }

        if (cpf.length() != 11 || !cpf.matches("\\d+")) {
            System.out.println("Erro: CPF deve conter 11 dígitos numéricos.");
            return false;
        }

        if (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
            System.out.println("Erro: Sexo deve ser 'M' ou 'F'.");
            return false;
        }

        try {
            java.sql.Date.valueOf(dataNascimento);
        } catch (Exception e) {
            System.out.println("Erro: Data de nascimento deve estar no formato 'YYYY-MM-DD'.");
            return false;
        }

        return true;
    }

    private String escolherTurma() {
        System.out.println("Informe a turma que deseja cadastrar:");
        System.out.println("1. Pré 1 | Idade 2-3 anos");
        System.out.println("2. Pré 2 | Idade 3-4 anos");
        System.out.println("3. Jardim 1 | Idade 4-5 anos");
        System.out.println("4. Jardim 2 | Idade 5-6 anos");
        

        int opcao = read.nextInt();
        read.nextLine(); 
        return obterNomeTurma(opcao);
    }

    private String obterNomeTurma(int opcao) {
        return switch (opcao) {
            case 1 -> "Pré 1";
            case 2 -> "Pré 2";
            case 3 -> "Jardim 1";
            case 4 -> "Jardim 2";
            default -> {
                System.out.println("Opção inválida! Turma padrão 'Pré 1' selecionada.");
                yield "Pré 1";
            }
        };
    }

    private String escolherHorario() {
        System.out.println("Escolha o horário desejado:");
        System.out.println("1. Manhã");
        System.out.println("2. Tarde");
        System.out.println("3. Integral");

        int opcao = read.nextInt();
        read.nextLine(); 
        return switch (opcao) {
            case 1 -> "Manhã";
            case 2 -> "Tarde";
            case 3 -> "Integral";
            default -> {
                System.out.println("Opção inválida! Horário padrão 'Manhã' selecionado.");
                yield "Manhã";
            }
        };
    }
}
