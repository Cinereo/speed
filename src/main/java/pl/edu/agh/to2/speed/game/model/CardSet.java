package pl.edu.agh.to2.speed.game.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CardSet {

    // First in list elements is card on top
    // Observable for GUI controller's needs
    protected ObservableList<Card> cards;

    public CardSet() {
        this(new ArrayList<>());
    }

    public CardSet(List<Card> cards) {
        this.cards = FXCollections.observableArrayList(cards);
    }

    public void addCardSet(CardSet cardSet) {
        cards.addAll(cardSet.getCards());
    }

    public int size() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public Card takeFirstCard() {
        if (cards.size() > 0) {
            return cards.remove(0);
        } else {
            throw new IllegalStateException("Cannot take card from empty card set");
        }
    }

    public Card lookAtFirstCard() {
        return cards.get(0);
    }

    public void addCardAtFront(Card card) {
        cards.add(0, card);
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        return cards.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
