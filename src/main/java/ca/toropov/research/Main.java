package ca.toropov.research;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Author: toropov
 * Date: 2/20/2019
 */
public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Font.loadFont(Main.class.getResource("/font/roboto/Roboto-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(Main.class.getResource("/font/roboto/Roboto-Italic.ttf").toExternalForm(), 10);
        Font.loadFont(Main.class.getResource("/font/roboto/Roboto-Bold.ttf").toExternalForm(), 10);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        root.getStylesheets().add("/css/main.css");
        primaryStage.setTitle("Cognitive Maps Research");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/icon.png"));

        primaryStage.show();
    }
}
