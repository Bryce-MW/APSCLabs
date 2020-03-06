package ca.thenetworknerds.APCS.lab10.language.c;

import ca.thenetworknerds.APCS.lab10.Lab10v110;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Swift extends ObjectiveC {
    private Image icon;

    public Swift() throws IOException {
        this.icon = ImageIO.read(new File(Lab10v110.pathToSwiftIcon));
        extension = "swift";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        g2d.drawImage(this.icon, startX, startY, null);
    }
}
