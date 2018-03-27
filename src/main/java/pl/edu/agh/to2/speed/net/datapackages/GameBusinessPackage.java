package pl.edu.agh.to2.speed.net.datapackages;

import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;

import java.io.Serializable;

/**
 * Class for representing a network package contains a business information about game
 */
public class GameBusinessPackage extends AbstractPackage implements Serializable {

    public enum GamePackageType {
        MODEL, MOVE
    }

    public GameBusinessPackage(ModelDto modelDto, MoveDto moveDto, GamePackageType type) {
        this.modelDto = modelDto;
        this.moveDto = moveDto;
        this.type = type;
    }

    private final ModelDto modelDto;

    private final MoveDto moveDto;

    public ModelDto getModelDto() {
        return modelDto;
    }


    public MoveDto getMoveDto() {
        return moveDto;
    }

    public GamePackageType getType() {
        return type;
    }

    private final GamePackageType type;


}
