package com.codecool.klondike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Klondike extends Application {

    private static final double WINDOW_WIDTH = 1280;
    private static final double WINDOW_HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       startGame(primaryStage);
    }

    private static void startGame(Stage primaryStage) {
        Card.loadCardImages();
        Game game = new Game();
        game.setTableBackground(new Image("/table/green.png"));
        MenuBar menuBar = Interaction.createMenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        game.getChildren().add(menuBar);
        Interaction.prStage = primaryStage;
        Interaction.game = game;
        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.getIcons().add(new Image("/icon.png"));
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }

    static void restart(Stage primaryStage) {
        primaryStage.close();
        startGame(primaryStage);
    }

}
