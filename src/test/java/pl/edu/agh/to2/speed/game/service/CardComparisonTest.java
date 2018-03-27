package pl.edu.agh.to2.speed.game.service;

import org.junit.Test;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.CardRank;
import pl.edu.agh.to2.speed.game.model.CardSuit;

import static org.junit.Assert.*;

public class CardComparisonTest {

    @Test
    public void testCardsWith4And5Rank(){
        // given
        Card card1 = new Card(CardSuit.CLUBS, CardRank.CARD_4);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.CARD_5);

        // then
        assertTrue(CardComparison.areCardsNext(card1,card2));
        assertTrue(CardComparison.areCardsNext(card2,card1));
    }

    @Test
    public void testAceAndTwoCards(){
        // given
        Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.CARD_2);

        // then
        assertTrue(CardComparison.areCardsNext(card1,card2));
        assertTrue(CardComparison.areCardsNext(card2,card1));
    }

    @Test
    public void testCardsWith4And6Rank(){
        // given
        Card card1 = new Card(CardSuit.CLUBS, CardRank.CARD_4);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.CARD_6);

        // then
        assertFalse(CardComparison.areCardsNext(card1,card2));
        assertFalse(CardComparison.areCardsNext(card2,card1));
    }

    @Test
    public void testTwoCardsWithSameRank(){
        // given
        Card card1 = new Card(CardSuit.CLUBS, CardRank.CARD_4);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.CARD_4);

        // then
        assertFalse(CardComparison.areCardsNext(card1,card2));
        assertFalse(CardComparison.areCardsNext(card2,card1));
    }
}
