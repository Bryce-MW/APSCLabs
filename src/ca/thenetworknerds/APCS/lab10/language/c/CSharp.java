package ca.thenetworknerds.APCS.lab10.language.c;

import java.awt.*;

public class CSharp extends Cpp {
    public CSharp() {
        extension = "cs";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        super.paintIcon(g2d, startX, startY);
        g2d.drawLine(startX, startY + 17, startX + 50, startY + 17);
        g2d.drawLine(startX, startY + 33, startX + 50, startY + 33);
        g2d.drawLine(startX + 17, startY, startX + 17, startY + 50);
        g2d.drawLine(startX + 33, startY, startX + 33, startY + 50);
    }
}
