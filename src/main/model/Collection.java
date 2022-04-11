package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

// Represents a named collection of performances
public class Collection {

    private String name;
    private List<Performance> data;

    // MODIFIES: this
    // EFFECTS: creates a new collection with a name and empty performance list
    public Collection(String n) {
        name = n;
        data = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Created new " + n + " collection."));
    }

    // MODIFIES: this
    // EFFECTS: adds given performance to collection
    public void addPerformance(Performance p) {
        data.add(p);
        EventLog.getInstance().logEvent(new Event("Added " + p.getName() + " to " + name + " collection."));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Performance> getList() {
        return (ArrayList<Performance>) data;
    }

    // EFFECTS: returns a list of all performances in the collection matching the given year. If no matches are found
    //          (including the case where a negative integer is passed), returns an empty list.
    public ArrayList<Performance> searchByYear(int y) {
        ArrayList<Performance> result = new ArrayList<>();
        for (Performance next : data) {
            if (next.getYear() == y) {
                result.add(next);
            }
        }
        return result;
    }

    // EFFECTS: returns a list of all performances in the collection matching the given name.
    //          If no matches are found, returns an empty list.
    public ArrayList<Performance> searchByName(String n) {
        ArrayList<Performance> result = new ArrayList<>();
        for (Performance next : data) {
            String nm = next.getName();
            if (Objects.equals(nm.toLowerCase(), n.toLowerCase())) {
                result.add(next);
            }
        }
        return result;
    }

    // EFFECTS: returns a list of all performances in the collection matching the given event.
    //          If no matches are found, returns an empty list.
    public ArrayList<Performance> searchByEvent(String e) {
        ArrayList<Performance> result = new ArrayList<>();
        for (Performance next : data) {
            String ev = next.getEvent();
            if (Objects.equals(ev.toLowerCase(), e.toLowerCase())) {
                result.add(next);
            }
        }
        return result;
    }

    // EFFECTS: returns a list of all performances in [min, max]. If min or max are set to -1, respective bound of range
    //          is ignored. If no matches are found (including the case where a negative integer i != -1 is passed),
    //          returns an empty list.
    public ArrayList<Performance> filterByYear(int min, int max) {
        ArrayList<Performance> result = new ArrayList<>();
        for (Performance next : data) {
            if (min == -1 && max == -1) {
                return (ArrayList<Performance>) data;
            } else if (min == -1) {
                if (next.getYear() <= max) {
                    result.add(next);
                }
            } else if (max == -1) {
                if (min <= next.getYear()) {
                    result.add(next);
                }
            } else {
                if (min <= next.getYear() && next.getYear() <= max) {
                    result.add(next);
                }
            }
        }
        return result;
    }

    // EFFECTS: stores the fields of the collection as a JSON object
    // This method references code from this repo
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("performances", performancesToJson());
        return json;
    }

    // EFFECTS: stores the performances in data as a JSON array
    // This method references code from this repo
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray performancesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Performance p : data) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}
