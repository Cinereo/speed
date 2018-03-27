package pl.edu.agh.to2.speed.game.model;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.speed.game.service.InitializationService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameTableTest {

    private GameTable gameTable1;
    private GameTable gameTable2;
    private GameTable gameTable3;
    private CardPlayer a = new CardPlayer();
    private CardPlayer b = new CardPlayer();
    private List<Card> allCards = new LinkedList<>();
    private Random random = new Random();
    private UncoveredCards uncoveredCards1;
    private UncoveredCards uncoveredCards2;

    @Before
    public void initGameTable() {
        InitializationService service = new InitializationService();
        gameTable1 = service.getInitializedTable(a, b);
        gameTable2 = service.getInitializedTable(b, a);

        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                allCards.add(new Card(cardSuit, cardRank));
            }
        }

        int s = allCards.size();
        uncoveredCards1 = new UncoveredCards(Arrays.asList(allCards.get(random.nextInt(s)),
                allCards.get(random.nextInt(s)),allCards.get(random.nextInt(s))));
        uncoveredCards2 = new UncoveredCards(Arrays.asList(allCards.get(random.nextInt(s)),
                allCards.get(random.nextInt(s)),allCards.get(random.nextInt(s))));
        gameTable3 = new GameTable(a, b, uncoveredCards1, uncoveredCards2, null, null);
    }

    @Test
    public void getCardsPlayerTest() {
        assertThat(gameTable1.getCardPlayer1(), is(a));
        assertThat(gameTable1.getCardPlayer2(), is(b));
        assertThat(gameTable2.getCardPlayer1(), is(b));
        assertThat(gameTable2.getCardPlayer2(), is(a));
    }

    @Test
    public void getUncoveredCardsOnTopTest() {
        // given
        List<Card> cardsOnTop = gameTable3.getUncoveredCardsOnTop();
        // then
        assertThat(cardsOnTop.size(), is(2));
        assertThat(cardsOnTop.contains(uncoveredCards1.lookAtFirstCard()), is(true));
        assertThat(cardsOnTop.contains(uncoveredCards2.lookAtFirstCard()), is(true));
    }

}
