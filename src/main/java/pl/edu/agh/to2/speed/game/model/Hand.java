package pl.edu.agh.to2.speed.game.model;

import java.util.List;

public class Hand extends CardSet{

    private static final int MAX_CARDS_IN_HAND = 5;

    public Hand() {
    }

    public Hand(List<Card> cards) {
        super(cards);
        if(cards.size() > MAX_CARDS_IN_HAND) {
            throw new IllegalArgumentException("Maximal number of cards in hand = "+MAX_CARDS_IN_HAND);
        }
    }

    public boolean isFull(){
        return this.cards.size() == MAX_CARDS_IN_HAND;
    }

    public Card lookAtCard(int index){
        return cards.get(index);
    }
}