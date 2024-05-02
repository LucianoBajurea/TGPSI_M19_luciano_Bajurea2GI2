package com.example.tgpsi_m19_luciano_bajurea;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PrincipalController {
    public AnchorPane AnchorPaneMain;
    public Button btnAbout;
    public Button btnClient;
    public Button btnSeller;
    public Button btnProduct;
    public AnchorPane AnchorPaneProduct;
    public TableView tableViewProduct;
    public TableColumn tableColumnIdProduct;
    public TableColumn tableColumnNameProduct;
    public TableColumn tableColumnTypeProduct;
    public TableColumn tableColumnPriceProduct;
    public TextField productId;
    public TextField productName;
    public ComboBox productType;
    public TextField productPrice;
    public Button btnAdd;
    public Button btnRemove;
    public Button btnEdit;
    public AnchorPane AnchorPaneAbout;
    public AnchorPane AnchorPaneClient;
    public TableView tableViewClient;
    public TableColumn tableColumnIdClient;
    public TableColumn tableColumnNameClient;
    public TableColumn tableColumnAdressClient;
    public TableColumn tableColumnNumberTelClient;
    public TableColumn tableColumnEmailCliente;
    public TextField clientId;
    public TextField clientName;
    public TextField clientNumTel;
    public TextField clientEmail;
    public Button btnAddClient;
    public Button btnRemoveCliente;
    public Button btnEditClient;
    public TextField clientAdress;
    public AnchorPane AnchorPaneFornecedor;
    public TableView tableViewFornecedor;
    public TableColumn tableColumnIdForn;
    public TableColumn tableColumnNameForn;
    public TableColumn tableColumnAdressForn;
    public TableColumn tableColumnNumTelForn;
    public TableColumn tableColumnEmailForn;
    public TextField FornecedorSearch;
    public TextField fornecedorId;
    public TextField fornecedorName;
    public TextField fornecedorAdress;
    public TextField fornecedorNumTel;
    public TextField fornecedorEmail;
    public Button btnFornecedorAdd;
    public Button btnFornecedorRemove;
    public Button btnFornecedorEdit;

    public void buttonAbout(ActionEvent actionEvent) {
    }

    public void buttonOnClient(ActionEvent actionEvent) {
    }

    public void buttonFornecedor(ActionEvent actionEvent) {
    }

    public void buttonCloseApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void buttonOnProduct(ActionEvent actionEvent) {
    }

    public void verProdutos(MouseEvent event) {
    }

    public void buttonAdd(ActionEvent actionEvent) {
    }

    public void buttonRemove(ActionEvent actionEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(productId == 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem de Erro⚠\uFE0F");
            alert.setHeaderText(null);
            alert.setContentText("Selecione primeiro o Produto!");
            alert.showAndWait();
        }else{
            String sql = "DELECT FROM produto WHERE idProduto = "+productId;
        }
    }

    public void buttonEdit(ActionEvent actionEvent) {
    }

    public void verClientes(MouseEvent event) {
    }

    public void buttonAddClient(ActionEvent actionEvent) {
    }

    public void buttonRemoveClient(ActionEvent actionEvent) {
    }

    public void buttonEditClient(ActionEvent actionEvent) {
    }

    public void verVendedor(MouseEvent event) {
    }

    public void pesquisarFornecedor(KeyEvent keyEvent) {
    }

    public void buttonFornAdd(ActionEvent actionEvent) {
    }

    public void buttonFornRemove(ActionEvent actionEvent) {
    }

    public void buttonFornEdit(ActionEvent actionEvent) {
    }
}
