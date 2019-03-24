package ca.toropov.research.task;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: toropov
 * Date: 2/26/2019
 */
public class LocationGatheringTask extends Task {
    private List<Row> fields = new ArrayList<>();

    @Override
    public void addInstructions(VBox vBox, int wrapWidth) {
        String title = "Task 1: Location Gathering";
        String text = "    Please, fill out the locations of places within London that you go to frequently in daily life. " +
                "These locations could include but are not limited to: a campus building, a home/residence building, your workplace, a mall, a gym, a grocery store, or other places you frequently visit.\n" +
                "   Please, fill out minimum 4 locations you frequently visit; however, you are encouraged to provide as many locations as you feel, with a maximum of 10 locations.\n" +
                "If you have any questions do not hesitate to ask the researcher.";

        vBox.getChildren().addAll(formatTitle(title, wrapWidth), formatText(text, wrapWidth));
    }

    @Override
    public void addTask(StackPane pane) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setPrefSize(pane.getWidth() - 40, pane.getHeight() - 20);
        gridPane.setPadding(new Insets(10, 20, 10, 20));
        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(15),
                new ColumnConstraints(200),
                new ColumnConstraints(pane.getWidth() - 510),
                new ColumnConstraints(200)
        );
        pane.getChildren().add(gridPane);

        double rowHeight = pane.getHeight() >= 550 ? 50 : pane.getHeight() / 11;

        addLine(gridPane, 0, rowHeight, pane.getWidth() - 55, rowHeight);
        addLine(gridPane, 22.5, 0, 22.5, rowHeight * 11);
        addLine(gridPane, 237.5, 0, 237.5, rowHeight * 11);
        addLine(gridPane, 252.5 + pane.getWidth() - 510, 0, 252.5 + pane.getWidth() - 510, rowHeight * 11);

        for (int i = 0; i < 11; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
            generateRow(gridPane, i);
        }
    }

    private void addLine(GridPane pane, double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.LIGHTGRAY);
        pane.add(new Pane(line), 0, 0);
    }

    private void generateRow(GridPane grid, int i) {
        if (i == 0) {
            Label index = new Label("#");
            index.setFont(Font.font(18));
            GridPane.setHalignment(index, HPos.CENTER);

            Label location = new Label("Location/Address");
            location.setFont(Font.font(18));
            GridPane.setHalignment(location, HPos.CENTER);

            Label description = new Label("Description");
            description.setFont(Font.font(18));

            Label frequency = new Label("Frequency");
            frequency.setFont(Font.font(18));
            GridPane.setHalignment(frequency, HPos.CENTER);

            grid.add(index, 0, i);
            grid.add(location, 1, i);
            grid.add(description, 2, i);
            grid.add(frequency, 3, i);
        } else {
            Label index = new Label(i + "");
            GridPane.setHalignment(index, HPos.RIGHT);

            JFXTextField location = new JFXTextField();
            location.setPromptText("Type in location...");
            if (i < 5) {
                location.getValidators().add(new RequiredFieldValidator("Location is required"));
            }

            JFXTextField description = new JFXTextField();
            description.setPromptText("Type in description...");
            if (i < 5) {
                description.getValidators().add(new RequiredFieldValidator("Description is required"));
            }

            ObservableList<String> choices = FXCollections.observableArrayList();
            choices.add("Less than once per week");
            choices.add("1-2 times per week");
            choices.add("3-4 times per week");
            choices.add("5-6 times per week");
            choices.add("More than 6 times per week");
            JFXComboBox<String> frequency = new JFXComboBox<>(choices);
            frequency.setPromptText("Click to select...");

            if (i < 5) {
                frequency.getValidators().add(new RequiredFieldValidator("Please select one"));
            }

            grid.add(index, 0, i);
            grid.add(location, 1, i);
            grid.add(description, 2, i);
            grid.add(frequency, 3, i);

            fields.add(new Row(i, location, description, frequency));
        }
    }

    @Override
    public boolean canGoNext() {
        return fields.stream().allMatch(Row::isValid);
    }

    @Override
    public boolean canGoPrevious() {
        return false;
    }

    @Override
    public boolean isPreviousDisabled() {
        return true;
    }

    @Override
    public void save() {

    }

    @Override
    public Task getNextTask() {
        return null;
    }

    @Value
    private class Row {
        private final int index;
        private final JFXTextField location;
        private final JFXTextField description;
        private final JFXComboBox<String> frequency;

        private boolean isValid() {
            location.validate();
            description.validate();
            frequency.validate();

            return location.getValidators().stream().noneMatch(ValidatorBase::getHasErrors)
                    && description.getValidators().stream().noneMatch(ValidatorBase::getHasErrors)
                    && frequency.getValidators().stream().noneMatch(ValidatorBase::getHasErrors);
        }
    }
}
