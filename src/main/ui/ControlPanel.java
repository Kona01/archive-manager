package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Control panel for the app GUI, which handles user input from buttons
// This class references code from this website
// Link: https://en.wikipedia.org/wiki/Swing_(Java)
public class ControlPanel extends JPanel {

    private ArchiveApp app;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Integer rowNum;

    // EFFECTS: if b is true, creates archive control panel, if false creates collection control panel
    // This method references code from this website
    // Link: https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
    public ControlPanel(ArchiveApp app, Boolean b) {
        this.app = app;
        setPreferredSize(new Dimension(ArchiveApp.WIDTH / 3, ArchiveApp.HEIGHT));
        setBackground(new Color(141, 213, 213));
        setLayout(new GridBagLayout());

        constraints.gridx = 0;
        rowNum = 0;
        constraints.gridy = rowNum;
        rowNum++;
        add(new JLabel("Select an action:"), constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        if (b) {
            archivePanel();
        } else {
            collectionPanel();
        }
    }

    // EFFECTS: creates a control panel with the default archive options upon start
    private void archivePanel() {
        makeCreateButton();
        makeSelectButton();
        makeLoadButton();
        makeSaveButton();
    }

    // EFFECTS: creates a control panel with options relating to a selected collection
    private void collectionPanel() {
        makeAddButton();
        makeViewButton();
        makeYearSearchButton();
        makeNameSearchButton();
        makeEventSearchButton();
        makeFilterButton();
        makeBackButton();

    }

    // EFFECTS: makes a button for the user to create a new collection
    private void makeCreateButton() {
        JButton b = new JButton("Create collection");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.createCollection();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to select a collection
    private void makeSelectButton() {
        JButton b = new JButton("Select collection");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.selectCollection();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to load an archive
    private void makeLoadButton() {
        JButton b = new JButton("Load archive");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.loadArchive();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to quit the archive
    private void makeSaveButton() {
        JButton b = new JButton("Save");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.saveArchive();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to add a performance to the collection
    private void makeAddButton() {
        JButton b = new JButton("Add performance");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.doAddPerformance();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to view all performances in the collection
    private void makeViewButton() {
        JButton b = new JButton("View performances");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showAllPerformances();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to search performances by year
    private void makeYearSearchButton() {
        JButton b = new JButton("Search by year");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.doSearchByYear();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to search performances by name
    private void makeNameSearchButton() {
        JButton b = new JButton("Search by name");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.doSearchByName();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to search performances by event
    private void makeEventSearchButton() {
        JButton b = new JButton("Search by event");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.doSearchByEvent();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to filter performances by year range
    private void makeFilterButton() {
        JButton b = new JButton("Filter by year range");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.doFilterByYear();
            }
        });
        addButton(b);
    }

    // EFFECTS: makes a button for the user to go back to the archive menu
    private void makeBackButton() {
        JButton b = new JButton("Back");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.goBack();
            }
        });
        addButton(b);
    }

    // This method references code from this website
    // Link: https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
    private void addButton(JButton b) {
        constraints.gridy = rowNum;
        rowNum++;
        add(b, constraints);
    }

}
