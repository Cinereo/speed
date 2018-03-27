package pl.edu.agh.to2.speed.game.service;

import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.CardRank;

public class CardComparison {

    public static boolean areCardsNext(Card card1, Card card2){
        if (card1.getRank() == CardRank.ACE && card2.getRank() == CardRank.CARD_2)
            return true;
        if (card2.getRank() == CardRank.ACE && card1.getRank() == CardRank.CARD_2)
            return true;
        return Math.abs(card1.getRank().ordinal() - card2.getRank().ordinal()) == 1;
    }
}