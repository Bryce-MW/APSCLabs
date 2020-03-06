// Lab17bv100.java
// This is the 100 point version.
// Bryce Wilson
// May 24, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import javax.swing.*;
import java.awt.*;

public class Lab17bv100 {

    public static void main(String[] args) throws InterruptedException {
        Lab17bv100Frame panel = new Lab17bv100Frame();
        Runnable r = () -> {
            JFrame f = new JFrame("Lab 17: 100 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            panel.setBackground(Color.WHITE);
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 824);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
        int current = 0;
        int max = 10;
        while (true) {
            panel.maxIter = current++ % max;
            panel.repaint();
            Thread.sleep(100);
        }
    }
}

class Lab17bv100Frame extends JPanel {
    public int maxIter = 10;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawSquare1(g2d, (int) g2d.getClipBounds().getWidth(), (int) g2d.getClipBounds().getHeight(), 0, 0, 0, maxIter);
    }

    public void drawSquare1(Graphics2D g2d, int maxX, int maxY, int startX, int startY, int mask, int iter) {
        int midX = maxX / 2;
        int midY = maxY / 2;
        int startWidth = maxX / 3;
        int startHeight = maxY / 3;
        int tlX = midX - (startWidth / 2);
        int tlY = midY - (startHeight / 2);
        g2d.fillRect(startX + tlX, startY + tlY, startWidth, startHeight);
        if (maxX > 0 && maxY > 0 && iter > 0) {
            g2d.setColor(Color.RED);
            if (mask != 1) drawSquare1(g2d, ((maxX) / 2), ((maxY) / 2), startX, startY, 4, iter - 1);
            g2d.setColor(Color.BLUE);
            if (mask != 2) drawSquare1(g2d, ((maxX) / 2), ((maxY) / 2), startX + midX, startY, 3, iter - 1);
            g2d.setColor(Color.GREEN);
            if (mask != 3) drawSquare1(g2d, ((maxX) / 2), ((maxY) / 2), startX, startY + midY, 2, iter - 1);
            g2d.setColor(Color.YELLOW);
            if (mask != 4) drawSquare1(g2d, ((maxX) / 2), ((maxY) / 2), startX + midX, startY + midY, 1, iter - 1);
        }
    }
}
