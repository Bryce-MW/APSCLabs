package ca.thenetworknerds.APCS.lab14b;

import java.awt.*;

public class FreightCar extends RailCar {

    public FreightCar() {
        super(Color.GREEN);
    }

    public void drawCar(Graphics2D g2d, int startX, int startY) {
        super.drawCar(g2d, startX, startY);
        drawFreightDoors(g2d, startX, startY);
    }

    private void drawFreightDoors(Graphics g, int startX, int startY) {
        g.setColor(Color.black);
        g.drawRect(startX + 30, startY + 10, 90, 60);
        g.drawLine(startX + 75, startY + 10, startX + 75, startY + 70);
        g.drawLine(startX + 30, startY + 10, startX + 75, startY + 70);
        g.drawLine(startX + 30, startY + 70, startX + 75, startY + 10);
        g.drawLine(startX + 75, startY + 10, startX + 120, startY + 70);
        g.drawLine(startX + 75, startY + 70, startX + 120, startY + 10);
        g.fillRect(startX + 66, startY + 35, 5, 15);
        g.fillRect(startX + 80, startY + 35, 5, 15);
    }
}

