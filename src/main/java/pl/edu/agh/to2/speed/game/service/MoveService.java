package pl.edu.agh.to2.speed.game.service;

import com.google.inject.Inject;
import pl.edu.agh.to2.speed.game.model.*;

import java.util.Optional;

public class MoveService {

    @Inject
    private GameStatusDetectorService gameStatusDetectorService;

    public Optional<Move> pushCard(GameTable gameTable, Move move){
        return pushCard(move.getPlayer(), move.getCard(), gameTable, move.getDestination());
    }

    public Optional<Move> pushCard(CardPlayer cardPlayer, Card card, GameTable gameTable, UncoveredCards uncoveredCards) {

        if (!this.gameStatusDetectorService.canPlayerMakeMove(gameTable, cardPlayer)) {
            return Optional.empty();
        }

        Card topCard = uncoveredCards.lookAtFirstCard();
        if (CardComparison.areCardsNext(topCard, card)) {
            uncoveredCards.addCard(card);
            return Optional.of(new Move(cardPlayer, card, uncoveredCards));
        }

        return Optional.empty();
    }
}

