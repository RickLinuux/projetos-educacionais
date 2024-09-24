package com.shakalinux.modelo;

import java.sql.Date;
import java.util.Calendar;

public class Crianca extends Pessoa {
    
    private String turma;
    private String horario;

    public Crianca(){

    }

    public Crianca(String nome, String cpf, Sexo sexo, Date dataNascimento, String turma, String horario){
        super(nome, cpf, sexo, dataNascimento);
        this.turma = turma;
        this.horario = horario;
    }


    public String getTurma() {
        return this.turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    // Metodo não considera mes e dia, apenas ano
    public int calcularIdade(String nascimento){
        Calendar calendario =  Calendar.getInstance();
        int ano = Integer.parseInt(nascimento.substring(0, 4));
        int idade  = calendario.get(Calendar.YEAR) - ano;
        return idade;
    }




 
}
