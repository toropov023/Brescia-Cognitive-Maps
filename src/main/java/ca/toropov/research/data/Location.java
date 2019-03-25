package ca.toropov.research.data;

import lombok.Data;

/**
 * Author: toropov
 * Date: 3/24/2019
 */
@Data
public class Location {
    //Retrieved from Google
    private String name;
    private String address;
    private double longitude;
    private double latitude;

    //Defined by user
    private String description;
    private Frequency frequency;
}
