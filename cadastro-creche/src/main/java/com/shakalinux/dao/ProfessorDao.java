package com.shakalinux.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProfessorDao {

    private Scanner read = new Scanner(System.in);

    public void cadastrarProfessor() {
        System.out.print("Insira seu Nome: ");
        String nome = read.nextLine();
        System.out.print("Insira seu CPF: : ");
        String cpf = read.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = read.nextLine();
        System.out.print("Endereço: ");
        String endereco = read.nextLine();
        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimento = read.nextLine();
        System.out.print("Disciplina: Educação infantil, Artes, Música: ");
        String disciplina = read.nextLine();

        String horario = escolherHorario();
        String turma = escolherTurma();
        

        if(!validarDisciplina(disciplina)){
            System.out.println("Disciplina inválida. As opções válidas são: Educação infantil, Artes, Música.");
        }

    
        if(validarCampos(nome, cpf, sexo, endereco, dataNascimento, disciplina)){
            try (Connection conn = BaseConnection.getConnection()){
                String sqlPessoa = "INSERT INTO Pessoa (nome, cpf, sexo, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
                stmtPessoa.setString(1, nome);
                stmtPessoa.setString(2, cpf);
                stmtPessoa.setString(3, sexo);
                stmtPessoa.setString(4, endereco);
                stmtPessoa.setDate(5, java.sql.Date.valueOf(dataNascimento));
                stmtPessoa.executeUpdate();
                

                
                int pessoaId = 0;
                var rs = stmtPessoa.getGeneratedKeys();
                if (rs.next()) {
                    pessoaId = rs.getInt(1);
                }

                String sqlProfessor = "INSERT INTO Professor (id, disciplina, horario) VALUES (?, ?, ?)";
                PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor);
                stmtProfessor.setInt(1, pessoaId);
                stmtProfessor.setString(2, disciplina);
                stmtProfessor.setString(3, horario);
                stmtProfessor.executeUpdate();
                professorTurmaCadastrar(pessoaId, turma, conn);

                System.out.println("Professor " + nome +  " foi cadastrado na turma " + turma + " no turno " + horario);
                
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao cadastrar professor: " + e.getMessage());
            }
        }else{
            System.out.println( "Verifique os campos, não podem ser vazios.");
        }
    }
    


    private void professorTurmaCadastrar(int idProfessor, String turma, Connection conn) throws SQLException {
        String sqlTurma = "SELECT id FROM Turma WHERE nome = ?";
        try (PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma)) {
            stmtTurma.setString(1, turma);
            var rs = stmtTurma.executeQuery();
            if (rs.next()) {
                int idTurma = rs.getInt("id");
                String sqlProfessorTurma = "INSERT INTO Professor_Turma (id_professor, id_turma) VALUES (?, ?)";
                try (PreparedStatement stmtAssoc = conn.prepareStatement(sqlProfessorTurma)) {
                    stmtAssoc.setInt(1, idProfessor);
                    stmtAssoc.setInt(2, idTurma);
                    stmtAssoc.executeUpdate();
                }
            } else {
                System.out.println("Turma não encontrada: " + turma);
            }
        }
    }










    private boolean validarDisciplina(String disciplina){
        return disciplina.equalsIgnoreCase("Educação infantil") ||
        disciplina.equalsIgnoreCase("Artes") ||
        disciplina.equalsIgnoreCase("Música");
    }
    

    private boolean validarCampos(String nome, String cpf, String sexo, 
    String endereco, String dataNascimento, String disciplina) {
        
        if (nome.isEmpty() || cpf.isEmpty() || sexo.isEmpty() || endereco.isEmpty() 
        || dataNascimento.isEmpty() || disciplina.isEmpty()) {
            System.out.println("Erro: Todos os campos devem ser preenchidos.");
            return false;
        }

        if (cpf.length() != 11 ) {
            System.out.println("Erro: CPF deve conter 11 dígitos.");
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
        System.out.println("Informe a turma que deseja Lecionar:");
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
            default -> "Opção inválida! Turma padrão 'Pré 1' selecionada.";
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
