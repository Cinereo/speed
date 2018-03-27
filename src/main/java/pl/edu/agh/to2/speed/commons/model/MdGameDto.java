package pl.edu.agh.to2.speed.commons.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Data transfer object for meta data about game
 */
public class MdGameDto implements Serializable {
    private Date startGame;
    private PlayerInfoDto player1;
    private PlayerInfoDto player2;
}

