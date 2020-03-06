package ca.thenetworknerds.APCS.lab10.language.c;

import java.awt.*;

public class Java extends C {
    public Java() {
        extension = "java";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        g2d.drawString("J", startX + 25 - (int) (g2d.getFontMetrics().getStringBounds("J", g2d).getWidth() / 2),
                startY + 25 + g2d.getFontMetrics().getHeight() / 2);
    }
}
