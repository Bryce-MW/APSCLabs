package ca.thenetworknerds.APCS.lab13.vectorables;

import ca.thenetworknerds.APCS.lab13.operations.*;

public class IntegerAdapter extends Number implements
        Addable<IntegerAdapter>, Subtractable<IntegerAdapter>, Multiplicable<IntegerAdapter>,
        Dividable<IntegerAdapter>, FourFunctionVectorable<IntegerAdapter> {
    private int number;

    public IntegerAdapter() {
        number = 0;
    }
    public IntegerAdapter(int number) {
        this.number = number;
    }

    @Override
    public IntegerAdapter subtract(IntegerAdapter other) {
        return new IntegerAdapter(number - other.number);
    }

    @Override
    public IntegerAdapter add(IntegerAdapter other) {
        return new IntegerAdapter(number + other.number);
    }

    @Override
    public IntegerAdapter divide(IntegerAdapter other) {
        return new IntegerAdapter(number / other.number);
    }

    @Override
    public IntegerAdapter multiply(IntegerAdapter other) {
        return new IntegerAdapter(number * other.number);
    }

    @Override
    public String getNature() {
        return "Number";
    }

    public IntegerAdapter createRandomValue() {
        return new IntegerAdapter((int) (Math.random() * 6));
    }

    @Override
    public int getPrintHeight() {
        return 1;
    }

    @Override
    public int getPrintWidth() {
        return Integer.toString(number).length();
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
