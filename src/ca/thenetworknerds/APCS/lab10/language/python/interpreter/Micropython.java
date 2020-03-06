package ca.thenetworknerds.APCS.lab10.language.python.interpreter;

import java.awt.*;

public class Micropython extends Interpreter {
    @Override
    public void printInterpreter(Graphics2D g2d, int middleX, int topY) {
        g2d.drawString("μ",
                middleX - (int) (g2d.getFontMetrics().getStringBounds("Py", g2d).getWidth() / 2) -
                        (int) g2d.getFontMetrics().getStringBounds("μ", g2d).getWidth(),
                topY - g2d.getFontMetrics().getHeight());
    }
}
