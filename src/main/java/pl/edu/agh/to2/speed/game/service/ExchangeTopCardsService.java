package pl.edu.agh.to2.speed.game.service;

import pl.edu.agh.to2.speed.game.model.CoveredCards;
import pl.edu.agh.to2.speed.game.model.GameTable;
import pl.edu.agh.to2.speed.game.model.UncoveredCards;
import pl.edu.agh.to2.speed.game.model.Card;
import java.util.List;
import java.util.Optional;

public class ExchangeTopCardsService {

    // Take cards from covered sets and put the at top of uncovered cards
    public void exchangeTopCards(GameTable gameTable){
        CoveredCards coveredCards1 = gameTable.getCommonCoveredCards1();
        CoveredCards coveredCards2 = gameTable.getCommonCoveredCards2();

        if(coveredCards1.isEmpty() || coveredCards2.isEmpty()){
            shuffleCommonCardsAndPutToHiddenCommon(gameTable);
        }

        coveredCards1 = gameTable.getCommonCoveredCards1();
        coveredCards2 = gameTable.getCommonCoveredCards2();

        Card newTopCard1 = coveredCards1.takeFirstCard();
        Card newTopCard2 = coveredCards2.takeFirstCard();

        gameTable.getUncoveredCards1().addCardAtFront(newTopCard1);
        gameTable.getUncoveredCards2().addCardAtFront(newTopCard2);
    }

    // If there are no cards to take from, there is a necessity to take common cards,
    // shuffle them and make them covered cards
    private void shuffleCommonCardsAndPutToHiddenCommon(GameTable gameTable){
        UncoveredCards uncoveredCards1 = gameTable.getUncoveredCards1() ;
        UncoveredCards uncoveredCards2 = gameTable.getUncoveredCards2() ;

        UncoveredCards tmpUncoveredCards = new UncoveredCards();
        tmpUncoveredCards.addCardSet(uncoveredCards1);
        tmpUncoveredCards.addCardSet(uncoveredCards2);
        tmpUncoveredCards.shuffleCards();

        int cardInTmpCardSetNumber = tmpUncoveredCards.size();

        List<Card> cardList1 = tmpUncoveredCards.getCards().subList(0, cardInTmpCardSetNumber/2);
        List<Card> cardList2 = tmpUncoveredCards.getCards().subList(cardInTmpCardSetNumber/2, cardInTmpCardSetNumber);

        CoveredCards newCoveredCards1 = new CoveredCards(cardList1);
        CoveredCards newCoveredCards2 = new CoveredCards(cardList2);

        gameTable.setCommonCoveredCards1(newCoveredCards1);
        gameTable.setCommonCoveredCards2(newCoveredCards2);

        gameTable.setUncoveredCards1(new UncoveredCards());
        gameTable.setUncoveredCards2(new UncoveredCards());
    }
}
