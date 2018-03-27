package pl.edu.agh.to2.speed.commons.converters;


import pl.edu.agh.to2.speed.commons.model.CardDto;
import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.PlayerDto;
import pl.edu.agh.to2.speed.game.model.GameTable;

import java.util.List;

public class ModelConverter{
    public static ModelDto getModelDto(GameTable gameTable){
        ModelDto modelDto = new ModelDto();

        List<CardDto> commonCoveredCards1 = CardSetConverter.getListCardDtoFromCardSet(gameTable.getCommonCoveredCards1());
        modelDto.setCommonCoveredCards1(commonCoveredCards1);

        List<CardDto> commonCoveredCards2 = CardSetConverter.getListCardDtoFromCardSet(gameTable.getCommonCoveredCards2());
        modelDto.setCommonCoveredCards2(commonCoveredCards2);

        List<CardDto> uncoveredCards1 = CardSetConverter.getListCardDtoFromCardSet(gameTable.getUncoveredCards1());
        modelDto.setUncoveredCards1(uncoveredCards1);

        List<CardDto> uncoveredCards2 = CardSetConverter.getListCardDtoFromCardSet(gameTable.getUncoveredCards2());
        modelDto.setUncoveredCards2(uncoveredCards2);

        PlayerDto player1 = PlayerConverter.toDto(gameTable.getCardPlayer1());
        modelDto.setPlayer1(player1);

        PlayerDto player2 = PlayerConverter.toDto(gameTable.getCardPlayer2());
        modelDto.setPlayer2(player2);

        return modelDto;
    }

    public static GameTable getGameTable(ModelDto modelDto) {
        GameTable gameTable = new GameTable();
        gameTable.setCardPlayer1(PlayerConverter.fromDto(modelDto.getPlayer1()));
        gameTable.setCardPlayer2(PlayerConverter.fromDto(modelDto.getPlayer2()));
        gameTable.setUncoveredCards1(CardSetConverter.getUncoveredCards(modelDto.getUncoveredCards1()));
        gameTable.setUncoveredCards2(CardSetConverter.getUncoveredCards(modelDto.getUncoveredCards2()));
        gameTable.setCommonCoveredCards1(CardSetConverter.getCoveredCards(modelDto.getCommonCoveredCards1()));
        gameTable.setCommonCoveredCards2(CardSetConverter.getCoveredCards(modelDto.getCommonCoveredCards2()));
        return gameTable;
    }

}
