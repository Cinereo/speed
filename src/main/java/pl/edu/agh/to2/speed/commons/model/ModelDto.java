package pl.edu.agh.to2.speed.commons.model;


import java.io.Serializable;
import java.util.List;

public class ModelDto implements Serializable {
    private PlayerDto player1;
    private PlayerDto player2;

    private List<CardDto> commonCoveredCards1;
    private List<CardDto> commonCoveredCards2;

    private List<CardDto> uncoveredCards1;
    private List<CardDto> uncoveredCards2;

    public PlayerDto getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerDto player1) {
        this.player1 = player1;
    }

    public PlayerDto getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerDto player2) {
        this.player2 = player2;
    }

    public List<CardDto> getCommonCoveredCards1() {
        return commonCoveredCards1;
    }

    public void setCommonCoveredCards1(List<CardDto> commonCoveredCards1) {
        this.commonCoveredCards1 = commonCoveredCards1;
    }

    public List<CardDto> getCommonCoveredCards2() {
        return commonCoveredCards2;
    }

    public void setCommonCoveredCards2(List<CardDto> commonCoveredCards2) {
        this.commonCoveredCards2 = commonCoveredCards2;
    }

    public List<CardDto> getUncoveredCards1() {
        return uncoveredCards1;
    }

    public void setUncoveredCards1(List<CardDto> uncoveredCards1) {
        this.uncoveredCards1 = uncoveredCards1;
    }

    public List<CardDto> getUncoveredCards2() {
        return uncoveredCards2;
    }

    public void setUncoveredCards2(List<CardDto> uncoveredCards2) {
        this.uncoveredCards2 = uncoveredCards2;
    }
}
