package pl.edu.agh.to2.speed.commons.converters;

import pl.edu.agh.to2.speed.commons.model.CardDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;
import pl.edu.agh.to2.speed.commons.model.PlayerDto;
import pl.edu.agh.to2.speed.game.model.Move;

import java.util.List;

public class MoveConverter {
    public static MoveDto toDto(Move move){
        List<CardDto> destination = CardSetConverter.getListCardDtoFromCardSet(move.getDestination());
        PlayerDto playerDto = PlayerConverter.toDto(move.getPlayer());
        CardDto cardDto = CardConverter.toDto(move.getCard());
        return new MoveDto(playerDto, cardDto, destination);
    }

    public static Move fromDto(MoveDto moveDto) {
        return new Move(PlayerConverter.fromDto(moveDto.player),
                CardConverter.fromDto(moveDto.card),
                CardSetConverter.getUncoveredCards(moveDto.destination));
    }
}
