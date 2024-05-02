package com.example.tgpsi_m19_luciano_bajurea;

public class Categoria {

    // Atributos privados da classe
    private int idCategoria;
    private String nomeCategoria;
    private String DescricaoCategoria;

    //-----------------------------------------------------------
    //Método Contrutor Vázio
    public Categoria() {
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Categoria com todos os atributos
    public Categoria(int idCategoria, String nomeCategoria, String descricaoCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        DescricaoCategoria = descricaoCategoria;
    }

    public Categoria(String nomeCategoria, String descricaoCategoria) {
        this.nomeCategoria = nomeCategoria;
        DescricaoCategoria = descricaoCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return DescricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        DescricaoCategoria = descricaoCategoria;
    }
}
