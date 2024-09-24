package com.shakalinux.modelo;

import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Professor extends Pessoa {
    private String disciplina;
    private List<Turma> turmas;
    private String horario;

    public Professor(String nome, String cpf, Sexo sexo, Date dataNascimento, String disciplina, List<Turma> turmas, String horario) {
        super(nome, cpf, sexo, dataNascimento);
        this.disciplina = disciplina;
        this.turmas = turmas;
        this.horario = horario;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }


    public String getHorario(){
        return horario;
    }

    public void setHorario(String horario){
        this.horario = horario;
    }


}
