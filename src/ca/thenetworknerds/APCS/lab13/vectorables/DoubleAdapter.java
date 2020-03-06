package ca.thenetworknerds.APCS.lab13.vectorables;

import ca.thenetworknerds.APCS.lab13.operations.*;

public class DoubleAdapter extends Number implements
        Addable<DoubleAdapter>, Subtractable<DoubleAdapter>,
        Multiplicable<DoubleAdapter>, Dividable<DoubleAdapter>, FourFunctionVectorable<DoubleAdapter> {
    private double number;

    public DoubleAdapter() {
        number = 0;
    }
    public DoubleAdapter(double number) {
        this.number = number;
    }

    @Override
    public DoubleAdapter subtract(DoubleAdapter other) {
        return new DoubleAdapter(number - other.number);
    }

    @Override
    public DoubleAdapter add(DoubleAdapter other) {
        return new DoubleAdapter(number + other.number);
    }

    @Override
    public DoubleAdapter divide(DoubleAdapter other) {
        return new DoubleAdapter(number / other.number);
    }

    @Override
    public DoubleAdapter multiply(DoubleAdapter other) {
        return new DoubleAdapter(number * other.number);
    }

    @Override
    public String getNature() {
        return "Number";
    }

    public DoubleAdapter createRandomValue() {
        return new DoubleAdapter(Math.random() * 5);
    }

    @Override
    public int getPrintHeight() {
        return 1;
    }

    @Override
    public int getPrintWidth() {
        return Double.toString(number).length();
    }

    @Override
    public String toString() {
        return Double.toString(number);
    }
}
