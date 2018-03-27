package pl.edu.agh.to2.speed.game.controllers;

import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;

public interface ModelController {
    void updateModel(MoveDto move);

    void updateModel(ModelDto model);
}
