package ca.thenetworknerds.APCS.lab10.language.python;

import ca.thenetworknerds.APCS.lab10.language.Language;
import ca.thenetworknerds.APCS.lab10.language.python.interpreter.Interpreter;

import java.awt.*;

public class Python extends Language {
    private Interpreter interpreter;

    public Python() {
        extension = "py";
    }

    @Override
    public void paintIcon(Graphics2D g2d, int startX, int startY) {
        g2d.drawString("Py", startX + 25 - (int) (g2d.getFontMetrics().getStringBounds("Py", g2d).getWidth() / 2),
                startY + 25 + g2d.getFontMetrics().getHeight() / 2);
        this.interpreter.printInterpreter(g2d, startX + 25, startY + 25 + g2d.getFontMetrics().getHeight() / 2);
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
}
