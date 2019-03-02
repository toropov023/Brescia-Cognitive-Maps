package ca.toropov.research.task;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Author: toropov
 * Date: 2/20/2019
 */
public abstract class Task {
    public Task() {

    }

    public abstract void addInstructions(VBox pane, int wrapWidth);

    public abstract void addTask(Pane pane);

    public abstract boolean canGoNext();

    public abstract boolean canGoPrevious();

    public abstract boolean isPreviousDisabled();

    public abstract void save();

    public abstract Task getNextTask();

    public Text formatTitle(String text, int wrapWidth) {
        return formatText(text, true, wrapWidth);
    }

    public Text formatText(String text, int wrapWidth) {
        return formatText(text, false, wrapWidth);
    }

    private Text formatText(String msg, boolean title, int wrapWidth) {
        Text text = new Text(msg);
        if (title) {
            text.getStyleClass().add("title");
        }
        text.setWrappingWidth(wrapWidth);

        return text;
    }
}
