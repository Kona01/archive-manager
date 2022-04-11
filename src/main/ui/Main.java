package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// This method references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Main {
    public static void main(String[] args) {
        LoadScreen ls = new LoadScreen();
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ls.dispose();
                try {
                    new ArchiveApp();
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to run application: file not found");
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
