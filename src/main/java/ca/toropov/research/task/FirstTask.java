package ca.toropov.research.task;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Author: toropov
 * Date: 2/26/2019
 */
public class FirstTask extends Task {
    @Override
    public void addInstructions(VBox vBox, int wrapWidth) {
        String title = "Task 1: Location Gathering";
        String text = "    Please, fill out the locations of places within London that you go to frequently in daily life. " +
                "These locations could include but are not limited to: a campus building, a home/residence building, your workplace, a mall, a gym, a grocery store, or other places you frequently visit.\n" +
                "   Please, fill out minimum 4 locations you frequently visit; however, you are encouraged to provide as many locations as you feel, with a maximum of 10 locations." +
                "If you have any questions do not hesitate to ask the researcher.";

        vBox.getChildren().addAll(formatTitle(title, wrapWidth), formatText(text, wrapWidth));
    }

    @Override
    public void addTask(Pane pane) {
        VBox vBox = new VBox(40);
        for (int i = 0; i < 10; i++) {
            vBox.getChildren().add(createLocation(i));
        }

        StackPane.setAlignment(vBox, Pos.CENTER);
        pane.getChildren().add(vBox);
    }

    private Pane createLocation(int i) {
        GridPane grid = new GridPane();
        grid.add(new Label("#  "), 0, 0);
        grid.add(new Label("Location/Address"), 1, 0);
        grid.add(new Label("Description     "), 2, 0);
        grid.add(new Label("Frequency       "), 3, 0);

        grid.add(new Label((i + 1) + ""), 0, 1);
        grid.add(new JFXTextField(), 1, 1);
        grid.add(new JFXTextField(), 2, 1);

        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.add("Less than once per week");
        choices.add("1-2 times per week");
        choices.add("3-4 times per week");
        choices.add("5-6 times per week");
        choices.add("More than 6 times per week");
        grid.add(new JFXComboBox<>(choices), 3, 1);

        grid.setHgap(15);
        return grid;
    }

    @Override
    public boolean canGoNext() {
        return false;
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
}
