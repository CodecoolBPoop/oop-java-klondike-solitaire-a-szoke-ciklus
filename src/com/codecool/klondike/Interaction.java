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
import javafx.stage.*;

public class Interaction {
    public static Label modalLabel;
    public static StackPane modalPane;
    public static Scene modalScene;
    public static Stage modalStage;


    public static Stage prStage;

    public Button newBtn(String textOnBtn, int posX, int posY) {
        Button btn = new Button();

        btn.setText(textOnBtn);
        btn.setTranslateX(posX);
        btn.setTranslateY(posY);
        return btn;
    }

    public static void showModal(String title, String text, int modalWidth, int modalHeight) {
        modalLabel = new Label(text);
        modalPane = new StackPane();
        modalPane.getChildren().add(modalLabel);
        modalScene = new Scene(modalPane, modalWidth, modalHeight);
        modalStage = new Stage();
        modalStage.setTitle(title);
        modalStage.setScene(modalScene);

        Interaction inter = new Interaction();

        Button closeBtn = inter.newBtn("Close", modalHeight / 2, modalHeight / 2 - 20);
        modalPane.getChildren().add(closeBtn);

        //AOT + inactivate owner
        closeBtn.setOnAction(event -> {
            modalStage.close();
        });
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(prStage);

        modalStage.show();
    }
}
