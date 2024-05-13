package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Settings {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Settings.primaryStage = primaryStage;
    }

    //------------------------------------------------------------------------
    //PARTE DO PRODUTO

    // Atributo privado da Classe Settings - ProductEdit
    private static Produto ProductEdit;

    // A Método Getter e setter do ProductEdit
    public static Produto getProductEdit() {
        return ProductEdit;
    }

    public static void setProductEdit(Produto ProductEdit) {
        Settings.ProductEdit = ProductEdit;
    }

    // LISTA DO PRODUTO
    public static ObservableList<Produto> listProduct = FXCollections.observableArrayList();

    // Método Getter e Setter da Lista do Produto
    public static ObservableList<Produto> getListProduct() {
        return listProduct;
    }

    public static void setListProduct(ObservableList<Produto> listProduct) {
        Settings.listProduct = listProduct;
    }


    //------------------------------------------------------------------------
    //PARTE DO FORNECEDOR
    private static Fornecedor FornecedorEdit;

    public static Fornecedor getFornecedorEdit() {
        return FornecedorEdit;
    }

    public static void setFornecedorEdit(Fornecedor fornecedorEdit) {
        FornecedorEdit = fornecedorEdit;
    }

    public static ObservableList<Fornecedor> listForn = FXCollections.observableArrayList();

    // Método Getter e Setter da Lista do Produto
    public static ObservableList<Fornecedor> getListForn() {
        return listForn;
    }

    public static void setListForn(ObservableList<Fornecedor> listForn) {
        Settings.listForn = listForn;
    }

    //------------------------------------------------------------------------
    //PARTE DO ClIENTE
    // Atributo privado da classe Cliente
    private static Cliente clientEdit;

    // Método Getter e Setter do Cliente
    public static Cliente getClientEdit() {
        return clientEdit;
    }

    public static void setClientEdit(Cliente clientEdit) {
        Settings.clientEdit = clientEdit;
    }

    // LISTA DO CLIENTE
    private static ObservableList<Cliente> listClient = FXCollections.observableArrayList();

    // Método Getter e Setter da lista Cliente
    public static ObservableList<Cliente> getListClient() {
        return listClient;
    }
    public static void setListClient(ObservableList<Cliente> listClient) {
        Settings.listClient = listClient;
    }
}

