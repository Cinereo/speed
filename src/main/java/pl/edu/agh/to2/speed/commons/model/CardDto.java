package pl.edu.agh.to2.speed.commons.model;

import java.io.Serializable;

public class CardDto implements Serializable {
    public final String cardRank;
    public final String cardSuite;

    public CardDto(String cardRank, String cardSuite) {
        this.cardRank = cardRank;
        this.cardSuite = cardSuite;
    }
}