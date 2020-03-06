package ca.thenetworknerds.APCS.lab10.language.python.interpreter;

import java.awt.*;

public class PyPy extends Interpreter {
    @Override
    public void printInterpreter(Graphics2D g2d, int middleX, int topY) {
        g2d.drawString("Py", middleX - (int) (g2d.getFontMetrics().getStringBounds("Py", g2d).getWidth() / 2),
                topY + g2d.getFontMetrics().getHeight());
    }
}
