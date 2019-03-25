package ca.toropov.research.util;

import ca.toropov.research.data.Location;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Author: toropov
 * Date: 3/24/2019
 */
public class GoogleMapsAPI {
    private static GoogleMapsAPI i;
    private final String apiKey;

    private GoogleMapsAPI() {
        apiKey = CredentialsFile.getI().getGoogleKey();
    }

    public static GoogleMapsAPI getI() {
        if (i == null) {
            i = new GoogleMapsAPI();
        }

        return i;
    }

    public void searchLocation(String input, Consumer<List<Location>> consumer) {
        callQuery(GoogleMapsAPI.getI().generateQuery(ApiType.PLACE_SEARCH)
                        .param("input", input)
                        .param("location", "42.981331,-81.246717") //TODO lookup location by ip so to allow this software to be used anywhere
                        .param("radius", "20000"),
                jsonObject -> {
                    List<Location> list = new ArrayList<>();
                    JsonArray results = jsonObject.getArray("results");
                    for (int i = 0; i < results.size(); i++) {
                        JsonObject json = results.getObject(i);

                        Location location = new Location();
                        location.setName(json.getString("name"));
                        location.setAddress(json.getString("formatted_address"));

                        JsonObject locObject = json.getObject("geometry").getObject("location");
                        location.setLatitude(locObject.getDouble("lat"));
                        location.setLongitude(locObject.getDouble("lng"));

                        list.add(location);
                    }

                    System.out.println(list);

                    consumer.accept(list);
                });
    }

    private void callQuery(QueryGenerator generator, Consumer<JsonObject> consumer) {
        callQuery(generator.toString(), consumer);
    }

    private void callQuery(String query, Consumer<JsonObject> consumer) {
        Scheduler.run(() -> {
            try {
                URL url = new URL(query);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                JsonObject object = JsonParser.object().from(connection.getInputStream());
                connection.disconnect();

                Scheduler.runOnMainThread(() -> consumer.accept(object));
            } catch (IOException | JsonParserException e) {
                e.printStackTrace();
                Scheduler.runOnMainThread(() -> consumer.accept(null));
            }
        });
    }

    private QueryGenerator generateQuery(ApiType type) {
        return new QueryGenerator(type);
    }

    @RequiredArgsConstructor
    public enum ApiType {
        /**
         * Searches the full address of a place that best matches the input parameter
         */
        PLACE_SPECIFIC("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"),
        /**
         * Searches up to 20 places in the nearby location based on the input string
         */
        PLACE_SEARCH("https://maps.googleapis.com/maps/api/place/textsearch/json?"),
        /**
         * Convert from address to long,lat and vice versa
         */
        GEOCODING("https://maps.googleapis.com/maps/api/geocode/json?");

        private final String url;
    }

    public class QueryGenerator {
        private final StringBuilder builder;

        public QueryGenerator(ApiType apiType) {
            builder = new StringBuilder(apiType.url);
        }

        public QueryGenerator param(String key, String value) {
            try {
                builder.append(key).append("=");
                builder.append(URLEncoder.encode(value, "UTF-8"));
                builder.append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return this;
        }

        @Override
        public String toString() {
            builder.append("key=").append(apiKey);

            return builder.toString();
        }
    }
}
