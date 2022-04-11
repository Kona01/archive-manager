package ui;

import model.Archive;
import model.Collection;
import model.Event;
import model.EventLog;
import model.Performance;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Archive application
// This class references code from these repos
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class ArchiveApp extends JFrame {

    private Archive arc;
    private Collection selected;
    private ControlPanel cp;
    private DisplayPanel dp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String WINDOW_TITLE = "Performance Archive";
    public static final String JSON_STORE = "./data/archive.json";

    // EFFECTS: runs the archive application
    public ArchiveApp() throws FileNotFoundException {
        super(WINDOW_TITLE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog log = EventLog.getInstance();
                for (Event next : log) {
                    System.out.println(next.toString());
                }
            }
        });

        add(cp, BorderLayout.WEST);
        add(dp, BorderLayout.EAST);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes archive
    private void init() {
        arc = new Archive();
        cp = new ControlPanel(this,true);
        dp = new DisplayPanel();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: creates a new collection with user-inputted name, and adds it to the archive
    public void createCollection() {
        String name = getInput("Enter collection name:");
        if (name != null && name.length() > 0) {
            Collection c = new Collection(name);
            arc.addCollection(c);
            dp.addMessage(name + " collection created");
        } else {
            dp.addMessage("No collection created");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to select a collection to perform actions on
    public void selectCollection() {
        List<Collection> l = arc.getArchive();
        int length = l.size();
        if (length == 0) {
            dp.addMessage("No collections to select from");
            return;
        }
        dp.divide();
        int i = 1;
        for (Collection next : l) {
            dp.addMessage(i + ": " + next.getName());
            i++;
        }
        String num = getInput("Enter the number of the collection you wish to select:");
        if (num != null && num.length() > 0) {
            int select = Integer.parseInt(num);
            if (select > 0 && select <= length) {
                selected = arc.getCollectionByIndex(select - 1);
                updateControlPanel(false);
                dp.addMessage("Selected collection: " + selected.getName());
            } else {
                dp.addMessage("Invalid selection");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads archive from file
    public void loadArchive() {
        try {
            arc = jsonReader.read();
            dp.addMessage("Loaded archive from " + JSON_STORE);
        } catch (IOException e) {
            dp.addMessage("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the archive to file
    public void saveArchive() {
        try {
            jsonWriter.open();
            jsonWriter.write(arc);
            jsonWriter.close();
            dp.addMessage("The archive has been saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            dp.addMessage("Unable to write to file: " + JSON_STORE);
        }
    }

    // REQUIRES: user inputs a string that represents a valid integer for year
    // MODIFIES: this
    // EFFECTS: creates a new performance, and adds it to the selected collection
    public void doAddPerformance() {
        String name = getInput("Enter performance name:");
        String year = getInput("Enter performance year:");
        String event = getInput("Enter performance event:");
        Performance p = new Performance(name,Integer.parseInt(year),event);
        selected.addPerformance(p);
        dp.addMessage("Performance added");
    }

    // EFFECTS: displays all performances in the selected collection
    public void showAllPerformances() {
        List<Performance> l = selected.getList();
        showPerformances(l);
    }

    // EFFECTS: displays the performances in the given list
    private void showPerformances(List<Performance> l) {
        dp.divide();
        if (l.size() == 0) {
            dp.addMessage("No performances");
        }
        for (Performance next : l) {
            String entry = showPerformance(next);
            dp.addMessage(entry);
        }
    }

    // EFFECTS: returns the information of a performance as a single string which can be read by the user
    private String showPerformance(Performance p) {
        String n = p.getName();
        int y = p.getYear();
        String e = p.getEvent();
        return "Name: " + n + ", Year: " + y + ", Event: " + e;
    }

    // REQUIRES: user inputs a string that represents a valid integer
    // EFFECTS: allows user to specify parameter for searchByYear, and outputs result
    public void doSearchByYear() {
        String year = getInput("Enter search year:");
        List<Performance> results = selected.searchByYear(Integer.parseInt(year));
        showPerformances(results);
    }

    // EFFECTS: allows user to specify parameter for searchByName, and outputs result
    public void doSearchByName() {
        String name = getInput("Enter search name:");
        List<Performance> results = selected.searchByName(name);
        showPerformances(results);
    }

    // EFFECTS: allows user to specify parameter for searchByEvent, and outputs result
    public void doSearchByEvent() {
        String event = getInput("Enter search event:");
        List<Performance> results = selected.searchByEvent(event);
        showPerformances(results);
    }

    // REQUIRES: user inputs a string that represents a valid integer
    // EFFECTS: allows user to specify parameter for filterByYear, and outputs result
    public void doFilterByYear() {
        String min = getInput("Enter minimum year (input -1 for no lower bound):");
        String max = getInput("Enter maximum year (input -1 for no upper bound):");
        List<Performance> results = selected.filterByYear(Integer.parseInt(min),Integer.parseInt(max));
        showPerformances(results);
    }

    // EFFECTS: switches control panel back to initial archive options
    public void goBack() {
        updateControlPanel(true);
    }

    // EFFECTS: collects input from user
    // This method references code from this website
    // Link: https://www.tutorialspoint.com/swingexamples/show_input_dialog_text.htm
    private String getInput(String prompt) {
        JFrame inputBox = new JFrame(WINDOW_TITLE);
        return (String) JOptionPane.showInputDialog(inputBox, prompt, WINDOW_TITLE, JOptionPane.PLAIN_MESSAGE,
                null, null, null);
    }

    // MODIFIES: this
    // EFFECTS: updates which set of options are displayed on the control panel
    private void updateControlPanel(Boolean b) {
        remove(cp);
        cp = new ControlPanel(this,b);
        add(cp, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: frame location is set so that it is centred on desktop
    // This method references code from this repo
    // Link: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}
