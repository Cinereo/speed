package pl.edu.agh.to2.speed.game.service

import com.google.inject.Guice
import com.google.inject.Injector
import pl.edu.agh.to2.speed.game.guice.GameModule
import pl.edu.agh.to2.speed.game.model.*
import spock.lang.Specification


class ExchangeTopCardsServiceTest extends Specification{

    Injector injector = Guice.createInjector(new GameModule())
    ExchangeTopCardsService exchangeTopCardsService = injector.getInstance(ExchangeTopCardsService.class)
    InitializationService initializationService = injector.getInstance(InitializationService.class)

    def "set common cards to game after initialization"() {
        given:
        CardPlayer player1 = new CardPlayer()
        CardPlayer player2 = new CardPlayer()
        GameTable table = initializationService.getInitializedTable(player1, player2)

        when:
        exchangeTopCardsService.exchangeTopCards(table)

        then:

        player1.getHand().size() == 5
        player2.getHand().size() == 5
        player1.getPlayersCoveredCards().size() == 16
        player2.getPlayersCoveredCards().size() == 16

        table.getCommonCoveredCards1().size() == 4
        table.getCommonCoveredCards2().size() == 4

        table.getUncoveredCards1().size() == 1
        table.getUncoveredCards2().size() == 1
    }


    def "take new common cards 4 times"() {
        given:
        CardPlayer player1 = new CardPlayer()
        CardPlayer player2 = new CardPlayer()
        GameTable table = initializationService.getInitializedTable(player1, player2)

        when:
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)

        then:

        player1.getHand().size() == 5
        player2.getHand().size() == 5
        player1.getPlayersCoveredCards().size() == 16
        player2.getPlayersCoveredCards().size() == 16

        table.getCommonCoveredCards1().size() == 1
        table.getCommonCoveredCards2().size() == 1

        table.getUncoveredCards1().size() == 4
        table.getUncoveredCards2().size() == 4
    }

    def "take new common cards 5 times"() {
        given:
        CardPlayer player1 = new CardPlayer()
        CardPlayer player2 = new CardPlayer()
        GameTable table = initializationService.getInitializedTable(player1, player2)

        when:
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)

        then:

        player1.getHand().size() == 5
        player2.getHand().size() == 5
        player1.getPlayersCoveredCards().size() == 16
        player2.getPlayersCoveredCards().size() == 16

        table.getCommonCoveredCards1().size() == 0
        table.getCommonCoveredCards2().size() == 0

        table.getUncoveredCards1().size() == 5
        table.getUncoveredCards2().size() == 5
    }

    def "take new common cards 6 times"() {
        given:
        CardPlayer player1 = new CardPlayer()
        CardPlayer player2 = new CardPlayer()
        GameTable table = initializationService.getInitializedTable(player1, player2)

        when:
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)
        exchangeTopCardsService.exchangeTopCards(table)

        then:

        player1.getHand().size() == 5
        player2.getHand().size() == 5
        player1.getPlayersCoveredCards().size() == 16
        player2.getPlayersCoveredCards().size() == 16

        table.getCommonCoveredCards1().size() == 4
        table.getCommonCoveredCards2().size() == 4

        table.getUncoveredCards1().size() == 1
        table.getUncoveredCards2().size() == 1
    }

    def "check if good card is being added "() {
        given:
        Card card1 = new Card(CardSuit.CLUBS, CardRank.CARD_2)
        Card card2 = new Card(CardSuit.CLUBS, CardRank.CARD_3)
        Card card3 = new Card(CardSuit.CLUBS, CardRank.CARD_4)
        Card card4 = new Card(CardSuit.CLUBS, CardRank.CARD_5)

        GameTable table = new GameTable()

        table.setCardPlayer1(new CardPlayer())
        table.setCardPlayer2(new CardPlayer())

        UncoveredCards uncoveredCards1 = new UncoveredCards()
        UncoveredCards uncoveredCards2 = new UncoveredCards()

        CoveredCards coveredCards1 = new CoveredCards()
        CoveredCards coveredCards2 = new CoveredCards()

        uncoveredCards1.addCard(card1)
        uncoveredCards2.addCard(card2)

        coveredCards1.addCard(card3)
        coveredCards2.addCard(card4)

        table.setUncoveredCards1(uncoveredCards1)
        table.setUncoveredCards2(uncoveredCards2)

        table.setCommonCoveredCards1(coveredCards1)
        table.setCommonCoveredCards2(coveredCards2)

        when:

        exchangeTopCardsService.exchangeTopCards(table)

        then:
        table.getCommonCoveredCards1().size() == 0
        table.getCommonCoveredCards2().size() == 0

        table.getUncoveredCards1().size() == 2
        table.getUncoveredCards2().size() == 2

        table.getUncoveredCards1().lookAtFirstCard() == card3
        table.getUncoveredCards2().lookAtFirstCard() == card4
    }

    def "take card if any possible"() {
        given:
        Card card1 = new Card(CardSuit.CLUBS, CardRank.CARD_2)
        Card card2 = new Card(CardSuit.CLUBS, CardRank.CARD_3)
        Card card3 = new Card(CardSuit.CLUBS, CardRank.CARD_4)
        Card card4 = new Card(CardSuit.CLUBS, CardRank.CARD_5)

        GameTable table = new GameTable()

        table.setCardPlayer1(new CardPlayer())
        table.setCardPlayer2(new CardPlayer())

        UncoveredCards uncoveredCards1 = new UncoveredCards()
        UncoveredCards uncoveredCards2 = new UncoveredCards()

        CoveredCards coveredCards1 = new CoveredCards()
        CoveredCards coveredCards2 = new CoveredCards()

        uncoveredCards1.addCard(card1)
        uncoveredCards1.addCardAtFront(card2)

        uncoveredCards2.addCard(card3)
        uncoveredCards2.addCardAtFront(card4)

        table.setUncoveredCards1(uncoveredCards1)
        table.setUncoveredCards2(uncoveredCards2)

        table.setCommonCoveredCards1(coveredCards1)
        table.setCommonCoveredCards2(coveredCards2)

        when:

        exchangeTopCardsService.exchangeTopCards(table)

        then:
        table.getCommonCoveredCards1().size() == 1
        table.getCommonCoveredCards2().size() == 1

        table.getUncoveredCards1().size() == 1
        table.getUncoveredCards2().size() == 1
    }
}