// Lab10v110.java
// This is the 110 point version.
// Bryce Wilson
// May 6, 2019
// APCS, Mr. Robinson
// You might have to update pathToSwiftIcon based on where the icon is if your folder structure is different
// Maybe lab10/language/c/swift.png depending on where your cwd is

/*
Trying to do this accurately is basically impossible and needs multiple inheritance because not everything is as simple
as C++ being a superset of C or Cython being a superset of CPython which is an implementation of Python.
Just look at C#s info:

Major implementations
Visual C#, .NET Framework, Mono, .NET Core, DotGNU (discontinued), Universal Windows Platform

Dialects
Cω, Spec#, Polyphonic C#, Enhanced C#

Influenced by
C++, Cω, Eiffel, F#,[a], Haskell, Icon, J#, J++, Java, ML, Modula-3, Object Pascal,[8] Rust, VB

Influenced
Chapel, Clojure, Crystal, D, J#, Dart, F#, Hack, Java, Kotlin, Monkey, Nemerle, Oxygene, Ring, Rust, Swift, Vala, TypeScript

 */

package ca.thenetworknerds.APCS.lab10;

import ca.thenetworknerds.APCS.lab10.language.*;
import ca.thenetworknerds.APCS.lab10.language.basic.*;
import ca.thenetworknerds.APCS.lab10.language.c.*;
import ca.thenetworknerds.APCS.lab10.language.python.*;
import ca.thenetworknerds.APCS.lab10.language.python.interpreter.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Lab10v110 {
    public static final String pathToSwiftIcon = "src/ca/thenetworknerds/APCS/lab10/language/c/swift.png";

    private static final Class[] classes = {Basic.class, VisualBasic.class, C.class, Cpp.class, CSharp.class, Java.class,
        Jython.class, ObjectiveC.class, Swift.class, Python.class};
    private  static final Class[] pythonInterpreters = {CPython.class, Micropython.class, PyPy.class, Cython.class};

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        File[] files = new File[200];

        for (int i = 0; i < files.length; i++) {
            files[i] = new File("test", (Language) Lab10v110.classes[(int) (Math.random() * Lab10v110.classes.length)].newInstance());
            if (files[i].getLanguage() instanceof Python) {
                ((Python) files[i].getLanguage()).setInterpreter((Interpreter) Lab10v110.pythonInterpreters[(int) (Math.random() * Lab10v110.pythonInterpreters.length)].newInstance());
            }
        }

        Runnable r = () -> {
            JFrame f = new JFrame("Lab 10: 110 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Lab10v110Panel panel = new Lab10v110Panel(files);
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 824);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}

class Lab10v110Panel extends JPanel {
    private File[] files;

    Lab10v110Panel(File[] files) {
        this.files = files;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int y = 8;
        int total = 0;
        while (total < this.files.length) {
            try {
                for (int i = 0; i < 25; i++) {
                    this.files[total++].paintFile(g2d, i * 64 + 8, y);
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            y += 70;
        }
    }
}
