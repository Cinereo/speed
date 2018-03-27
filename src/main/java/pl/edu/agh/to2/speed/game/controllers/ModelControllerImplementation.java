package pl.edu.agh.to2.speed.game.controllers;

import com.google.inject.Inject;
import pl.edu.agh.to2.speed.commons.controller.PlayerControllers;
import pl.edu.agh.to2.speed.commons.converters.ModelConverter;
import pl.edu.agh.to2.speed.commons.converters.MoveConverter;
import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;
import pl.edu.agh.to2.speed.game.guice.GameModule;
import pl.edu.agh.to2.speed.game.model.GameTable;
import pl.edu.agh.to2.speed.game.model.Move;
import pl.edu.agh.to2.speed.game.service.MoveService;

import java.util.Optional;

public class ModelControllerImplementation implements ModelController{

    @Inject
    private MoveService moveService;

    private Optional<PlayerControllers> playerControllers = Optional.empty();

    @Override
    public void updateModel(MoveDto moveDto) {
        if(playerControllers.isPresent()) {
            Move move = MoveConverter.fromDto(moveDto);
            GameTable gameTable = playerControllers.get().getGameTable();
            moveService.pushCard(gameTable, move);
        }
        else {
            throw new IllegalStateException("Player Controller was not provided");
        }
    }

    @Override
    public void updateModel(ModelDto model) {
        if(playerControllers.isPresent()) {
            playerControllers.get().setGameTable(ModelConverter.getGameTable(model));
        }
        else {
            throw new IllegalStateException("Player Controller was not provided");
        }
    }

    public void setPlayerControllers(PlayerControllers playerControllers) {
        this.playerControllers = Optional.of(playerControllers);
    }
}
