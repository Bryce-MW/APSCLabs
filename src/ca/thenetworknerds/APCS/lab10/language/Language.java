package ca.thenetworknerds.APCS.lab10.language;

import java.awt.*;

public abstract class Language {
    protected String extension;

    public String getExtension() {
        return this.extension;
    }

    public abstract void paintIcon(Graphics2D g2d, int startX, int startY);
}
