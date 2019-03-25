package ca.toropov.research.data;

import com.grack.nanojson.JsonObject;
import lombok.Value;

/**
 * Author: toropov
 * Date: 3/24/2019
 */
@Value
public class Researcher {
    private final String id;
    private final String firstName;
    private final String lastName;

    public Researcher(JsonObject jsonObject) {
        id = jsonObject.getString("id");
        firstName = jsonObject.getString("firstName");
        lastName = jsonObject.getString("lastName");
    }
}
