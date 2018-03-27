package pl.edu.agh.to2.speed.commons.converters;

import pl.edu.agh.to2.speed.commons.model.PlayerDto;
import pl.edu.agh.to2.speed.game.model.CardPlayer;

public class PlayerConverter {
    public static CardPlayer fromDto(PlayerDto playerDto) {
        return new CardPlayer(CardSetConverter.getHand(playerDto.hand),
                CardSetConverter.getCoveredCards(playerDto.coveredCards));
    }

    public static PlayerDto toDto(CardPlayer cardPlayer) {
        return new PlayerDto(CardSetConverter.getListCardDtoFromCardSet(cardPlayer.getHand()),
                CardSetConverter.getListCardDtoFromCardSet(cardPlayer.getPlayersCoveredCards()));
    }
}
