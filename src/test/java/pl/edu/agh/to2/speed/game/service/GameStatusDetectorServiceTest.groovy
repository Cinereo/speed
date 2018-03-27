package pl.edu.agh.to2.speed.game.service

import com.google.inject.Guice
import com.google.inject.Injector
import pl.edu.agh.to2.speed.game.guice.GameModule
import pl.edu.agh.to2.speed.game.model.Card
import pl.edu.agh.to2.speed.game.model.CardPlayer
import pl.edu.agh.to2.speed.game.model.CardRank
import pl.edu.agh.to2.speed.game.model.CardSuit
import pl.edu.agh.to2.speed.game.model.CoveredCards
import pl.edu.agh.to2.speed.game.model.GameTable
import pl.edu.agh.to2.speed.game.model.GameWinner
import pl.edu.agh.to2.speed.game.model.Hand
import spock.lang.*


class GameStatusDetectorServiceTest extends Specification{

    Injector injector = Guice.createInjector(new GameModule())
    GameStatusDetectorService gameStatusDetectorService = injector.getInstance(GameStatusDetectorService.class)

    def "can player without cards make a move"() {
        given:
            CardPlayer player1 = new CardPlayer()
            CardPlayer player2 = new CardPlayer()
            GameTable gameTable = new GameTable()

            gameTable.setCardPlayer1(player1)
            gameTable.setCardPlayer2(player2)
        when:
            boolean canMakeMove = gameStatusDetectorService.canPlayerMakeMove(gameTable, player1)
        then:
            !canMakeMove
    }

    def "can player with ace put card on king"() {
        given:
            Card ace = new Card(CardSuit.HEARTS, CardRank.ACE)
            Card king = new Card(CardSuit.DIAMONDS, CardRank.KING)
            CardPlayer player1 = Stub()
            GameTable gameTable = Stub()
            Hand hand = new Hand()
            hand.addCard(ace)
            List<Card> list = new LinkedList<>()
            list.add(king)
            player1.getHand() >> hand
            gameTable.uncoveredCardsOnTop >> list
        when:
            boolean canMakeMove = gameStatusDetectorService.canPlayerMakeMove(gameTable, player1)
        then:
            canMakeMove
    }

    def "can player with ace put card on jack"() {
        given:
            Card ace = new Card(CardSuit.HEARTS, CardRank.ACE)
            Card king = new Card(CardSuit.DIAMONDS, CardRank.JACK)
            CardPlayer player1 = Stub()
            GameTable gameTable = Stub()
            Hand hand = new Hand()
            hand.addCard(ace)
            List<Card> list = new LinkedList<>()
            list.add(king)
            player1.getHand() >> hand
            gameTable.uncoveredCardsOnTop >> list
        when:
            boolean canMakeMove = gameStatusDetectorService.canPlayerMakeMove(gameTable, player1)
        then:
            !canMakeMove
    }

    def "can move be done if one player can make a move"() {
        given:
            Card ace = new Card(CardSuit.HEARTS, CardRank.ACE)
            Card king = new Card(CardSuit.DIAMONDS, CardRank.KING)
            CardPlayer player1 = Stub()
            CardPlayer player2 = Stub()
            GameTable gameTable = Stub()
            Hand hand1 = new Hand()
            Hand hand2 = new Hand()
            hand1.addCard(ace)
            List<Card> list = new LinkedList<>()
            list.add(king)
            player1.getHand() >> hand1
            player2.getHand() >> hand2
            gameTable.getCardPlayer1() >> player1
            gameTable.getCardPlayer2() >> player2
            gameTable.uncoveredCardsOnTop >> list
        when:
            boolean canMakeMove1 = gameStatusDetectorService.canPlayerMakeMove(gameTable, player1)
            boolean canMakeMove2 = gameStatusDetectorService.canPlayerMakeMove(gameTable, player2)

            boolean isMovePossible = gameStatusDetectorService.isAnyMovePossible(gameTable)
        then:
            canMakeMove1
            ! canMakeMove2
            isMovePossible
    }

    def "can move be done if no player can make a move"() {
        given:
            Card ace = new Card(CardSuit.HEARTS, CardRank.ACE)
            Card king = new Card(CardSuit.DIAMONDS, CardRank.JACK)
            CardPlayer player1 = Stub()
            CardPlayer player2 = Stub()
            GameTable gameTable = Stub()
            Hand hand1 = new Hand()
            Hand hand2 = new Hand()
            hand1.addCard(ace)
            List<Card> list = new LinkedList<>()
            list.add(king)
            player1.getHand() >> hand1
            player2.getHand() >> hand2
            gameTable.getCardPlayer1() >> player1
            gameTable.getCardPlayer2() >> player2
            gameTable.uncoveredCardsOnTop >> list
        when:
            boolean canMakeMove1 = gameStatusDetectorService.canPlayerMakeMove(gameTable, player1)
            boolean canMakeMove2 = gameStatusDetectorService.canPlayerMakeMove(gameTable, player2)

            boolean isMovePossible = gameStatusDetectorService.isAnyMovePossible(gameTable)
        then:
            ! canMakeMove1
            ! canMakeMove2
            ! isMovePossible
    }

    def "should be bo winner if two players have covered cards "() {
        given:
            Card ace = new Card(CardSuit.HEARTS, CardRank.ACE)
            List<Card> notEmptyList = new LinkedList<>()
            notEmptyList.add(ace)
            CardPlayer player1 = Stub()
            CardPlayer player2 = Stub()
            player1.getPlayersCoveredCards() >> new CoveredCards(notEmptyList)
            player2.getPlayersCoveredCards() >> new CoveredCards(notEmptyList)
            GameTable gameTable = Stub()
            gameTable.getCardPlayer1() >> player1
            gameTable.getCardPlayer2() >> player2
        when:
          GameWinner winner = gameStatusDetectorService.gameWinner(gameTable)
        then:
            winner == GameWinner.NO_WINNER
    }

    def "player without any cards is a winner"() {
        given:
            CardPlayer player1 = Stub()
            CardPlayer player2 = Stub()
            player1.isPlayerOutOfCards() >> true
            player2.isPlayerOutOfCards() >> false
            GameTable gameTable = Stub()
            gameTable.getCardPlayer1() >> player1
            gameTable.getCardPlayer2() >> player2
        when:
            GameWinner winner = gameStatusDetectorService.gameWinner(gameTable)
        then:
            winner == GameWinner.PLAYER_1
    }
}