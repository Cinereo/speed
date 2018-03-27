package pl.edu.agh.to2.speed.commons.controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import pl.edu.agh.to2.speed.commons.converters.ModelConverter;
import pl.edu.agh.to2.speed.commons.converters.MoveConverter;
import pl.edu.agh.to2.speed.commons.helpers.AppContext;
import pl.edu.agh.to2.speed.commons.model.PlayersStatus;
import pl.edu.agh.to2.speed.game.controllers.ModelControllerImplementation;
import pl.edu.agh.to2.speed.game.guice.GameModule;
import pl.edu.agh.to2.speed.game.model.CardPlayer;
import pl.edu.agh.to2.speed.game.model.GameTable;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.Hand;
import pl.edu.agh.to2.speed.game.model.UncoveredCards;
import pl.edu.agh.to2.speed.game.service.GameStatusDetectorService;
import pl.edu.agh.to2.speed.game.service.InitializationService;
import pl.edu.agh.to2.speed.game.service.MoveService;
import pl.edu.agh.to2.speed.game.service.TakeCardService;
import pl.edu.agh.to2.speed.net.ConnectionModule;
import pl.edu.agh.to2.speed.net.services.facade.GameFacade;

import java.util.List;

public class PlayerControllers {

    @Inject
    private GameStatusDetectorService gameStatusDetectorService;

    @Inject
    private MoveService moveService;

    @Inject
    private TakeCardService takeCardService;

    @Inject
    private InitializationService initializationService;

    private GameFacade gameFacade;
    private GameTable gameTable;

    public PlayerControllers(){
        initializeGameFacade();
    }

    public void initializeTable() {
        if (AppContext.isHost) {
            // this model won't be part of a game.
            // It's only initialized to display sth to host as long as client isn't connected
            gameTable = initializationService.getInitializedTable();
        } else {
            gameTable = initializationService.getInitializedTable();
            gameFacade.sendModel(ModelConverter.getModelDto(gameTable));
        }
    }

    public List<Card> getMyHandAsList() {
        return getMyHand().getCards();
    }

    public List<Card> getCommonUncoveredCards1() {
        return gameTable.getUncoveredCards1().getCards();
    }

    public List<Card> getCommonUncoveredCards2() {
        return gameTable.getUncoveredCards2().getCards();
    }

    public PlayersStatus getCurrentPlayerStatus() {
        switch (gameStatusDetectorService.gameWinner(gameTable)) {
            case PLAYER_1:
                if (AppContext.isHost) {
                    return PlayersStatus.WINNER;
                } else {
                    return PlayersStatus.LOSER;
                }

            case PLAYER_2:
                if (AppContext.isHost) {
                    return PlayersStatus.LOSER;
                } else {
                    return PlayersStatus.WINNER;
                }

            case NO_WINNER:
                return PlayersStatus.NO_RESULT;

            case DRAW:
                return PlayersStatus.DRAW;

            default:
                throw new IllegalStateException("Player status not valid");
        }
    }

    public boolean putCardOnTheTable(int cardNumber, int commonCardsNumber) {
        CardPlayer askingPlayer = getAskingPlayer();

        Card card = getMyHand().lookAtCard(cardNumber);
        UncoveredCards uncoveredCards = getCommonUncoveredCardsByNumber(commonCardsNumber);

        return moveService.pushCard(askingPlayer, card, gameTable, uncoveredCards).map(
                move -> {
                        gameFacade.sendMove(MoveConverter.toDto(move));
                        return true;
                }).orElse(false);
    }

    public boolean takeCard() {
        CardPlayer player = getAskingPlayer();
        if (takeCardService.takeCard(player)) {
            gameFacade.sendModel(ModelConverter.getModelDto(gameTable));
            return true;
        }
        return false;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    private Hand getMyHand() {
        if (AppContext.isHost) {
            return gameTable.getCardPlayer1().getHand();
        } else {
            return gameTable.getCardPlayer2().getHand();
        }
    }

    private CardPlayer getAskingPlayer() {
        return AppContext.isHost ? gameTable.getCardPlayer1() : gameTable.getCardPlayer2();
    }

    private UncoveredCards getCommonUncoveredCardsByNumber(int index) {
        switch (index) {
            case 1:
                return gameTable.getUncoveredCards1();
            case 2:
                return gameTable.getUncoveredCards2();
            default:
                throw new IllegalArgumentException("Index should be 1 or 2 value");
        }
    }

    private void initializeGameFacade() {
        try {
            Injector injector = Guice.createInjector(new GameModule());
            ModelControllerImplementation modelControllerImplementation = injector.getInstance(ModelControllerImplementation.class);
            modelControllerImplementation.setPlayerControllers(this);
            ConnectionModule.modelController = modelControllerImplementation;
            gameFacade = ConnectionModule.getGameFacade();
        } catch (InterruptedException e) {
            throw new RuntimeException("Cannot reach connection");
        }
    }
}
