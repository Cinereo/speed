package pl.edu.agh.to2.speed.commons.converters;

import pl.edu.agh.to2.speed.commons.model.CardDto;
import pl.edu.agh.to2.speed.game.model.CardSet;
import pl.edu.agh.to2.speed.game.model.CoveredCards;
import pl.edu.agh.to2.speed.game.model.Hand;
import pl.edu.agh.to2.speed.game.model.UncoveredCards;

import java.util.List;
import java.util.stream.Collectors;

public class CardSetConverter {
    public static List<CardDto> getListCardDtoFromCardSet(CardSet cardSet){
        return cardSet.getCards().stream()
                                 .map(CardConverter::toDto)
                                 .collect(Collectors.toList());
    }

    public static Hand getHand(List<CardDto> handDto) {
        return new Hand(handDto.stream()
                               .map(CardConverter::fromDto)
                               .collect(Collectors.toList()));
    }

    public static CoveredCards getCoveredCards(List<CardDto> coveredCardsDto) {
        return new CoveredCards(coveredCardsDto.stream()
                                               .map(CardConverter::fromDto)
                                               .collect(Collectors.toList()));
    }

    public static UncoveredCards getUncoveredCards(List<CardDto> uncoveredCardsDto) {
        return new UncoveredCards(uncoveredCardsDto.stream()
                                                   .map(CardConverter::fromDto)
                                                   .collect(Collectors.toList()));
    }
}
