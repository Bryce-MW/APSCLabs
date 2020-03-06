package ca.thenetworknerds.APCS.lab10.language.c;

import ca.thenetworknerds.APCS.lab10.language.Language;

import java.awt.*;

public class C extends Language {
    public C() {
        extension = "c";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        g2d.drawString("C", startX + 25 - (int) (g2d.getFontMetrics().getStringBounds("C", g2d).getWidth() / 2),
                startY + 25 + g2d.getFontMetrics().getHeight() / 2);
    }
}
