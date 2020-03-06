package ca.thenetworknerds.APCS.lab14b;

import java.awt.*;
import java.util.ArrayList;

public class Train {
    ArrayList<RailCar> cars;
    int startX, startY;

    public Train(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        cars = new ArrayList<>();
    }

    public void addCar(RailCar car) {
        cars.add(car);
    }

    public void addCar(RailCar car, int index) {
        cars.add(index, car);
    }

    public void showCars(Graphics2D g2d) {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).drawCar(g2d, startX + i * 175, startY);
        }
    }
}
