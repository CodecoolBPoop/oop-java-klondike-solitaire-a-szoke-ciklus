package com.codecool.klondike;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.*;

public class Interaction {
    public static Label modalLabel;
    public static StackPane modalPane;
    public static Scene modalScene;
    public static Stage modalStage;


    public static Stage prStage;

    public static Button newBtn(String textOnBtn, int posX, int posY) {
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

        Button closeBtn = newBtn("Close", modalHeight / 2, modalHeight / 2 - 20);
        modalPane.getChildren().add(closeBtn);

        closeBtn.setOnAction(event -> System.exit(0));
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(prStage);

        modalStage.show();
    }

    public static void closeModal() {
        modalStage.close();
    }
}
