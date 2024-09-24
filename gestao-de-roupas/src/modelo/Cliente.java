package modelo;

import utils.Sexo;

public class Cliente {
    private long id;
    private String nome;
    private String email;
    private Sexo sexo;

    public Cliente(){

    }

    public Cliente(long id, String nome, String email, Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
    }

    public long getId(int id2) {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }




}
