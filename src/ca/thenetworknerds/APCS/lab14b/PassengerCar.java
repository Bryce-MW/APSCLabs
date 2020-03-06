package ca.thenetworknerds.APCS.lab14b;

import java.awt.*;

class PassengerCar extends RailCar {

    public PassengerCar() {
        super(Color.GRAY);
    }

    public void drawCar(Graphics2D g2d, int startX, int startY) {
        super.drawCar(g2d, startX, startY);
        drawWindows(g2d, startX, startY);
        drawRoof(g2d, startX, startY);
    }

    private void drawWindows(Graphics2D g2d, int startX, int startY) {
        g2d.setColor(Color.white);
        g2d.fillRect(startX + 10, startY + 30, 25, 30);
        g2d.fillRect(startX + 45, startY + 30, 25, 30);
        g2d.fillRect(startX + 80, startY + 30, 25, 30);
        g2d.fillRect(startX + 115, startY + 30, 25, 30);
    }

    private void drawRoof(Graphics2D g2d, int startX, int startY) {
        Polygon roof = new Polygon();
        roof.addPoint(startX - 15, startY + 20);
        roof.addPoint(startX, startY);
        roof.addPoint(startX + 150, startY);
        roof.addPoint(startX + 165, startY + 20);
        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(roof);
    }
}
	