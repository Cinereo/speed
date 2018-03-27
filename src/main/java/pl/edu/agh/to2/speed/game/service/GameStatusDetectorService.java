package pl.edu.agh.to2.speed.game.service;


import com.google.inject.Inject;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.CardPlayer;
import pl.edu.agh.to2.speed.game.model.GameTable;
import pl.edu.agh.to2.speed.game.model.GameWinner;
import java.util.List;

public class GameStatusDetectorService {

    @Inject
    public TakeCardService takeCardService;

    public GameWinner gameWinner(GameTable table){
        CardPlayer player1 = table.getCardPlayer1();
        CardPlayer player2 = table.getCardPlayer2();

        if(player1.isPlayerOutOfCards()){
            return GameWinner.PLAYER_1;
        }

        if(player2.isPlayerOutOfCards()){
            return GameWinner.PLAYER_2;
        }

        if(! isAnyMovePossible(table) &&
                ! takeCardService.canPlayerTakeACard(player1) &&
                ! takeCardService.canPlayerTakeACard(player2)){
            return GameWinner.DRAW;
        }
        return GameWinner.NO_WINNER;
    }

    // can any player push a card
    public boolean isAnyMovePossible(GameTable gameTable){
        return canPlayerMakeMove(gameTable, gameTable.getCardPlayer1()) || canPlayerMakeMove(gameTable, gameTable.getCardPlayer2());
    }

    // check if player can take a card. Do not specify if player can take card
    public boolean canPlayerMakeMove(GameTable gameTable, CardPlayer player){
        List<Card> uncoveredCards = gameTable.getUncoveredCardsOnTop();

        for(Card playerCard : player.getHand().getCards()){
            for (Card uncoveredCard : uncoveredCards){
                if(CardComparison.areCardsNext(playerCard, uncoveredCard)){
                    return true;
                }
            }
        }
        return false;
    }
}
