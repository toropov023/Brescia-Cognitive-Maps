package ca.toropov.research;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Author: toropov
 * Date: 2/20/2019
 */
public class TaskLayout extends GridPane {
    public TaskLayout() {
        JFXButton nextTaskButton = new JFXButton("Next Task");
        nextTaskButton.setButtonType(JFXButton.ButtonType.RAISED);
        nextTaskButton.setRipplerFill(Color.GREEN);
        nextTaskButton.getStyleClass().add("nextTaskButton");

        StackPane nextTaskPane = new StackPane(nextTaskButton);
        nextTaskPane.setAlignment(Pos.CENTER);
        nextTaskPane.setMinHeight(100);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        getColumnConstraints().addAll(col1, col2);

        GridPane.setValignment(nextTaskPane, VPos.BOTTOM);
        GridPane.setHalignment(nextTaskPane, HPos.CENTER);

        add(nextTaskPane, 0, 1);
    }
}
