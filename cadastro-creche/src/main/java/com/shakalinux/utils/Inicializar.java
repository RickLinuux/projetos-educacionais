package com.shakalinux.utils;

import java.util.Scanner;

import com.shakalinux.dao.CriancaDao;
import com.shakalinux.dao.ProfessorDao;
import com.shakalinux.dao.RelatorioDao;

public class Inicializar {
    public void inicializar() {
        Scanner read = new Scanner(System.in);
        ProfessorDao pd = new ProfessorDao();
        CriancaDao cd = new CriancaDao();
        RelatorioDao rd = new RelatorioDao();
    
        int opcao = 0;
        do {
            System.out.println("=============================");
            System.out.println("        SISTEMA CRECHE       ");
            System.out.println("=============================");
            System.out.println("1- Cadastrar Professor ");
            System.out.println("2- Cadastrar Criança ");
            System.out.println("3- Listar Crianca Por Turma");
            System.out.println("4- Sair do sistema");
            opcao = read.nextInt();
            read.nextLine();
    
            switch(opcao){
                case 1 -> {pd.cadastrarProfessor();}
                case 2 -> {cd.cadastrarCrianca();}
                case 3 -> {rd.listarCriancaPorTurma();}
                default -> System.out.println("Saindo...");

            }
        } while (opcao != 4);
    }
}
