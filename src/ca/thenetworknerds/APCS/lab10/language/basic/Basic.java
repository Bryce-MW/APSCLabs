package ca.thenetworknerds.APCS.lab10.language.basic;

import ca.thenetworknerds.APCS.lab10.language.Language;

import java.awt.*;

public class Basic extends Language {
    public Basic() {
        extension = "basic";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        g2d.fillRect(startX + 10, startY + 10, 30, 30);
    }
}
