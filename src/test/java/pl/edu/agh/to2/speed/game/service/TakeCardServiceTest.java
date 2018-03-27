package pl.edu.agh.to2.speed.game.service;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.speed.game.guice.GameModule;
import pl.edu.agh.to2.speed.game.model.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class TakeCardServiceTest {

    private Injector injector = Guice.createInjector(new GameModule());
    private TakeCardService takeCardService = injector.getInstance(TakeCardService.class);

    private Card aceClub;
    private Card fourHeart;
    private Hand emptyHand;
    private CoveredCards emptyCoveredCards;
    private CardPlayer defaultPlayer;

    @Before
    public void init(){
        aceClub = new Card(CardSuit.CLUBS, CardRank.ACE);
        fourHeart = new Card(CardSuit.HEARTS, CardRank.CARD_4);

        emptyHand = new Hand();
        emptyCoveredCards = new CoveredCards();

        defaultPlayer = new CardPlayer();
        defaultPlayer.setHand(emptyHand);
        defaultPlayer.setPlayersCoveredCards(emptyCoveredCards);
    }


    @Test
    public void fullHandTest(){
        // given
        Hand mockedHand = mock(Hand.class);
        when(mockedHand.isFull()).thenReturn(true);

        CoveredCards coveredCards = mock(CoveredCards.class);
        when(coveredCards.isEmpty()).thenReturn(false);

        CardPlayer mockedCardPlayer = mock(CardPlayer.class);
        when(mockedCardPlayer.getHand()).thenReturn(mockedHand);

        // then
        assertFalse(takeCardService.takeCard(mockedCardPlayer));
    }

    @Test
    public void noCardsToTakeTest(){
        // given
        Hand mockedHand = mock(Hand.class);
        when(mockedHand.isFull()).thenReturn(false);

        CoveredCards mockedCoveredCards = mock(CoveredCards.class);
        when(mockedCoveredCards.isEmpty()).thenReturn(true);

        CardPlayer mockedCardPlayer = mock(CardPlayer.class);
        when(mockedCardPlayer.getHand()).thenReturn(mockedHand);
        when(mockedCardPlayer.getPlayersCoveredCards()).thenReturn(mockedCoveredCards);

        // then
        assertFalse(takeCardService.takeCard(mockedCardPlayer));
    }

    @Test
    public void wellCardsToTakeTest(){
        // given
        Hand mockedHand = mock(Hand.class);
        when(mockedHand.isFull()).thenReturn(false);

        CoveredCards mockedCoveredCards = mock(CoveredCards.class);
        when(mockedCoveredCards.isEmpty()).thenReturn(false);

        CardPlayer mockedCardPlayer = mock(CardPlayer.class);
        when(mockedCardPlayer.getHand()).thenReturn(mockedHand);
        when(mockedCardPlayer.getPlayersCoveredCards()).thenReturn(mockedCoveredCards);

        // then
        assertTrue(takeCardService.takeCard(mockedCardPlayer));
    }

    @Test
    public void cardMigrationSizeTest(){
        // when
        emptyCoveredCards.addCard(aceClub);
        emptyCoveredCards.addCard(fourHeart);

        // then
        assertTrue(takeCardService.takeCard(defaultPlayer));

        assertThat(emptyCoveredCards.size(), is(1));
        assertThat(emptyHand.size(), is(1));
    }

    @Test
    public void cardMigrationTest(){
        // when
        emptyCoveredCards.addCard(aceClub);

        //then
        assertThat(emptyCoveredCards.size(), is(1));
        assertThat(emptyHand.size(), is(0));

        assertTrue(takeCardService.takeCard(defaultPlayer));

        assertThat(emptyCoveredCards.size(), is(0));
        assertThat(emptyHand.size(), is(1));

        assertThat(emptyHand.getCards().get(0), is(aceClub));
    }
}
