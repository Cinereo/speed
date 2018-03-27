package pl.edu.agh.to2.speed.game.model;

public class CardPlayer {

    private Hand hand;
    private CoveredCards playersCoveredCards;

    public CardPlayer() {
        this(new Hand(), new CoveredCards());
    }

    public CardPlayer(Hand hand, CoveredCards playersCoveredCards) {
        this.hand = hand;
        this.playersCoveredCards = playersCoveredCards;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public CoveredCards getPlayersCoveredCards() {
        return playersCoveredCards;
    }

    public void setPlayersCoveredCards(CoveredCards playersCoveredCards) {
        this.playersCoveredCards = playersCoveredCards;
    }

    public boolean isPlayerOutOfCards(){
        return hand.isEmpty() && playersCoveredCards.isEmpty();
    }
}
