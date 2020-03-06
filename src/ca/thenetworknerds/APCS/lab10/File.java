package ca.thenetworknerds.APCS.lab10;

import ca.thenetworknerds.APCS.lab10.language.Language;

import java.awt.*;

public class File {
    private Language language;
    private String name;

    public File(String name, Language language) {
        this.name = name;
        this.language = language;
    }

    public void paintFile(Graphics2D g2d, int startX, int startY) {
        g2d.drawRect(startX, startY, 50, 50);
        this.language.paintIcon(g2d, startX, startY);
        String fullName = this.name + "." + this.language.getExtension();
        double length = g2d.getFontMetrics().getStringBounds(fullName, g2d).getWidth();
        g2d.drawString(fullName, (startX + 25) - (int) (length / 2), startY + 50 + g2d.getFontMetrics().getHeight());
    }

    public Language getLanguage() {
        return this.language;
    }
}
