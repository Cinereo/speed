package pl.edu.agh.to2.speed.game.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandTest {

    private List<Card> cardsNList(int n) {
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new Card(CardSuit.CLUBS, CardRank.ACE));
        }
        return result;
    }

    @Test
    public void isFullTest() {
        for (int i = 0; i < 5; i++) {
            assertThat(new Hand(cardsNList(i)).isFull(), is(false));
        }
        assertThat(new Hand(cardsNList(5)).isFull(), is(true));
    }

    @Test
    public void isEmptyTest() {
        for (int i = 5; i > 0; i--) {
            assertThat(new Hand(cardsNList(i)).isEmpty(), is(false));
        }
        assertThat(new Hand(cardsNList(0)).isEmpty(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void handWithMoreThanMaxElements() {
        new Hand(this.cardsNList(6));
        new Hand(this.cardsNList(10));
    }
}
