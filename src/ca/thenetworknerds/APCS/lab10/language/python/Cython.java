package ca.thenetworknerds.APCS.lab10.language.python;

import ca.thenetworknerds.APCS.lab10.language.python.interpreter.CPython;

import java.awt.*;

public class Cython extends CPython {
    @Override
    public void printInterpreter(Graphics2D g2d, int middleX, int topY) {
        super.printInterpreter(g2d, middleX, topY);
        g2d.drawString("++",
                middleX - (int) (g2d.getFontMetrics().getStringBounds("C", g2d).getWidth() / 2) -
                        (int) g2d.getFontMetrics().getStringBounds("++", g2d).getWidth(),
                topY + g2d.getFontMetrics().getHeight());
    }
}
