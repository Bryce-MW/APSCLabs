package ca.thenetworknerds.APCS;

import javax.swing.*;
import java.awt.*;

public class Lab14av100 {
    public static void main(String[] args) {
        Runnable r = () -> {
            JFrame f = new JFrame("Lab 6: 100 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JPanel panel = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawRect(0,0,800,400);
                    g2d.drawRect(800,0,800,400);
                    g2d.drawRect(0,400,800,400);
                    g2d.drawRect(800,400,800,400);

                    Shape[] shapes = {new Polygon(4, "Square"),
                                      new Polygon(3, "Triangle"),
                                      new Polygon(8, "Octagon"),
                                      new Circle()};

                    shapes[0].drawAll(g2d, 0  , 0  , 800, 400);
                    shapes[1].drawAll(g2d, 800, 0  , 800, 400);
                    shapes[2].drawAll(g2d, 0  , 400, 800, 400);
                    shapes[3].drawAll(g2d, 800, 400, 800, 400);
                }
            };
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 822);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}

interface Shape {
    void drawShape(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY);
    void drawName(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY);
    void drawSidesLabel(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY);

    default void drawAll(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        this.drawShape(g2d, startX, startY, sizeX, sizeY);
        this.drawName(g2d, startX, startY, sizeX, sizeY);
        this.drawSidesLabel(g2d, startX, startY, sizeX, sizeY);
    }
}

class Circle implements Shape {

    @Override
    public void drawShape(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        double size = Math.min(sizeX / 3, sizeY / 3);
        g2d.fillOval((int) (startX + sizeX / 2 - size), (int) (startY + sizeY / 2 - size), (int) (2 * size), (int) (2 * size));
    }

    @Override
    public void drawName(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        g2d.drawString(" Circle", startX, startY + g2d.getFontMetrics().getHeight());
    }

    @Override
    public void drawSidesLabel(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        String label = "The Circle has \u221E sides.";
        g2d.drawString(label,
                (float) startX + (float) sizeX / 2F - (float) g2d.getFontMetrics().getStringBounds(label, g2d).getWidth() / 2F,
                (float) startY + (float) sizeY / 2F + Math.min((float) sizeX / 3F, (float) sizeY / 3F) + (float) g2d.getFontMetrics().getHeight());
    }
}

class Polygon implements Shape {
    private int numSides;
    private String name;

    Polygon(int sides, String name) {
        this.numSides = sides;
        this.name = " " + name;
    }

    @Override
    public void drawShape(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        double size = Math.min(sizeX / 3, sizeY / 3);
        double centerX = startX + sizeX / 2;
        double centerY = startY + sizeY / 2;
        double angle = (2 * Math.PI) / this.numSides;

        for (int i = 0; i < this.numSides; i++) {
            g2d.fillPolygon(new int[] {(int) centerX, (int) (centerX + size * Math.cos(angle * i)), (int) (centerX + size * Math.cos(angle * (i + 1)))},
                            new int[] {(int) centerY, (int) (centerY + size * Math.sin(angle * i)), (int) (centerY + size * Math.sin(angle * (i + 1)))}, 3);
        }
    }

    @Override
    public void drawName(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        g2d.drawString(this.name, startX, startY + g2d.getFontMetrics().getHeight());
    }

    @Override
    public void drawSidesLabel(Graphics2D g2d, int startX, int startY, int sizeX, int sizeY) {
        String label = "The " + this.name + " has " + this.numSides + " sides.";
        g2d.drawString(label,
                (float) startX + (float) sizeX / 2F - (float) g2d.getFontMetrics().getStringBounds(label, g2d).getWidth() / 2F,
                (float) startY + (float) sizeY / 2F + Math.min((float) sizeX / 3F, (float) sizeY / 3F) + (float) g2d.getFontMetrics().getHeight());
    }
}