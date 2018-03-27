package pl.edu.agh.to2.speed.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.edu.agh.to2.speed.app.App;

public class InitSceneLauncher extends Application{

    public static final String title = "Speed online game";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/InitOverviewPane.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle(title);
        primaryStage.setFullScreen(true);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
