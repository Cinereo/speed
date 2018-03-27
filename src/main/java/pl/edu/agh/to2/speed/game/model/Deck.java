package pl.edu.agh.to2.speed.game.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> cards;
    private Random random;

    public Deck() {
        this.random = new Random();
        createDeck();
    }

    public Card takeRandom() {
        return cards.remove(random.nextInt(cards.size()));
    }

    public List<Card> takeRandomList(int n){
        if (n > cards.size()){
            throw new IndexOutOfBoundsException("Try take more cards than exists in cards-deck");
        }

        List <Card> sublist = new LinkedList<>();
        for (int i = 0; i < n; i++){
            sublist.add(takeRandom());
        }
        return sublist;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    private void createDeck() {
        cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }
}