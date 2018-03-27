package pl.edu.agh.to2.speed.game.guice;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import pl.edu.agh.to2.speed.commons.controller.PlayerControllers;
import pl.edu.agh.to2.speed.game.model.GameTable;
import pl.edu.agh.to2.speed.game.service.*;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GameTable.class).in(Singleton.class);
        bind(ExchangeTopCardsService.class).in(Singleton.class);
        bind(GameStatusDetectorService.class).in(Singleton.class);
        bind(InitializationService.class).in(Singleton.class);
        bind(MoveService.class).in(Singleton.class);
        bind(TakeCardService.class).in(Singleton.class);
        bind(PlayerControllers.class).in(Singleton.class);
    }
}
