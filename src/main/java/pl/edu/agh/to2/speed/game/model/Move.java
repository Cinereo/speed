package pl.edu.agh.to2.speed.game.model;

public class Move {

    private final CardPlayer player;
    private final Card card;
    private final UncoveredCards destination;

    public Move(CardPlayer player, Card card, UncoveredCards destination) {
        this.player = player;
        this.card = card;
        this.destination = destination;
    }

    public CardPlayer getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    public UncoveredCards getDestination() {
        return destination;
    }
}
