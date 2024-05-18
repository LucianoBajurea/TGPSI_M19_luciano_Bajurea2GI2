package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetalheProdutoController implements Initializable {

    @FXML
    private TableView tableViewCategoria;
    @FXML
    private TableColumn tableColumnIdCategoria;
    @FXML
    private TableColumn tableColumnNomeCategoria;
    @FXML
    private TableColumn tableColumnDescCategoria;
    @FXML
    private TextField categoriaSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductListar();
    }

    public void ProductListar() {
        // Configura as colunas da tabela para associar os atributos do objeto Produto
        tableColumnIdCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("idCategoria"));
        tableColumnNomeCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nomeCategoria"));
        tableColumnDescCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("descricaoCategoria"));

        Settings.getlistCategory().clear();
        // Associação da Obse0rvableList à TableView. A partir daqui, tudo se faz na ObservableList.
        tableViewCategoria.setItems(CategoriaDAO.listCategoria());
    }
}