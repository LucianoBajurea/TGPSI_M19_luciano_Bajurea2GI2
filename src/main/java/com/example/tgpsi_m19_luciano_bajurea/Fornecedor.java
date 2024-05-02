package com.example.tgpsi_m19_luciano_bajurea;

public class Fornecedor {

    // Atributos privados da classe
    private int idFornecedor;
    private String nome;
    private String nif;
    private String endereco;
    private String email;
    private String numTelemovel;

    //-----------------------------------------------------------
    //Método Contrutor Vázio
    public Fornecedor() {
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Cliente com todos os atributos
    public Fornecedor(int idFornecedor, String nome, String nif, String endereco, String email, String numTelemovel) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.nif = nif;
        this.endereco = endereco;
        this.email = email;
        this.numTelemovel = numTelemovel;
    }

    public Fornecedor(String nome, String nif, String endereco, String email, String numTelemovel) {
        this.nome = nome;
        this.nif = nif;
        this.endereco = endereco;
        this.email = email;
        this.numTelemovel = numTelemovel;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
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
