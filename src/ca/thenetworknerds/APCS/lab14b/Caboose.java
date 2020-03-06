package ca.thenetworknerds.APCS.lab14b;

import java.awt.*;


public class Caboose extends RailCar {

    public Caboose() {
        super(Color.RED);

    }

    public void drawCar(Graphics2D g2d, int startX, int startY) {
        super.drawCar(g2d, startX, startY);
        drawWindows(g2d, startX, startY);
        drawCupola(g2d, startX, startY);
    }

    private void drawWindows(Graphics2D g2d, int startX, int startY) {
        g2d.setColor(Color.white);
        g2d.fillRect(startX + 30, startY + 30, 30, 30);
        g2d.fillRect(startX + 90, startY + 30, 30, 30);
    }

    private void drawCupola(Graphics2D g2d, int startX, int startY) {
        g2d.setColor(Color.red);
        g2d.fillRect(startX + 30, startY - 30, 90, 30);
        g2d.setColor(Color.black);
        g2d.fillRect(startX + 25, startY - 30, 100, 5);
    }
}

