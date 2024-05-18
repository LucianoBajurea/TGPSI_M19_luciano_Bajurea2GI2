package com.example.tgpsi_m19_luciano_bajurea;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DetalheProdutoController {
    public TextField detailNameProduct;
    public TextField detailCategoryProduct;
    public TextField detailPriceProduct;
    public TextField detailDesProduct;
    public Button btnReturn;
    public AnchorPane anchorPaneDetailProduct;

    public void buttonCloseApp(ActionEvent actionEvent) throws Exception {

        Parent scene = FXMLLoader.load(getClass().getResource("Principal.fxml"));

        Stage seeDetailForn = new Stage();
        seeDetailForn.setTitle("Fornecedor - Detalhe do Fornecedor");

        // Associação da Scene à Stage
        seeDetailForn.setScene(new Scene(scene));

        // Abertura da janela seeDetailProduct em modo MODAL, em relação à primaryStage
        seeDetailForn.initOwner(Settings.getPrimaryStage());
        seeDetailForn.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        seeDetailForn.show();
}

    public void buttonReturn(ActionEvent actionEvent) {
        System.exit(0);
    }
}
