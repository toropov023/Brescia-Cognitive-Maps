package ca.toropov.research;

import ca.toropov.research.task.LocationGatheringTask;
import ca.toropov.research.task.Task;
import ca.toropov.research.util.Scheduler;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Author: toropov
 * Date: 2/20/2019
 */
public class TaskLayout extends GridPane {
    private final VBox instructions = new VBox();
    private final StackPane taskPane = new StackPane();
    private Task currentTask;

    public TaskLayout() {
        setHgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        getColumnConstraints().addAll(col1, col2);

        Scheduler.runOnMainThread(() -> {
            double height = getScene().getHeight();
            RowConstraints row1 = new RowConstraints();
            RowConstraints row2 = new RowConstraints();
            if (height > 300) {
                row1.setPrefHeight(height - 80);
                row2.setPrefHeight(80);
            } else {
                row1.setPercentHeight(90);
                row2.setPercentHeight(10);
            }
            getRowConstraints().addAll(row1, row2);
        });

        JFXButton nextTaskButton = new JFXButton("Next Task");
        nextTaskButton.setButtonType(JFXButton.ButtonType.RAISED);
        nextTaskButton.getStyleClass().add("nextTaskButton");
        nextTaskButton.setOnMouseClicked(event -> nextTask());

        StackPane nextTaskPane = new StackPane(nextTaskButton);
        nextTaskPane.setAlignment(Pos.CENTER);

        add(nextTaskPane, 0, 1);

        instructions.setSpacing(5);
        instructions.setPadding(new Insets(20));
        instructions.getStyleClass().add("instructionsContainer");
        add(instructions, 0, 0);

        taskPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        taskPane.setPadding(new Insets(10));
        add(taskPane, 1, 0, 1, 2);

        Scheduler.runOnMainThread(() -> Scheduler.runOnMainThread(() -> {
            currentTask = new LocationGatheringTask();
            currentTask.addInstructions(instructions, (int) (instructions.getWidth() - 20));
            currentTask.addTask(taskPane);
        }));
    }

    private void nextTask() {
        if (currentTask != null && currentTask.canGoNext()) {
            currentTask.save();
            currentTask = currentTask.getNextTask();

            instructions.getChildren().clear();
            taskPane.getChildren().clear();
            if (currentTask != null) {
                currentTask.addInstructions(instructions, (int) (instructions.getWidth() - 20));
                currentTask.addTask(taskPane);
            }
        }
    }
}
