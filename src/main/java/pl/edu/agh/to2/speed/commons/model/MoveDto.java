package pl.edu.agh.to2.speed.commons.model;

import java.io.Serializable;
import java.util.List;

public class MoveDto implements Serializable {
    public final PlayerDto player;
    public final CardDto card;
    public final List<CardDto> destination;

    public MoveDto(PlayerDto player, CardDto card, List<CardDto> destination) {
        this.player = player;
        this.card = card;
        this.destination = destination;
    }
}
