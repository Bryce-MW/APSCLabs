package ca.thenetworknerds.APCS.lab13.vectorables;

import ca.thenetworknerds.APCS.lab13.operations.Addable;
import ca.thenetworknerds.APCS.lab13.operations.Subtractable;
import ca.thenetworknerds.APCS.lab13.operations.triVector.MathVectorable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class MathVector<T extends FourFunctionVectorable<T>> extends ExampleVector implements Addable<MathVector<T>>, Subtractable<MathVector<T>>, MathVectorable<T> {
    protected ArrayList<T> vector;

    public MathVector() {}
    public MathVector(ArrayList<T> numbers) {
        vector = numbers;
    }

    private void checkLength(MathVector<T> other) {
        if (vector.size() != other.vector.size()) {
            throw new IllegalArgumentException("Both MathVectors must have the same size when subtracting");
        }
    }

    @Override
    public MathVector<T> subtract(MathVector<T> other) {
        checkLength(other);
        ArrayList<T> result = new ArrayList<>(vector.size());
        for (int i = 0; i < vector.size(); i++) {
            result.add(vector.get(i).subtract(other.vector.get(i)));
        }
        return new MathVector<>(result);
    }

    @Override
    public MathVector<T> add(MathVector<T> other) {
        checkLength(other);
        ArrayList<T> result = new ArrayList<>(vector.size());
        for (int i = 0; i < vector.size(); i++) {
            result.add(vector.get(i).add(other.vector.get(i)));
        }
        return new MathVector<>(result);
    }

    @Override
    public T squareDistance() {
        if (vector.size() == 1) {
            return vector.get(0);
        }
        T total = vector.get(0).add(vector.get(0));
        for (int i = 2; i < vector.size(); i++) {
            total = total.add(vector.get(i));
        }
        return total;
    }

    @Override
    public String getNature() {
        return "Multi-Number container";
    }

    @Override
    public MathVector<IntegerAdapter> createRandomValue() {
        ArrayList<IntegerAdapter> values = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            values.add(new IntegerAdapter((int) (Math.random() * 5)));
        }
        return new MathVector<>(values);
    }

    @Override
    public int getPrintHeight() {
        try {
            return vector.size() * vector.stream().max(Comparator.comparingInt(Vectorable::getPrintHeight)).get().toString().length() + 2;
        } catch (NoSuchElementException e) {
            return vector.size();
        }
    }

    @Override
    public int getPrintWidth() {
        try {
            return vector.stream().max(Comparator.comparingInt(left -> left.toString().length())).get().toString().length() + 2;
        } catch (NoSuchElementException e) {
            return 3;
        }
    }

    @Override
    public String toString() {
        int width = getPrintWidth();
        ArrayList<StringBuilder> representation = new ArrayList<>();
        for (T item :
                vector) {
            String[] itemString = item.toString().split("\\R", -1);
            Arrays.stream(itemString).map(StringBuilder::new).forEach(representation::add);
        }
        for (StringBuilder item :
                representation) {
            item.insert(0, '\u23A2');
            item.append(" ".repeat(width - item.length() - 1));
            item.append('\u23A5');
        }
        StringBuilder specialCase = representation.get(0);
        specialCase.setCharAt(0, '\u23A1');
        specialCase.setCharAt(specialCase.length() - 1, '\u23A4');
        specialCase = representation.get(representation.size() - 1);
        specialCase.setCharAt(0, '\u23A3');
        specialCase.setCharAt(specialCase.length() - 1, '\u23A6');
        representation.forEach(item -> item.append("\n"));
        representation.get(representation.size() - 1).deleteCharAt(representation.get(representation.size() - 1).length() - 1);
        StringBuilder result = new StringBuilder(width * representation.size());
        representation.forEach(result::append);
        return result.toString();
    }
}
