package ca.thenetworknerds.APCS.lab14b;

import java.awt.*;


public class RailCar {
    private Color carColor;

    public RailCar(Color carColor) {
        this.carColor = carColor;
    }

    public void drawCar(Graphics2D g2d, int startX, int startY) {
        drawContainer(g2d, startX, startY);
        drawRailWheels(g2d, startX, startY);
        drawLink(g2d, startX, startY);
    }

    private void drawContainer(Graphics2D g2d, int startX, int startY) {
        g2d.setColor(carColor);
        g2d.fillRect(startX, startY, 150, 100);
    }

    private void drawRailWheels(Graphics2D g2d, int startX, int startY) {
        g2d.setColor(Color.black);
        g2d.fillOval(startX + 5, startY + 75, 50, 50);
        g2d.fillOval(startX + 95, startY + 75, 50, 50);
    }

    private void drawLink(Graphics2D g2d, int startX, int startY) {
        g2d.setColor(Color.black);
        g2d.fillRect(startX - 25, startY + 80, 25, 5);
    }
}

