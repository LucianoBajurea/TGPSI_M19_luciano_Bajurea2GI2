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
}
