package ca.thenetworknerds.APCS.lab13.vectorables;

import ca.thenetworknerds.APCS.lab13.operations.triVector.CrossProductable;
import ca.thenetworknerds.APCS.lab13.operations.triVector.DotProductable;

import java.util.ArrayList;
import java.util.stream.Stream;

public class MathVectorThree<T extends FourFunctionVectorable<T>> extends MathVector<T>
        implements DotProductable<MathVectorThree<T>, T>, CrossProductable<MathVectorThree<T>, T> {

    public MathVectorThree() {}
    public MathVectorThree(ArrayList<T> numbers) {
        super(numbers);
        if (numbers.size() != 3) {
            throw new IllegalArgumentException("MathVectorThree must have exactly three elements");
        }
    }

    @Override
    public MathVectorThree<T> crossProduct(MathVectorThree<T> other) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dotProduct(MathVectorThree<T> other) {
        T total = vector.get(0).add(vector.get(1));
        for (int i = 2; i < vector.size(); i++) {
            total = total.add(vector.get(i));
        }
        return total;
    }

    class pairIndex<pairType> {
        pairType first;
        pairType second;
        int index;
        pairIndex(pairType first, pairType second, int index) {
            this.first = first;
            this.second = second;
            this.index = index;
        }
    }

    @Override
    public MathVectorThree<IntegerAdapter> createRandomValue() {
        ArrayList<IntegerAdapter> values = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            values.add(new IntegerAdapter((int) (Math.random() * 3)));
        }
        return new MathVectorThree<>(values);
    }

    @Override
    public String toString() {
        int width = getPrintWidth();
        return '\u239B' +
               vector.get(0).toString() +
               " ".repeat(width - vector.get(0).toString().length() - 2) +
               "\u239E\n\u239C" +
               vector.get(1).toString() +
               " ".repeat(width - vector.get(1).toString().length() - 2) +
               "\u239F\n\u239D" +
               vector.get(2).toString() +
               " ".repeat(width - vector.get(2).toString().length() - 2) +
               '\u23A0';
    }
}
