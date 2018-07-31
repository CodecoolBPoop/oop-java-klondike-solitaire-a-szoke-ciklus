package com.codecool.klondike;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Interaction {
    public Button newBtn(String textOnBtn, int posX, int posY) {
        Button btn = new Button();

        btn.setText(textOnBtn);
        btn.setLayoutX(posX);
        btn.setLayoutY(posY);
        return btn;
    }

    public void showModal(String title, String text) {
        Label modalLabel = new Label(text);
        StackPane modal = new StackPane();
        modal.getChildren().add(modalLabel);
        Scene modalScene = new Scene(modal, 230, 100);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(modalScene);
        stage.show();
    }
}
