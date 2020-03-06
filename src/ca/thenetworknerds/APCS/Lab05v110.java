// Lab05v110.java
// This is the 100 point version.
// Bryce Wilson
// April 28, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

// Number: 80
// Y height: 10
// X height: 20

import javax.swing.*;
import java.awt.*;

public class Lab05v110 {
    public static void main(String[] args) {
        Runnable r = () -> {
            JFrame f = new JFrame("Lab 5: 110 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Lab05v110Panel panel = new Lab05v110Panel();
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 800);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}


class Lab05v110Panel extends JPanel {
    public void paint(Graphics g) {
        for (int i = 0; i < 21; i++) {
            g.drawLine(i * 80, 800, 1600, 800 - i * 40);
            g.drawLine(i * 80, 0, 1600, i * 40);
            g.drawLine(i * 80, 0, 0, 800 - i * 40);
            g.drawLine(i * 80, 800, 0, i * 40);

            g.drawLine(400 + (i * 80 >> 1), 600, 1200, 200 + (800 - i * 40 >> 1));
            g.drawLine(400 + (i * 80 >> 1), 200, 1200, 200 + (i * 40 >> 1));
            g.drawLine(400 + (i * 80 >> 1), 200, 400, 200 + (800 - i * 40 >> 1));
            g.drawLine(400 + (i * 80 >> 1), 600, 400, 200 + (i * 40 >> 1));
        }
    }
}
