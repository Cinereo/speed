package pl.edu.agh.to2.speed.commons.converters;

import pl.edu.agh.to2.speed.commons.model.CardDto;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.CardRank;
import pl.edu.agh.to2.speed.game.model.CardSuit;

public class CardConverter {
    public static Card fromDto(CardDto cardDto){
        CardSuit cardSuit = CardSuit.valueOf(cardDto.cardSuite);
        CardRank cardRank = CardRank.valueOf(cardDto.cardRank);
        return new Card(cardSuit, cardRank);
    }

    public static CardDto toDto(Card card){
        String cardSuit = card.getSuit().name();
        String cardRank = card.getRank().name();
        return new CardDto(cardRank, cardSuit);
    }
}
