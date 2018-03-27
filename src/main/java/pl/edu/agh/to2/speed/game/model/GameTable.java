package pl.edu.agh.to2.speed.game.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GameTable {

    private Optional<CardPlayer> cardPlayer1;
    private Optional<CardPlayer> cardPlayer2;

    private Optional<CoveredCards> commonCoveredCards1;
    private Optional<CoveredCards> commonCoveredCards2;

    private Optional<UncoveredCards> uncoveredCards1;
    private Optional<UncoveredCards> uncoveredCards2;

    private Deck deck;

    public GameTable() {
        this(null, null, null, null, null, null);
    }

    public GameTable(CardPlayer cardPlayer1,
                     CardPlayer cardPlayer2,
                     UncoveredCards uncoveredCards1,
                     UncoveredCards uncoveredCards2,
                     CoveredCards commonCoveredCards1,
                     CoveredCards commonCoveredCards2) {
        deck = new Deck();
        this.cardPlayer1 = Optional.ofNullable(cardPlayer1);
        this.cardPlayer2 = Optional.ofNullable(cardPlayer2);
        this.uncoveredCards1 = Optional.ofNullable(uncoveredCards1);
        this.uncoveredCards2 = Optional.ofNullable(uncoveredCards2);
        this.commonCoveredCards1 = Optional.ofNullable(commonCoveredCards1);
        this.commonCoveredCards2 = Optional.ofNullable(commonCoveredCards2);
    }

    public CardPlayer getCardPlayer1() {
        return cardPlayer1.orElseThrow(() -> new RuntimeException("Player1 is empty"));
    }

    public void setCardPlayer1(CardPlayer cardPlayer1) {
        this.cardPlayer1 = Optional.of(cardPlayer1);
    }

    public CardPlayer getCardPlayer2() {
        return cardPlayer2.orElseThrow(() -> new RuntimeException("Player2 is empty"));
    }

    public void setCardPlayer2(CardPlayer cardPlayer2) {
        this.cardPlayer2 = Optional.of(cardPlayer2);
    }

    public CoveredCards getCommonCoveredCards1() {
        return commonCoveredCards1.orElseThrow(() -> new RuntimeException("CommonCoveredCards1 is empty"));
    }

    public void setCommonCoveredCards1(CoveredCards commonCoveredCards1) {
        this.commonCoveredCards1 = Optional.of(commonCoveredCards1);
    }

    public CoveredCards getCommonCoveredCards2() {
        return commonCoveredCards2.orElseThrow(() -> new RuntimeException("CommonCoveredCards2 is empty"));
    }

    public void setCommonCoveredCards2(CoveredCards commonCoveredCards2) {
        this.commonCoveredCards2 = Optional.of(commonCoveredCards2);
    }

    public UncoveredCards getUncoveredCards1() {
        return uncoveredCards1.orElseThrow(() -> new RuntimeException("UncoveredCards1 is empty"));
    }

    public void setUncoveredCards1(UncoveredCards uncoveredCards1) {
        this.uncoveredCards1 = Optional.of(uncoveredCards1);
    }

    public UncoveredCards getUncoveredCards2() {
        return uncoveredCards2.orElseThrow(() -> new RuntimeException("UncoveredCards2 is empty"));
    }

    public void setUncoveredCards2(UncoveredCards uncoveredCards2) {
        this.uncoveredCards2 = Optional.of(uncoveredCards2);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> getUncoveredCardsOnTop() {
        List<Card> topCards = new LinkedList<>();

        if (uncoveredCards1.isPresent() && !uncoveredCards1.get().isEmpty()) {
            topCards.add(uncoveredCards1.get().lookAtFirstCard());
        }

        if (uncoveredCards2.isPresent() && !uncoveredCards2.get().isEmpty()) {
            topCards.add(uncoveredCards2.get().lookAtFirstCard());
        }
        return topCards;
    }
}
