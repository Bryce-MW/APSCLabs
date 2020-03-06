package ca.thenetworknerds.APCS.lab10.language.basic;

import java.awt.*;

public class VisualBasic extends Basic {
    public VisualBasic() {
        extension = "vbs";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        super.paintIcon(g2d, startX, startY);
        g2d.drawString("V", startX, startY + 25 + g2d.getFontMetrics().getHeight() / 2);
    }
}
