package ca.toropov.research.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: toropov
 * Date: 3/24/2019
 */
@Data
public class Participant {
    private final String id;

    private final List<Location> locations = new ArrayList<>();
//    private
}
