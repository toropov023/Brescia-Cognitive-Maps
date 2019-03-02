package ca.toropov.research;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
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
    public JFXTextField participantId;

    @FXML
    public void initialize() {
        participantId.getValidators().add(new RequiredFieldValidator("Participant id is required"));
        participantId.setOnAction(event -> checkParticipantId());
        startButton.setOnMouseClicked(event -> checkParticipantId());
    }

    private void checkParticipantId() {
        participantId.validate();
        if (participantId.getValidators().stream().noneMatch(ValidatorBase::getHasErrors)) {
            openFullScreen();
        }
    }

    private void openFullScreen() {
        Scene scene = new Scene(new TaskLayout());
        scene.getStylesheets().add("/css/main.css");
        Main.primaryStage.setScene(scene);

        Main.primaryStage.setFullScreenExitHint("");
        Main.primaryStage.setFullScreen(true);
    }
}
