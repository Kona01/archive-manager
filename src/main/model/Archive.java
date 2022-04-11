package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents the highest level of organization, a list of collections
public class Archive {

    private List<Collection> archive;

    // MODIFIES: this
    // EFFECTS: creates a new archive with empty list of collections
    public Archive() {
        archive = new ArrayList<>();
    }

    public ArrayList<Collection> getArchive() {
        return (ArrayList<Collection>) archive;
    }

    // MODIFIES: this
    // EFFECTS: adds given collection to archive
    public void addCollection(Collection c) {
        archive.add(c);
    }

    // REQUIRES: archive contains an entry with given index
    // EFFECTS: returns collection in archive at given index
    public Collection getCollectionByIndex(int i) {
        return archive.get(i);
    }

    // EFFECTS: stores the fields of the archive as a JSON object
    // This method references code from this repo
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("collections", collectionsToJson());
        return json;
    }

    // EFFECTS: stores the collections in archive as a JSON array
    // This method references code from this repo
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray collectionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Collection c : archive) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

}
