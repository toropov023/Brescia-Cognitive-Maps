package ca.toropov.research.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Author: toropov
 * Date: 3/24/2019
 */
@Getter
@RequiredArgsConstructor
public enum Frequency {
    ONCE("Less than once per week"),
    ONCE_TWO("1-2 times per week"),
    THREE_FOUR("3-4 times per week"),
    FIVE_SIX("5-6 times per week"),
    SIX("More than 6 times per week");

    private final String description;

    public Frequency getByDescription(String desc) {
        for (Frequency frequency : values()) {
            if (frequency.description.equals(desc))
                return frequency;
        }

        return null;
    }
}
