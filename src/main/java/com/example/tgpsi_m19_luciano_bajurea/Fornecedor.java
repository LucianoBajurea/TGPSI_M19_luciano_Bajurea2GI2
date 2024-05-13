package com.example.tgpsi_m19_luciano_bajurea;

public class Fornecedor extends Cliente {

    // Atributos privados da classe
    private int idFornecedor;

    //-----------------------------------------------------------
    //Método Contrutor Vázio
    public Fornecedor() {
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Cliente com todos os atributos
    public Fornecedor(int idFornecedor, String nome, String nif, String morada, String email, String numTelemovel) {
        super(nome, nif, morada, email, numTelemovel);
        this.idFornecedor = idFornecedor;

    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

}