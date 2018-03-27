package pl.edu.agh.to2.speed.game.model;

import org.junit.Test;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.CardRank;
import pl.edu.agh.to2.speed.game.model.CardSuit;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CardTest {
    @Test
    public void testEqualCards(){
        // given
        CardSuit hearts = CardSuit.HEARTS;
        CardSuit clubs = CardSuit.CLUBS;

        CardRank ace = CardRank.ACE;
        CardRank two = CardRank.CARD_2;

        // when
        Card card1 = new Card(hearts,ace);
        Card card2 = new Card(hearts,two);
        Card card3 = new Card(clubs,ace);
        Card card4 = new Card(hearts,ace);

        //then
        assertThat(card1,is(card4));
        assertThat(card1,is(not(card2)));
        assertThat(card1,is(not(card3)));
        assertThat(card2,is(not(card3)));
    }
}
