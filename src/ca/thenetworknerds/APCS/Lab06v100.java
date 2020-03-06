// Lab06v100.java
// This is the 100 point version.
// Bryce Wilson
// April 28, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import javax.swing.*;
import java.awt.*;

public class Lab06v100 {
    public static void main(String[] args) {
        Runnable r = () -> {
            JFrame f = new JFrame("Lab 6: 100 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Lab06v100Panel panel = new Lab06v100Panel();
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 824);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}

class Lab06v100Panel extends JPanel {
    // Half X = 800
    // Half Y = 400
    public void paint(Graphics g) {
        g.drawLine(800, 0, 800, 800);
        g.drawLine(0, 400, 1600, 400);

        for (int i = 0; i < 100; i++) {
            g.setColor(new Color((int) (Math.random() * 16777216)));
            g.drawLine((int) (Math.random() * 801), (int) (Math.random() * 401), (int) (Math.random() * 801), (int) (Math.random() * 401));

            g.setColor(new Color((int) (Math.random() * 16777216)));
            g.fillRect((int) (800 + Math.random() * 801), (int) (Math.random() * 351), 50, 50);

            g.setColor(new Color((int) (Math.random() * 16777216)));
            int x = (int) (Math.random() * 801);
            int y = (int) (400 + Math.random() * 351);
            int size = 200;
            if (800-x < size) {
                size = 800-x;
            }
            if (800-y < size) {
                size = 800-y;
            }
            size = (int) (Math.random() * size + 1);
            g.drawOval(x, y, size, size);
        }

        // 900, 500

        // 900, 500
        // 950, 500
        // 950, 520
        // 920, 520
        int[] yellowX = {900, 950, 950, 920};
        int[] yellowY = {500, 500, 520, 520};

        // 950, 500
        // 970, 520
        // 950, 520
        int[] blueX = {950, 970, 950};
        int[] blueY = {500, 520, 520};

        // 900, 500
        // 920, 520
        // 920, 570
        // 900, 550
        int[] greenX = {900, 920, 920, 900};
        int[] greenY = {500, 520, 570, 550};

        // 920, 520
        // 970, 520
        // 970, 570
        // 920, 570
        int[] redX = {920, 970, 970, 920};
        int[] redY = {520, 520, 570, 570};


        g.setColor(Color.YELLOW);
        g.fillPolygon(yellowX, yellowY, 4);
        g.setColor(Color.BLUE);
        g.fillPolygon(blueX, blueY, 3);
        g.setColor(Color.GREEN);
        g.fillPolygon(greenX, greenY, 4);
        g.setColor(Color.RED);
        g.fillPolygon(redX, redY, 4);
    }
}
