package pl.edu.agh.to2.speed.game.service;

import pl.edu.agh.to2.speed.game.model.*;

public class InitializationService {
    private static int HAND_INIT_SIZE = 5;
    private static int PLAYER_HIDDEN_INIT_SIZE = 16;
    private static int COMMON_HIDDEN_INIT_SIZE = 5;

    public GameTable getInitializedTable() {
        CardPlayer player1 = new CardPlayer();
        CardPlayer player2 = new CardPlayer();
        return getInitializedTable(player1, player2);
    }

    public GameTable getInitializedTable(CardPlayer player1, CardPlayer player2){

        GameTable table = new GameTable();

        table.setCardPlayer1(player1);
        table.setCardPlayer2(player2);

        Deck deck = table.getDeck();

        setPlayersHand(player1, player2, deck);

        setPlayersHiddenCards(player1, player2, deck);

        setCommonHiddenCards(table, deck);

        setCommonUncoveredCards(table);
        return table;
    }

    public void updateTableModelByNewTable(GameTable current, GameTable newTable){
        current.setCommonCoveredCards1(newTable.getCommonCoveredCards1());
        current.setCommonCoveredCards2(newTable.getCommonCoveredCards2());

        current.setUncoveredCards1(newTable.getUncoveredCards1());
        current.setUncoveredCards2(newTable.getUncoveredCards2());

        current.setCardPlayer1(newTable.getCardPlayer1());
        current.setCardPlayer2(newTable.getCardPlayer2());

        current.setDeck(newTable.getDeck());
    }

    private void setCommonUncoveredCards(GameTable table) {
        table.setUncoveredCards1(new UncoveredCards());
        table.setUncoveredCards2(new UncoveredCards());
    }

    private void setCommonHiddenCards(GameTable table, Deck deck) {
        CoveredCards coveredCards1 = new CoveredCards(deck.takeRandomList(COMMON_HIDDEN_INIT_SIZE));
        CoveredCards coveredCards2 = new CoveredCards(deck.takeRandomList(COMMON_HIDDEN_INIT_SIZE));

        table.setCommonCoveredCards1(coveredCards1);
        table.setCommonCoveredCards2(coveredCards2);
    }

    private void setPlayersHiddenCards(CardPlayer player1, CardPlayer player2, Deck deck) {
        CoveredCards coveredCards1 = new CoveredCards(deck.takeRandomList(PLAYER_HIDDEN_INIT_SIZE));
        CoveredCards coveredCards2 = new CoveredCards(deck.takeRandomList(PLAYER_HIDDEN_INIT_SIZE));

        player1.setPlayersCoveredCards(coveredCards1);
        player2.setPlayersCoveredCards(coveredCards2);
    }

    private void setPlayersHand(CardPlayer player1, CardPlayer player2, Deck deck) {
        Hand hand1 = new Hand(deck.takeRandomList(HAND_INIT_SIZE));
        Hand hand2 = new Hand(deck.takeRandomList(HAND_INIT_SIZE));

        player1.setHand(hand1);
        player2.setHand(hand2);
    }

}
