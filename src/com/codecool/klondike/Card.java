package com.codecool.klondike;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

public class Card extends ImageView {

    private int suit;
    private int rank;
    private String color;
    private boolean faceDown;

    private Image backFace;
    private Image frontFace;
    private Pile containingPile;
    private DropShadow dropShadow;

    static Image cardBackImage;
    private static final Map<String, Image> cardFaceImages = new HashMap<>();
    public static final int WIDTH = 150;
    public static final int HEIGHT = 215;

    public Card(Suit suit, int rank, boolean faceDown) {
        this.suit = suit.number;
        this.color = suit.color;
        this.rank = rank;
        this.faceDown = faceDown;
        this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
        backFace = cardBackImage;
        frontFace = cardFaceImages.get(getShortName());
        setImage(faceDown ? backFace : frontFace);
        setEffect(dropShadow);
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public String getShortName() {
        return "S" + suit + "R" + rank;
    }

    public DropShadow getDropShadow() {
        return dropShadow;
    }

    public Pile getContainingPile() {
        return containingPile;
    }

    public void setContainingPile(Pile containingPile) {
        this.containingPile = containingPile;
    }

    public void moveToPile(Pile destPile) {
        Pile prevPile = this.getContainingPile();
        prevPile.getCards().remove(this);
        destPile.addCard(this);

        if (prevPile.getPileType() == Pile.PileType.TABLEAU)
            if (!prevPile.isEmpty() && prevPile.getTopCard().isFaceDown())
                prevPile.getTopCard().flip();
    }

    public void flip() {
        faceDown = !faceDown;
        setImage(faceDown ? backFace : frontFace);
    }

    @Override
    public String toString() {
        return "The " + "Rank" + rank + " of " + "Suit" + suit;
    }

    public static boolean isOppositeColor(Card card1, Card card2) {
        return !card1.color.equals(card2.color);
    }

    public static boolean isSameSuit(Card card1, Card card2) {
        return card1.getSuit() == card2.getSuit();
    }

    public static List<Card> createNewDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                result.add(new Card(suit, rank.number, true));
            }
        }
        shuffleCards(result);
        return result;
    }

    public static void loadCardImages() {
        cardBackImage = new Image("card_backs/szoke_ciklon.png");
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                String cardName = suit.name + rank.number;
                String cardId = "S" + suit.number + "R" + rank.number;
                String imageFileName = "card_images/" + cardName + ".png";
                cardFaceImages.put(cardId, new Image(imageFileName));
            }
        }
    }

    private static void shuffleCards(List<Card> deck) {
        Collections.shuffle(deck);
    }

    public enum Suit {
        HEARTS ("hearts", 1, "red"),
        DIAMONDS ("diamonds", 2, "red"),
        SPADES ("spades", 3, "black"),
        CLUBS ("clubs", 4, "black");

        private String name;
        private int number;
        private String color;

        Suit(String name, int number, String color) {
            this.name = name;
            this.number = number;
            this.color = color;
        }
    }

    public enum Rank {
        ACE (1),
        TWO (2),
        THREE (3),
        FOUR (4),
        FIVE (5),
        SIX (6),
        SEVEN (7),
        EIGHT (8),
        NINE (9),
        TEN (10),
        JACK (11),
        QUEEN (12),
        KING (13);

        private int number;

        Rank(int n) {
            number = n;
        }
    }

}
