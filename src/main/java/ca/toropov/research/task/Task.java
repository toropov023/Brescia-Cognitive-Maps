package ca.toropov.research.task;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * Author: toropov
 * Date: 2/20/2019
 */
public abstract class Task extends AnchorPane {
    public Task() {

    }

    abstract Node getInstructions();

    abstract Node getTask();

    abstract boolean canGoNext();

    abstract boolean canGoPrevious();

    abstract boolean isPreviousDisabled();

    abstract void onNext();

    abstract void onPrevious();
}
