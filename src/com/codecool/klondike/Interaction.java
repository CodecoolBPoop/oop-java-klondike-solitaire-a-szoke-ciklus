package com.codecool.klondike;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.*;

import java.util.List;

public class Interaction {
    public static Label modalLabel;
    public static StackPane modalPane;
    public static Scene modalScene;
    public static Stage modalStage;

    public static Stage prStage;
    public static Game game;


    public static MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("Game");

        MenuItem newGame = new MenuItem("New");
        newGame.setOnAction(event -> Klondike.restart(Interaction.prStage));
        gameMenu.getItems().add(newGame);

        gameMenu.getItems().add(new SeparatorMenuItem());

        gameMenu.getItems().add(new MenuItem("Undo"));
        gameMenu.getItems().add(new MenuItem("Redo"));

        gameMenu.getItems().add(new SeparatorMenuItem());

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> Platform.exit());
        gameMenu.getItems().add(exit);

        Menu optionsMenu = new Menu("Options");
        MenuItem deck = new MenuItem("Deck...");
        deck.setOnAction(event -> {
            showModal("Deck", "Choose a deck:", 640, 260);
            deckPicker();
        });

        optionsMenu.getItems().add(deck);

        menuBar.getMenus().addAll(gameMenu, optionsMenu);

        return menuBar;
    }

    private static Button newBtn(String textOnBtn, double posX, double posY) {
        Button btn = new Button();

        btn.setText(textOnBtn);
        btn.setTranslateX(posX);
        btn.setTranslateY(posY);
        return btn;
    }

    public static Button addNewGameBtn() {
        Button newGameButton = newBtn("New game", -modalStage.getWidth() / 2 + 100, modalStage.getHeight() / 2 - 35);
        modalPane.getChildren().add(newGameButton);
        newGameButton.setOnAction(event -> {
            closeModal();
            Klondike.restart(Interaction.prStage);
        });
        setBtnHoverStyle(newGameButton, "orange", "darkorange");
        return newGameButton;
    }

    private static void setBtnHoverStyle(Button btn, String originalColor, String hoverColor) {
        btn.setStyle("-fx-background-color: " + originalColor);
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + originalColor));
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: " + hoverColor));
    }

    public static Button addQuitGameBtn() {
        Button quitGameButton = newBtn("Quit game", modalStage.getWidth() / 2 - 100, modalStage.getHeight() / 2 - 35);
        modalPane.getChildren().add(quitGameButton);
        quitGameButton.setOnAction(event -> Platform.exit());
        setBtnHoverStyle(quitGameButton, "gainsboro", "silver");
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

    private static void deckPicker() {
        String[] cardBacks = { "szoke_ciklon", "card_back", "robin_hood", "hearthstone", "magic_the_gathering" };
        for (int i = 0; i < cardBacks.length; i++) {
            ImageView backImage = new ImageView(new Image("card_backs/" + cardBacks[i] + ".png", 75, 108, true, true));
            Button deckPick = new Button("", backImage);
            int j = i;
            deckPick.setOnAction(event -> {
                Card.cardBackImage = new Image("card_backs/" + cardBacks[j] + ".png");
                List<Card> deck = game.getDeck();
                for (Card card : deck) {
                    card.refreshCardBack();
                }
            });
            deckPick.setTranslateY(-20);
            deckPick.setTranslateX(-240 + i * (44 + 75));
            modalPane.getChildren().add(deckPick);
        }
        Button okayBtn = newBtn("Okay", 0, 80);
        okayBtn.setOnAction(event -> closeModal());
        setBtnHoverStyle(okayBtn, "orange", "darkorange");
        modalPane.getChildren().add(okayBtn);
    }
}
