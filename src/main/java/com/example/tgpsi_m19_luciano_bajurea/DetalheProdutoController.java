package com.example.tgpsi_m19_luciano_bajurea;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DetalheProdutoController {
    public TextField detailNameProduct;
    public TextField detailCategoryProduct;
    public TextField detailPriceProduct;
    public TextField detailDesProduct;
    public Button btnReturn;
    public AnchorPane anchorPaneDetailProduct;

    public void buttonCloseApp(ActionEvent actionEvent) {
    }

    public void buttonReturn(ActionEvent actionEvent) {
        System.exit(0);
    }
}
