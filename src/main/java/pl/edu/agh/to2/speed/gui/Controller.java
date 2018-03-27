package pl.edu.agh.to2.speed.gui;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edu.agh.to2.speed.app.App;
import pl.edu.agh.to2.speed.commons.controller.PlayerControllers;
import pl.edu.agh.to2.speed.game.model.*;
import pl.edu.agh.to2.speed.game.service.InitializationService;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final String title = "SPEED";

    private GameTable controllerTable;
    private CardPlayer player1, player2;
    int clickedCardIndex = -1;
    private boolean[] handCardPresent = {true, true, true, true, true};

    @FXML
    private ImageView uncoveredCard1,
                      uncoveredCard2,
                      handCard1, handCard2, handCard3, handCard4, handCard5,
                      commonDrawStack1,
                      commonDrawStack2;

    @FXML
    private ImageView[] enemyCard = new ImageView[5];

    @FXML
    private ImageView[] handCard = new ImageView[5];

    @FXML
    private Card clickedCard;

    private PlayerControllers playerControllers = new PlayerControllers();

    @FXML
    private List<Card> controllerHand;

    @FXML
    private TextField playerName;

    @FXML
    private Label commonCardCount1,
                  commonCardCount2,
                  timerLabel;

    public void timer(List<Card> cardPlayer1HandCards) { //Countdown function
        final int start = 5;

        JavaFxObservable.interval(new Duration(1000))

                .map(i -> start - i)

                .take(start + 1)

                .observeOn(JavaFxScheduler.platform())

                .doOnNext(time -> updateLabel(time.toString()))

                .doOnComplete(() -> uncoverCards(cardPlayer1HandCards))

                .subscribe();
    }

    public void updateLabel(String text) {
        timerLabel.setText(text);
    }

    public void uncoverCards(List<Card> cardPlayer1HandCards) {
        timerLabel.setVisible(false);
        displayHand(cardPlayer1HandCards);
        CommonDrawController commonDrawController = new CommonDrawController();
        commonDrawController.handleCommonDraw(controllerTable, commonCardCount1, commonCardCount2,
                uncoveredCard1, uncoveredCard2, commonDrawStack1, commonDrawStack2);
    }

    @FXML
    public void startGame(MouseEvent event) {
        ImageView eventSource = (ImageView) event.getSource();
        eventSource.setVisible(false);

        this.player1 = new CardPlayer();
        this.player2 = new CardPlayer();
        InitializationService initializationService = new InitializationService();
        controllerTable = initializationService.getInitializedTable(player1, player2);
        Hand cardPlayer1Hand = player1.getHand();
        List<Card> cardPlayer1HandCards = cardPlayer1Hand.getCards();

        timer(cardPlayer1HandCards);
        controllerTable.getCommonCoveredCards1().getCards().addListener((ListChangeListener<Card>) c -> commonCardCount1.setText(String.valueOf(controllerTable.getCommonCoveredCards1().getCards().size())));
        controllerTable.getCommonCoveredCards2().getCards().addListener((ListChangeListener<Card>) c -> commonCardCount2.setText(String.valueOf(controllerTable.getCommonCoveredCards1().getCards().size())));
    }

    @FXML
    public void initGame() throws IOException {
        if (!playerName.getText().equals("")) {
            Stage stage = (Stage) playerName.getScene().getWindow();
            stage.hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/AppOverviewPane.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();

            primaryStage.setTitle(title);
            primaryStage.setFullScreen(true);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }

    @FXML
    public void displayHand(List<Card> handCards) {
        ImageSetter imageSetter = new ImageSetter();
        int handCardNumber = 0;
        controllerHand = handCards;
        for (Card card : handCards) {
            imageSetter.setImage(card.toString() + ".png", handCard[handCardNumber]);
            handCardNumber++;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handCard[0] = handCard1;
        handCard[1] = handCard2;
        handCard[2] = handCard3;
        handCard[3] = handCard4;
        handCard[4] = handCard5;
    }

    @FXML
    public void handleHandCardClick(MouseEvent event) {
        displayHand(controllerHand);
        ImageView eventSource = (ImageView) event.getSource();
        String eventSourceCardString = eventSource.getId();
        switch (eventSourceCardString) {
            case "handCard1":
                clickedCard = controllerHand.get(0);
                clickedCardIndex = 0;
                break;
            case "handCard2":
                clickedCard = controllerHand.get(1);
                clickedCardIndex = 1;
                break;
            case "handCard3":
                clickedCard = controllerHand.get(2);
                clickedCardIndex = 2;
                break;
            case "handCard4":
                clickedCard = controllerHand.get(3);
                clickedCardIndex = 3;
                break;
            case "handCard5":
                clickedCard = controllerHand.get(4);
                clickedCardIndex = 4;
                break;
            default:
                break;
        }
        URL newPath = getClass().getResource(String.format("/imgs/%s2.png", clickedCard));
        Image image = new Image(newPath.toString());
        eventSource.setImage(image);
    }

    @FXML
    public void handleLeftStackClick(MouseEvent event) {
        if (playerControllers.putCardOnTheTable(clickedCardIndex, 1) && clickedCardIndex != -1) {
            handCard[clickedCardIndex].setVisible(false);
            handCardPresent[clickedCardIndex] = false;
        }
    }

    @FXML
    public void handleRightStackClick(MouseEvent event) {
        if (playerControllers.putCardOnTheTable(clickedCardIndex, 2) && clickedCardIndex != -1) {
            handCard[clickedCardIndex].setVisible(false);
            handCardPresent[clickedCardIndex] = false;
        }
    }

    @FXML
    public void handleCommonDraw() {
        CommonDrawController commonDrawController = new CommonDrawController();
        commonDrawController.handleCommonDraw(controllerTable, commonCardCount1, commonCardCount2,
                uncoveredCard1, uncoveredCard2, commonDrawStack1, commonDrawStack2);
    }

    @FXML
    public void handleDrawClick(MouseEvent event) {
        if (playerControllers.takeCard()) {
            displayHand(player1.getHand().getCards());
        }
    }

    @FXML
    public void switchEnemyHandVisibility(int index, boolean status) {
        enemyCard[index].setVisible(status);
    }
}