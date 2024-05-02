package com.example.tgpsi_m19_luciano_bajurea;
public class Cliente {

    //Atributos da classe
    private int idCliente;
    private String nome;
    private String nif;
    private String endereco;
    private String email;
    private String numTelemovel;

    //-----------------------------------------------------------
    //Método Contrutor Vázio
    public Cliente() {
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Cliente com todos os atributos
    public Cliente(int idCliente, String nome, String nif, String endereco, String email, String numTelemovel) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.nif = nif;
        this.endereco = endereco;
        this.email = email;
        this.numTelemovel = numTelemovel;
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Cliente com os atributos menos o ID
    public Cliente(String nome, String nif, String endereco, String email, String numTelemovel) {
        this.nome = nome;
        this.nif = nif;
        this.endereco = endereco;
        this.email = email;
        this.numTelemovel = numTelemovel;
    }

    //-----------------------------------------------------------
    //Método Getters e Setters dos Atributos - Para obter e alterar o Cliente
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTelemovel() {
        return numTelemovel;
    }

    public void setNumTelemovel(String numTelemovel) {
        this.numTelemovel = numTelemovel;
    }
}
