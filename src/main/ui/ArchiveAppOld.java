package ui;

import model.Archive;
import model.Collection;
import model.Performance;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Archive application
// This class references code from these repos
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class ArchiveAppOld {

    private Archive arc;
    private Collection col;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/archive.json";

    // EFFECTS: runs the archive application
    public ArchiveAppOld() throws FileNotFoundException {
        runArchive();
    }

    // MODIFIES: this
    // EFFECTS: processes user input relating to entire archive
    private void runArchive() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayArchiveMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                checkSave();
                keepGoing = false;
            } else {
                processArchiveCommand(command);
            }
        }
        System.out.println("\nArchive closed.");
    }

    // MODIFIES: this
    // EFFECTS: initializes archive
    private void init() {
        arc = new Archive();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of archive-related options to user
    private void displayArchiveMenu() {
        System.out.println("\nSelect an action:");
        System.out.println("\tc -> Create collection");
        System.out.println("\ts -> Select collection");
        System.out.println("\tl -> Load archive");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command relating to archive
    private void processArchiveCommand(String command) {
        if (command.equals("c")) {
            createCollection();
        } else if (command.equals("s")) {
            selectCollection();
        } else if (command.equals("l")) {
            loadArchive();
        } else {
            System.out.println("Invalid selection");
        }
    }

    // REQUIRES: given name for collection is unique
    // MODIFIES: this
    // EFFECTS: creates a new collection with user-inputted name, and adds it to the archive
    private void createCollection() {
        System.out.println("Enter collection name:");
        String name = input.next();
        Collection c = new Collection(name);
        arc.addCollection(c);
    }

    // REQUIRES: user inputs a string that represents a valid integer, archive not empty
    // EFFECTS: allows the user to select a collection to perform actions on
    private void selectCollection() {
        List<Collection> l = arc.getArchive();
        int length = l.size();
        if (length == 0) {
            System.out.println("No collections to select from.");
            return;
        }
        System.out.println("Enter the number of the collection you wish to select:");
        int i = 1;
        String cn;
        for (Collection next : l) {
            cn = next.getName();
            System.out.println(i + ": " + cn);
            i++;
        }
        int select = input.nextInt();
        if (select > 0 && select <= length) {
            col = arc.getCollectionByIndex(select - 1);
            collectionActions();
        } else {
            System.out.println("Invalid selection");
            this.selectCollection();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input relating to selected collection
    private void collectionActions() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayCollectionMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                processCollectionCommand(command);
            }
        }
    }

    // EFFECTS: displays menu of collection-related options to user
    private void displayCollectionMenu() {
        System.out.println("\nSelect an action:");
        System.out.println("\ta -> Add performance");
        System.out.println("\tv -> View performances");
        System.out.println("\ty -> Search by year");
        System.out.println("\tn -> Search by name");
        System.out.println("\te -> Search by event");
        System.out.println("\tf -> Filter by year range");
        System.out.println("\tb -> Back");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command relating to collection
    private void processCollectionCommand(String command) {
        if (command.equals("a")) {
            doAddPerformance();
        } else if (command.equals("v")) {
            showAllPerformances();
        } else if (command.equals("y")) {
            doSearchByYear();
        } else if (command.equals("n")) {
            doSearchByName();
        } else if (command.equals("e")) {
            doSearchByEvent();
        } else if (command.equals("f")) {
            doFilterByYear();
        } else {
            System.out.println("Invalid selection");
        }
    }

    // REQUIRES: user inputs a string that represents a valid integer for year
    // MODIFIES: this
    // EFFECTS: creates a new performance, and adds it to the selected collection
    private void doAddPerformance() {
        System.out.println("Enter performance name:");
        String name = input.next();
        System.out.println("Enter performance year:");
        String year = input.next();
        System.out.println("Enter performance event:");
        String event = input.next();
        Performance p = new Performance(name,Integer.parseInt(year),event);
        col.addPerformance(p);
    }

    // EFFECTS: displays all performances in the selected collection
    private void showAllPerformances() {
        List<Performance> l = col.getList();
        showPerformances(l);
    }

    // EFFECTS: displays the performances in the given list in a way that can be read by the user
    private void showPerformances(List<Performance> l) {
        List<String> result = new ArrayList<>();
        for (Performance next : l) {
            String entry = showPerformance(next);
            result.add(entry);
        }
        System.out.println(result);
    }

    // EFFECTS: returns the information of a performance as a single string which can be read by the user
    private String showPerformance(Performance p) {
        String n = p.getName();
        int y = p.getYear();
        String e = p.getEvent();
        return "(Name: " + n + ", Year: " + y + ", Event: " + e + ")";
    }

    // REQUIRES: user inputs a string that represents a valid integer
    // EFFECTS: allows user to specify parameter for searchByYear, and outputs result
    private void doSearchByYear() {
        System.out.println("Enter search year:");
        String year = input.next();
        List<Performance> results = col.searchByYear(Integer.parseInt(year));
        showPerformances(results);
    }

    // EFFECTS: allows user to specify parameter for searchByName, and outputs result
    private void doSearchByName() {
        System.out.println("Enter search name:");
        String name = input.next();
        List<Performance> results = col.searchByName(name);
        showPerformances(results);
    }

    // EFFECTS: allows user to specify parameter for searchByEvent, and outputs result
    private void doSearchByEvent() {
        System.out.println("Enter search event:");
        String event = input.next();
        List<Performance> results = col.searchByEvent(event);
        showPerformances(results);
    }

    // REQUIRES: user inputs a string that represents a valid integer
    // EFFECTS: allows user to specify parameter for filterByYear, and outputs result
    private void doFilterByYear() {
        System.out.println("Enter minimum year (input -1 for no lower bound):");
        String min = input.next();
        System.out.println("Enter maximum year (input -1 for no upper bound):");
        String max = input.next();
        List<Performance> results = col.filterByYear(Integer.parseInt(min),Integer.parseInt(max));
        showPerformances(results);
    }

    // EFFECTS: asks the user if they want to save the archive to file
    private void checkSave() {
        System.out.println("Would you like to save this archive?");
        System.out.println("y -> Yes");
        System.out.println("n -> No");
        String answer = input.next();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            saveArchive();
        }
    }

    // EFFECTS: saves the archive to file
    private void saveArchive() {
        try {
            jsonWriter.open();
            jsonWriter.write(arc);
            jsonWriter.close();
            System.out.println("The archive has been saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads archive from file
    private void loadArchive() {
        try {
            arc = jsonReader.read();
            System.out.println("Loaded archive from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
