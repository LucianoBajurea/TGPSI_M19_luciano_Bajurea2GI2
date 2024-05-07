package com.example.tgpsi_m19_luciano_bajurea;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class DetalheFornecedorController {
    public TextField detailNameProduct;
    public TextField detailCategoryProduct;
    public TextField detailPriceProduct;
    public TextField detailDesProduct;
    public Button btnReturn;
    public TextField detailNameFornecedor;
    public TextField detailMoradaFornecedor;
    public TextField detailNumTelFornecedor;
    public TextField detailNifFornecedor;
    public TextField detailEmailFornecedor;

    public void buttonCloseApp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair da aplicação");
        alert.setHeaderText("Deseja mesmo sair da apliação?");
        // Adiciona botões personalizados em português
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(botaoSim, botaoNao);

        alert.showAndWait().ifPresent(response -> {
            if (response == botaoSim) {
                Settings.getPrimaryStage().close();
            }
        });
    }

    public void buttonReturn(ActionEvent actionEvent) {
    }
}
