package com.example.tgpsi_m19_luciano_bajurea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Aquisição do controlo de uma Scene baseada em XML (FXML) e associação à primaryStage
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        //Definições da Stage
        primaryStage.setTitle("Agenda de Contactos");
        //Settings.setPrimaryStage(primaryStage);
        //Abertura da stage
        primaryStage.show();

    }
}
