package model;

import org.json.JSONObject;

// Represents a performance having a name, year performed, and event performed at
public class Performance {

    private String name;
    private int year;
    private String event;

    // MODIFIES: this
    // EFFECTS: creates a new performance with given name, year, and event info
    public Performance(String n, int y, String e) {
        name = n;
        year = y;
        event = e;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getEvent() {
        return event;
    }

    // EFFECTS: stores the fields of the performance as a JSON object
    // This method references code from this repo
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("year", year);
        json.put("event", event);
        return json;
    }

}
