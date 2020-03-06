package ca.thenetworknerds.APCS.lab14b;

import java.awt.*;


public class Locomotive extends RailCar {

    public Locomotive() {
        super(Color.BLUE);
    }

    public void drawCar(Graphics2D g2d, int startX, int startY) {
        super.drawCar(g2d, startX, startY);
        drawScoop(g2d, startX, startY);
        drawFunnel(g2d, startX, startY);
    }

    private void drawScoop(Graphics2D g2d, int startX, int startY) {
        Polygon scoop = new Polygon();
        scoop.addPoint(startX, startY + 50);
        scoop.addPoint(startX, startY + 100);
        scoop.addPoint(startX - 50, startY + 100);
        g2d.setColor(Color.black);
        g2d.fillPolygon(scoop);
    }

    private void drawFunnel(Graphics2D g2d, int startX, int startY) {
        Polygon funnel = new Polygon();
        funnel.addPoint(startX + 20, startY);
        funnel.addPoint(startX + 20, startY - 30);
        funnel.addPoint(startX, startY - 50);
        funnel.addPoint(startX, startY - 60);
        funnel.addPoint(startX + 60, startY - 60);
        funnel.addPoint(startX + 60, startY - 50);
        funnel.addPoint(startX + 40, startY - 30);
        funnel.addPoint(startX + 40, startY);
        g2d.setColor(Color.black);
        g2d.fillPolygon(funnel);
    }
}

