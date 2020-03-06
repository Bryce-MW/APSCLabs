package ca.thenetworknerds.APCS.lab10.language.c;

import java.awt.*;

public class Jython extends Java {
    public Jython() {
        extension = "py";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        super.paintIcon(g2d, startX, startY);
        g2d.drawString("y", startX + 25 + (int) (g2d.getFontMetrics().getStringBounds("J", g2d).getWidth() / 2),
                startY + 25 + g2d.getFontMetrics().getHeight() / 2);
    }
}
