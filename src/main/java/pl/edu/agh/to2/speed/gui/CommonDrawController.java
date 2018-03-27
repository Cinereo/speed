package pl.edu.agh.to2.speed.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.speed.game.model.Card;
import pl.edu.agh.to2.speed.game.model.GameTable;


public class CommonDrawController {

    @FXML
    public void handleCommonDraw(GameTable controllerTable, Label commonCardCount1, Label commonCardCount2, ImageView uncoveredCard1, ImageView uncoveredCard2, ImageView commonDrawStack1, ImageView commonDrawStack2) {
        int remainingCards = controllerTable.getCommonCoveredCards1().size();
        if (remainingCards > 0) {
            remainingCards--;
            Card uncCard1 = controllerTable.getCommonCoveredCards1().takeFirstCard();
            ImageSetter imageSetter = new ImageSetter();
            imageSetter.setImage(uncCard1.toString() + ".png", uncoveredCard1);
            Card uncCard2 = controllerTable.getCommonCoveredCards2().takeFirstCard();
            imageSetter.setImage(uncCard2.toString() + ".png", uncoveredCard2);
        }
        if (remainingCards == 0) {
            commonDrawStack1.setVisible(false);
            commonDrawStack2.setVisible(false);
            commonCardCount1.setVisible(false);
            commonCardCount2.setVisible(false);
        }

    }
}
