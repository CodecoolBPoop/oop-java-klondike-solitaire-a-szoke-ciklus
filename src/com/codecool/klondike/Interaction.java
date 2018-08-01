package com.codecool.klondike;

import com.sun.xml.internal.bind.v2.model.core.ID;
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

    private static Button newBtn(String textOnBtn, double posX, double posY) {
        Button btn = new Button();

        btn.setText(textOnBtn);
        btn.setTranslateX(posX);
        btn.setTranslateY(posY);
        return btn;
    }

    public static Button addNewGameBtn() {
        Button newGameButton = newBtn("New game", modalStage.getWidth() / 2 - 50, modalStage.getHeight() / 2 - 20);
        modalPane.getChildren().add(newGameButton);
        newGameButton.setOnAction(event -> {
            closeModal();
            Klondike.restart(Interaction.prStage);
        });
        setBtnHooverStyle(newGameButton, "orange", "darkorange");
        return newGameButton;

    }

    private static void setBtnHooverStyle(Button btn, String IDLE_NEW_GAME_STYLE, String HOOVERED_NEW_GAME_STYLE) {
        btn.setStyle("-fx-background-color: " + IDLE_NEW_GAME_STYLE);
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + IDLE_NEW_GAME_STYLE ));
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: " + HOOVERED_NEW_GAME_STYLE));
    }

    public static Button addQuitGameBtn() {
        Button quitGameButton = newBtn("Quit game", -modalStage.getWidth() / 2 + 50, modalStage.getHeight() / 2 - 20);
        modalPane.getChildren().add(quitGameButton);
        quitGameButton.setOnAction(event -> System.exit(0));
        return quitGameButton;
    }

    public static void showModal(String title, String text, int modalWidth, int modalHeight) {
        modalLabel = new Label(text);
        modalLabel.setTranslateY(-modalHeight/2 + 20);
        modalPane = new StackPane();
        modalPane.getChildren().add(modalLabel);
        modalScene = new Scene(modalPane, modalWidth, modalHeight);
        modalStage = new Stage();
        modalStage.setTitle(title);
        modalStage.setScene(modalScene);
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(prStage);

        modalStage.show();
    }

    public static void closeModal() {
        modalStage.close();
    }
}
