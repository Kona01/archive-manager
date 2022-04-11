package persistence;

import model.Archive;
import model.Collection;
import model.Performance;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the archive from JSON data stored in file
// This class references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the given source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads archive from file and returns it; throws IOException
    //          if an error occurs reading the data
    public Archive read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseArchive(jsonObject);
    }

    // EFFECTS: reads source file and returns it as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses archive from JSON object and returns it
    private Archive parseArchive(JSONObject jsonObject) {
        Archive ar = new Archive();
        addCollections(ar, jsonObject);
        return ar;
    }

    // MODIFIES: ar
    // EFFECTS: parses collections from JSON object and adds them to archive
    private void addCollections(Archive ar, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("collections");
        for (Object json : jsonArray) {
            JSONObject nextCollection = (JSONObject) json;
            addCollection(ar, nextCollection);
        }
    }

    // MODIFIES: ar
    // EFFECTS: parses collection from JSON object and adds it to archive
    private void addCollection(Archive ar, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Collection co = new Collection(name);
        addPerformances(co, jsonObject);
        ar.addCollection(co);
    }

    // MODIFIES: co
    // EFFECTS: parses performances from JSON object and adds them to collection
    private void addPerformances(Collection co, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("performances");
        for (Object json : jsonArray) {
            JSONObject nextPerformance = (JSONObject) json;
            addPerformance(co, nextPerformance);
        }
    }

    // MODIFIES: co
    // EFFECTS: parses performance from JSON object and adds it to collection
    private void addPerformance(Collection co, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int year = jsonObject.getInt("year");
        String event = jsonObject.getString("event");
        Performance performance = new Performance(name,year,event);
        co.addPerformance(performance);
    }

}
