package pl.edu.agh.to2.speed.game.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CardSetTest {

    private final Card card1 = new Card(CardSuit.CLUBS, CardRank.ACE);
    private final Card card2 = new Card(CardSuit.CLUBS, CardRank.KING);
    private final Card card3 = new Card(CardSuit.CLUBS, CardRank.QUEEN);
    private final Card card4 = new Card(CardSuit.CLUBS, CardRank.JACK);
    private final Card card5 = new Card(CardSuit.CLUBS, CardRank.CARD_10);
    private final Card card6 = new Card(CardSuit.CLUBS, CardRank.CARD_9);
    private final Card card7 = new Card(CardSuit.CLUBS, CardRank.CARD_8);

    private List<Card> listOfSix = Arrays.asList(card1, card2, card3, card4, card5, card6);
    private List<Card> listOfFive = Arrays.asList(card2, card3, card4, card5, card6);
    private List<Card> listOfFour = Arrays.asList(card3, card4, card5, card7);

    private CardSet hand5;
    private CardSet hand4;
    private CardSet uncov6;
    private CardSet uncov0;

    @Before
    public void init() {
        hand5 = new Hand(listOfFive);
        hand4 = new Hand(listOfFour);
        uncov6 = new UncoveredCards(listOfSix);
        uncov0 = new UncoveredCards(Collections.emptyList());
    }

    @Test
    public void sizeTest() {
        assertThat(hand5.size(), is(5));
        assertThat(hand4.size(), is(4));
        assertThat(uncov6.size(), is(6));
        assertThat(uncov0.size(), is(0));
    }


    @Test
    public void addAtFrontTest() {
        // given
        Card c = new Card(CardSuit.DIAMONDS, CardRank.CARD_2);
        // when
        hand5.addCardAtFront(c);
        hand4.addCardAtFront(c);
        uncov0.addCardAtFront(c);
        // then
        assertThat(hand5.lookAtFirstCard(), is(c));
        assertThat(hand4.lookAtFirstCard(), is(c));
        assertThat(uncov0.lookAtFirstCard(), is(c));
    }

    @Test
    public void addCardTest() {
        // given
        Card c = new Card(CardSuit.DIAMONDS, CardRank.CARD_2);
        // when
        hand5.addCard(c);
        hand4.addCard(c);
        uncov0.addCard(c);
        // then
        assertThat(hand5.getCards().get(5), is(c));
        assertThat(hand4.getCards().get(4), is(c));
        assertThat(uncov0.getCards().get(0), is(c));
    }

    @Test
    public void isEmptyTest() {
        assertThat(hand5.isEmpty(), is(false));
        assertThat(hand4.isEmpty(), is(false));
        assertThat(uncov6.isEmpty(), is(false));
        assertThat(uncov0.isEmpty(), is(true));
    }

    @Test
    public void getCardsTest() {
        assertThat(hand5.getCards(), is(listOfFive));
        assertThat(hand4.getCards(), is(listOfFour));
        assertThat(uncov6.getCards(), is(listOfSix));
        assertThat(uncov0.getCards(), is(Collections.emptyList()));
    }

    @Test
    public void takeFirstCardTest() {
        // given
        CardSet hand5 = new Hand(listOfFive);
        CardSet hand4 = new Hand(listOfFour);
        CardSet uncov6 = new UncoveredCards(listOfSix);
        // then
        assertThat(hand5.takeFirstCard(), is(card2));
        assertThat(hand4.takeFirstCard(), is(card3));
        assertThat(uncov6.takeFirstCard(), is(card1));
        assertThat(uncov6.takeFirstCard(), is(card2));

        assertThat(hand5.size(), is(4));
        assertThat(hand4.size(), is(3));
        assertThat(uncov6.size(), is(4));
    }

    @Test
    public void lookAtFirstCardTest() {
        // given
        CardSet hand5 = new Hand(listOfFive);
        CardSet hand4 = new Hand(listOfFour);
        CardSet uncov6 = new UncoveredCards(listOfSix);
        // then
        assertThat(hand5.lookAtFirstCard(), is(card2));
        assertThat(hand4.lookAtFirstCard(), is(card3));
        assertThat(uncov6.lookAtFirstCard(), is(card1));

        assertThat(hand5.size(), is(5));
        assertThat(hand4.size(), is(4));
        assertThat(uncov6.size(), is(6));
    }

    @Test
    public void addCardSetTest() {
        // given
        hand5.addCardSet(hand4);
        uncov0.addCardSet(uncov6);
        hand4.addCardSet(uncov6);
        // when
        List<Card> l1 = new LinkedList<>(listOfFive);
        l1.addAll(listOfFour);
        List<Card> l2 = new LinkedList<>(listOfFour);
        l2.addAll(listOfSix);
        // then
        assertThat(hand5.getCards(), is(l1));
        assertThat(hand4.getCards(), is(l2));
        assertThat(uncov0.getCards(), is(listOfSix));
    }
}
