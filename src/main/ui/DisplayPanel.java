package ui;

import javax.swing.*;
import java.awt.*;

// Display panel for app GUI, which outputs information to the user
public class DisplayPanel extends JPanel {

    private static final int LABEL_HEIGHT = 21;

    // EFFECTS: claims the right half of the window for the display panel
    public DisplayPanel() {
        setPreferredSize(new Dimension(ArchiveApp.WIDTH * 2 / 3 - 20, ArchiveApp.HEIGHT));
        setBackground(new Color(183, 194, 194));
    }

    // EFFECTS: prints the given string onto the display panel
    public void addMessage(String t) {
        checkScroll();
        JLabel message = new JLabel(t);
        message.setPreferredSize(new Dimension(this.getWidth(), LABEL_HEIGHT));
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message);
        revalidate();
        repaint();
    }

    // EFFECTS: ensures that new messages do not get printed outside the range of the window
    public void checkScroll() {
        Dimension windowDim = getSize();
        double windowHeight = windowDim.getHeight();
        double numLinesFit = windowHeight / (LABEL_HEIGHT + 5);
        int numLines = getComponentCount();
        if (numLines == (int) numLinesFit) {
            remove(0);
            revalidate();
            repaint();
        }
    }

    // EFFECTS: prints a dashed line to separate content in the display panel
    public void divide() {
        addMessage("------------------------------");
    }

}
