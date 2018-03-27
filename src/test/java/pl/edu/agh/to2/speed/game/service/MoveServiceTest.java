package pl.edu.agh.to2.speed.game.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;
import pl.edu.agh.to2.speed.game.guice.GameModule;
import pl.edu.agh.to2.speed.game.model.*;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MoveServiceTest {

    private Injector injector = Guice.createInjector(new GameModule());
    private GameStatusDetectorService gameStatusDetectorService = injector.getInstance(GameStatusDetectorService.class);
    private MoveService moveService = injector.getInstance(MoveService.class);

    @Test
    public void pushCardTest() {
        CardPlayer cardPlayer1 = new CardPlayer();
        CardPlayer cardPlayer2 = new CardPlayer();
        UncoveredCards uncoveredCards1 = new UncoveredCards(Arrays.asList(new Card(CardSuit.DIAMONDS, CardRank.ACE)));
        UncoveredCards uncoveredCards2 = new UncoveredCards(Arrays.asList(new Card(CardSuit.CLUBS, CardRank.KING)));
        GameTable gameTable = new GameTable(cardPlayer1, cardPlayer2, uncoveredCards1, uncoveredCards2,
                null, null);

        for(CardSuit cardSuit : CardSuit.values()) {
            for(CardRank cardRank : CardRank.values()) {
                Card card = new Card(cardSuit, cardRank);
                Optional<Move> result1 = moveService.pushCard(cardPlayer1, card, gameTable, uncoveredCards1);
                if( gameStatusDetectorService.canPlayerMakeMove(gameTable, cardPlayer1) &&
                        CardComparison.areCardsNext(card, uncoveredCards1.lookAtFirstCard())) {
                    assertThat(result1.isPresent(), is(true));
                } else {
                    assertThat(result1.isPresent(), is(false));
                }

                Optional<Move> result2 = moveService.pushCard(cardPlayer1, card, gameTable, uncoveredCards2);
                if( gameStatusDetectorService.canPlayerMakeMove(gameTable, cardPlayer1) &&
                        CardComparison.areCardsNext(card, uncoveredCards2.lookAtFirstCard())) {
                    assertThat(result2.isPresent(), is(true));
                } else {
                    assertThat(result2.isPresent(), is(false));
                }

                Optional<Move> result3 = moveService.pushCard(cardPlayer2, card, gameTable, uncoveredCards1);
                if( gameStatusDetectorService.canPlayerMakeMove(gameTable, cardPlayer2) &&
                        CardComparison.areCardsNext(card, uncoveredCards1.lookAtFirstCard())) {
                    assertThat(result3.isPresent(), is(true));
                } else {
                    assertThat(result3.isPresent(), is(false));
                }

                Optional<Move> result4 = moveService.pushCard(cardPlayer2, card, gameTable, uncoveredCards2);
                if( gameStatusDetectorService.canPlayerMakeMove(gameTable, cardPlayer2) &&
                        CardComparison.areCardsNext(card, uncoveredCards2.lookAtFirstCard())) {
                    assertThat(result4.isPresent(), is(true));
                } else {
                    assertThat(result4.isPresent(), is(false));
                }
            }
        }
    }
}
