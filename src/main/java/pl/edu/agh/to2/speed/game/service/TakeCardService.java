package pl.edu.agh.to2.speed.game.service;

import pl.edu.agh.to2.speed.game.model.CardPlayer;
import pl.edu.agh.to2.speed.game.model.CoveredCards;
import pl.edu.agh.to2.speed.game.model.Hand;

public class TakeCardService {

    public boolean canPlayerTakeACard(CardPlayer player){
        Hand hand = player.getHand();
        if(hand.isFull()) {
            return false;
        }
        CoveredCards cardsToTake = player.getPlayersCoveredCards();
        if (cardsToTake.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean takeCard(CardPlayer player){
        if(canPlayerTakeACard(player)){
            CoveredCards cardsToTake = player.getPlayersCoveredCards();
            Hand hand = player.getHand();
            hand.addCard(cardsToTake.takeFirstCard());
            return true;
        }
        return false;
    }
}
