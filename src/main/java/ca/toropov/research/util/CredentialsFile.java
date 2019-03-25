package ca.toropov.research.util;

import ca.toropov.research.Main;
import ca.toropov.research.data.Researcher;
import com.grack.nanojson.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Author: toropov
 * Date: 3/24/2019
 */
public class CredentialsFile {
    private static File file;
    private static JsonObject jsonObject;
    private static CredentialsFile i;

    private CredentialsFile() {
        try {
            file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/CognitiveMaps/credentials.json");

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();

                jsonObject = JsonParser.object().from(Main.class.getResourceAsStream("/credentials.json"));
                Files.write(file.toPath(), JsonWriter.string(jsonObject).getBytes());
            } else {
                jsonObject = JsonParser.object().from(new String(Files.readAllBytes(file.toPath())));
            }
        } catch (IOException | JsonParserException e) {
            e.printStackTrace();
        }
    }

    public String getGoogleKey() {
        return jsonObject.getString("googleAPI");
    }

    public void addResearcher(Researcher researcher) {
        jsonObject.getArray("researchers").add(researcher);
    }

    public boolean hasResearcher(String id) {
        return getResearcher(id) != null;
    }

    public Researcher getResearcher(String id) {
        JsonArray array = jsonObject.getArray("researchers");
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.getObject(i);
            if (object.getString("id").equals(id)) {
                return new Researcher(object);
            }
        }

        return null;
    }

    public static CredentialsFile getI() {
        if (i == null) {
            i = new CredentialsFile();
        }

        return i;
    }
}
