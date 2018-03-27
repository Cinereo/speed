package pl.edu.agh.to2.speed.game.service;


import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;
import pl.edu.agh.to2.speed.game.guice.GameModule;
import pl.edu.agh.to2.speed.game.model.CardPlayer;
import pl.edu.agh.to2.speed.game.model.GameTable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class InitializationServiceTest {

    private Injector injector = Guice.createInjector(new GameModule());
    private InitializationService initializationService = injector.getInstance(InitializationService.class);

    @Test
    public void initializationSizeTest(){
        // given
        CardPlayer player1 = new CardPlayer();
        CardPlayer player2 = new CardPlayer();

        // when
        GameTable table = initializationService.getInitializedTable(player1, player2);

        // then
        assertThat(player1.getHand().size(), is(5));
        assertThat(player2.getHand().size(), is(5));

        assertThat(player1.getPlayersCoveredCards().size(), is(16));
        assertThat(player2.getPlayersCoveredCards().size(), is(16));

        assertThat(table.getCommonCoveredCards1().size(), is(5));
        assertThat(table.getCommonCoveredCards2().size(), is(5));

        assertThat(table.getDeck().getCards().size(), is(0));

        assertThat(table.getUncoveredCards1().size(), is(0));
        assertThat(table.getUncoveredCards2().size(), is(0));
    }
}
