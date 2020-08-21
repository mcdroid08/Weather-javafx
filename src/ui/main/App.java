package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        primaryStage.setTitle("Weather v0.1");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void run(String[] args) {
        launch(args);
    }
}
