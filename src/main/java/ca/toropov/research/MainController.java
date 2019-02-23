package ca.toropov.research;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Author: toropov
 * Date: 2/20/2019
 */
public class MainController {
    @FXML
    public JFXButton startButton;
    @FXML
    public AnchorPane mainPane;

    @FXML
    public void initialize() {
        startButton.setOnMouseClicked(event -> openFullScreen());
    }

    private void openFullScreen() {
        Main.primaryStage.setScene(new Scene(new TaskLayout()));

        Main.primaryStage.setFullScreenExitHint("");
        Main.primaryStage.setFullScreen(true);
    }
}
