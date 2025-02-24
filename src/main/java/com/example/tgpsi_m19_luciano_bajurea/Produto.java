package com.example.tgpsi_m19_luciano_bajurea;

public class Produto {
    // Atributos privados da classe
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;
    private String categoriaProduto;

    //-----------------------------------------------------------
    //Método Contrutor Vázio
    public Produto() {
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Produto com todos os atributos
    public Produto(int idProduto, String nomeProduto, double precoProduto, String categoriaProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.categoriaProduto = categoriaProduto;
    }

    //-----------------------------------------------------------
    //Método Contrutor da Classe Produto com os atributos menos o ID
    public Produto(String nomeProduto, double precoProduto, String categoriaProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.categoriaProduto = categoriaProduto;
    }


    //-----------------------------------------------------------
    //Método Getters e Setters dos Atributos - Para obter e alterar o produto
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }
}
