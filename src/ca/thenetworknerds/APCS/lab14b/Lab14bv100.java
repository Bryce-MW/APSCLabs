// Lab03v100.java
// This is the 100 point version.
// Bryce Wilson
// April 19, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS.lab14b;

import javax.swing.*;
import java.awt.*;


public class Lab14bv100 {

    public static void main(String[] args) {
        Runnable r = () -> {
            JFrame f = new JFrame("Lab 6: 100 Point Version");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel() {
                Train train = new Train(100, 300);
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    Graphics2D g2d = (Graphics2D) g;
                    train.showCars(g2d);
                }

                JPanel setTrain() {
                    train.addCar(new Locomotive());
                    train.addCar(new PassengerCar());
                    train.addCar(new PassengerCar());
                    train.addCar(new FreightCar());
                    train.addCar(new PassengerCar(), 3);
                    train.addCar(new FreightCar());
                    train.addCar(new Caboose());
                    train.addCar(new FreightCar(), 6);
                    return this;
                }
            }.setTrain();
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 822);
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}

