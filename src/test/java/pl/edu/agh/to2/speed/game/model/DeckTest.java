package pl.edu.agh.to2.speed.game.model;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.Deck;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DeckTest {

    private Deck deck;
    @Before
    public void initDeck(){
        deck = new Deck();
    }

    @Test
    public void deckSizeTest(){
        assertThat(deck.getCards().size(), is(52));
    }

    @Test
    public void takeRandomTest(){
        //when
        Card card = deck.takeRandom();
        // then
        assertThat(deck.getCards().size(), is(51));
        assertThat(deck.getCards().indexOf(card), is(-1));
    }

    @Test
    public void takeRandomList(){
        // given
        List<Card> cards = deck.takeRandomList(2);

        // when
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);

        //then
        assertThat(deck.getCards().size(), is(50));
        assertThat(cards.size(), is(2));

        assertThat(card1, not(card2));
        assertThat(deck.getCards().indexOf(card1), is(-1));
        assertThat(deck.getCards().indexOf(card2), is(-1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void takeRandomListToMany(){
        deck.takeRandomList(53);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void takeRandomListToMany2(){
        deck.takeRandomList(52);
        deck.takeRandomList(1);
    }
}
