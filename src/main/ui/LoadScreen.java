package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Creates a "loading screen" that is displayed before the main application
public class LoadScreen extends JFrame {

    private static final String IMAGE_SRC = "./data/splashScreen.png";

    // EFFECTS: creates an undecorated window to display splash screen at start
    public LoadScreen() {
        super();
        setUndecorated(true);

        try {
            BufferedImage img = ImageIO.read(new File(IMAGE_SRC));
            add(new JLabel(new ImageIcon(img)));
            setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        } catch (IOException e) {
            add(new JLabel("Error loading splash screen"));
        }

        pack();
        centreOnScreen();
        setVisible(true);
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
