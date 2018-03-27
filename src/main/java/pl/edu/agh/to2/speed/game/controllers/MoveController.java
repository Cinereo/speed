package pl.edu.agh.to2.speed.game.controllers;

import pl.edu.agh.to2.speed.commons.model.MoveDto;
import pl.edu.agh.to2.speed.game.model.CardPlayer;

public interface MoveController {
    boolean tryMove(MoveDto move);

    boolean takeCard(CardPlayer cardPlayer);
}
