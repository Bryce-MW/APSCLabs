package ca.thenetworknerds.APCS.lab13;

import ca.thenetworknerds.APCS.lab13.operations.*;
import ca.thenetworknerds.APCS.lab13.operations.triVector.CrossProductable;
import ca.thenetworknerds.APCS.lab13.operations.triVector.DotProductable;
import ca.thenetworknerds.APCS.lab13.operations.triVector.MathVectorable;
import ca.thenetworknerds.APCS.lab13.vectorables.ExampleVector;
import ca.thenetworknerds.APCS.lab13.vectorables.Vectorable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
class Vector<T extends Operable> {
    public static final String NO_LENGTH_ZERO = "Length 0 vectors are currently not supported";
    private ArrayList<T> vector;

    Vector(ArrayList<T> vector) {
        this.vector = vector;
    }

    private void check(Vector<?> other) {
        if (vector.size() != other.vector.size()) {
            throw new IllegalArgumentException("Left vector size " + vector.size() +
                    " does not match right vector size " + other.vector.size());
        }
        if (vector.isEmpty()) {
            throw new IllegalArgumentException(NO_LENGTH_ZERO);
        }
    }
    private void check() {
        if (vector.isEmpty()) {
            throw new IllegalArgumentException(NO_LENGTH_ZERO);
        }
    }

    private <X extends Operable> Vector<T> doFunction(Function<pairIndex<X>, X> function, Vector<X> other) {
        check(other);
        ArrayList<X> left = (ArrayList<X>) vector;
        ArrayList<X> right = other.vector;
        int size = left.size();
        ArrayList<X> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(function.apply(new pairIndex<>(left.get(i), right.get(i), 0)));
        }
        return new Vector<>((ArrayList<T>) result);
    }

    private Addable<T> test(pairIndex<Addable<T>> tmp) {
        return (Addable<T>) tmp.first.add((T) tmp.second);
    }


    Vector<T> add(Vector<T> other) {
        Vector<Addable<T>> newOther = new Vector<>((ArrayList<Addable<T>>) other.vector);
        return this.doFunction((pairIndex<Addable<T>>       pair) -> (Addable<T>)       pair.first.add     ((T) pair.second), newOther);
    }
    Vector<T> subtract(Vector<T> other) {
        Vector<Subtractable<T>> newOther = new Vector<>((ArrayList<Subtractable<T>>) other.vector);
        return this.doFunction((pairIndex<Subtractable<T>>  pair) -> (Subtractable<T>)  pair.first.subtract((T) pair.second), newOther);
    }
    Vector<T> multiply(Vector<T> other) {
        Vector<Multiplicable<T>> newOther = new Vector<>((ArrayList<Multiplicable<T>>) other.vector);
        return this.doFunction((pairIndex<Multiplicable<T>> pair) -> (Multiplicable<T>) pair.first.multiply((T) pair.second), newOther);
    }
    Vector<T> divide(Vector<T> other) {
        Vector<Dividable<T>> newOther = new Vector<>((ArrayList<Dividable<T>>) other.vector);
        return this.doFunction((pairIndex<Dividable<T>>     pair) -> (Dividable<T>)     pair.first.divide  ((T) pair.second), newOther);
    }

    Vector<T> crossProduct(Vector<T> other) {
        Vector<CrossProductable<T, ?>> newOther = new Vector<>((ArrayList<CrossProductable<T, ?>>) other.vector);
        return this.doFunction((pairIndex<CrossProductable<T, ?>> pair) -> (CrossProductable<T, ?>) pair.first.crossProduct((T) pair.second), newOther);
    }
    <X extends Operable> Vector<X> dotProduct(Vector<T> other) {
        Vector<DotProductable<T, X>> newOther = new Vector<>((ArrayList<DotProductable<T, X>>) other.vector);
        check(other);
        ArrayList<DotProductable<T, X>> left = (ArrayList<DotProductable<T, X>>) vector;
        ArrayList<DotProductable<T, X>> right = newOther.vector;

        ArrayList<X> result = new ArrayList<>(left.size());
        for (int i = 0; i < left.size(); i++) {
            result.add(left.get(i).dotProduct((T) right.get(i)));
        }

        return new Vector<>(result);
    }

    <X extends Operable> Vector<X> squareDistance() {
        return distance(MathVectorable::squareDistance);
    }
    private <X extends Operable> Vector<X> distance(Function<MathVectorable<X>, X> function) {
        check();
        if (!(vector.get(0) instanceof MathVectorable)) {
            throw new UnsupportedOperationException("Class " + vector.get(0).getClass().getSimpleName() +
                                                    " does not implement MathVectorable, only: " +
                                                    Arrays.toString(vector.get(0).getClass().getInterfaces()));
        }
        ArrayList<MathVectorable<X>> left = (ArrayList<MathVectorable<X>>) vector;
        ArrayList<X> result = new ArrayList<>();
        left.stream().map(function).forEach(result::add);
        return new Vector<>(result);
    }

    public String toString() {
        int width;
        try {
            width = vector.stream().max(Comparator.comparingInt(Vectorable::getPrintWidth)).get().getPrintWidth() + 2;
        } catch (NoSuchElementException e) {
            width = 3;
        }
        @SuppressWarnings("Duplicates")
        ArrayList<StringBuilder> representation = new ArrayList<>();
        for (T item :
                vector) {
            String[] itemString = item.toString().split("\\R", -1);
            ArrayList<StringBuilder> result = new ArrayList<>();
            Arrays.stream(itemString).map(StringBuilder::new).forEach(result::add);
            representation.addAll(result);
        }
        for (StringBuilder item :
                representation) {
            item.insert(0, '\u23AA');
            int additionalSpaces = width - item.length() - 1;
            item.append(" ".repeat((additionalSpaces >= 0) ? additionalSpaces : 0));
            item.append('\u23AA');
        }
        StringBuilder specialCase = representation.get(0);
        specialCase.setCharAt(0, '\u23A7');
        specialCase.setCharAt(specialCase.length() - 1, '\u23AB');
        specialCase = representation.get(representation.size() - 1);
        specialCase.setCharAt(0, '\u23A9');
        specialCase.setCharAt(specialCase.length() - 1, '\u23AD');
        specialCase = representation.get((representation.size() - 1) / 2);
        specialCase.setCharAt(0, '\u23A8');
        specialCase.setCharAt(specialCase.length() - 1, '\u23AC');
        representation.forEach(item -> item.append("\n"));
        StringBuilder result = new StringBuilder(width * representation.size());
        representation.forEach(result::append);
        return result.toString();
    }

    private static void appendToAll(ArrayList<StringBuilder> string, String toAppend) {
        for (StringBuilder item :
                string) {
            item.append(toAppend);
        }
    }

    public static String displayOperation(Vector<?> vector1, char operation, Vector<?> vector2, Vector<?> result) {
        ArrayList<StringBuilder> output = new ArrayList<>();
        Arrays.stream(vector1.toString().split("\\R", -1)).map(StringBuilder::new).forEach(output::add);
        appendToAll(output, "  ");
        output.get((output.size() - 1) / 2).setCharAt(output.get(0).length() - 1, operation);
        appendToAll(output, " ");
        String[] temp = vector2.toString().split("\\R", -1);
        for (int i = 0; i < temp.length; i++) {
            output.get(i).append(temp[i]);
        }
        appendToAll(output, "  ");
        output.get((output.size() - 1) / 2).setCharAt(output.get(0).length() - 1, '=');
        appendToAll(output, " ");
        temp = result.toString().split("\\R", -1);
        for (int i = 0; i < temp.length; i++) {
            output.get(i).append(temp[i]);
        }
        StringBuilder finalOutput = new StringBuilder(output.get(0).length() * output.size());
        appendToAll(output, "\n");
        output.get(output.size() - 1).deleteCharAt(output.get(output.size() - 1).length() - 1);
        output.forEach(finalOutput::append);
        return finalOutput.toString();
    }

    private class pairIndex<pairType> {
        pairType first;
        pairType second;
        int index;
        pairIndex(pairType first, pairType second, int index) {
            this.first = first;
            this.second = second;
            this.index = index;
        }
    }

    public static <X extends ExampleVector> Vector<X> createRandomVector(int size, X example) {
        ArrayList<X> vector = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            vector.add((X) example.createRandomValue());
        }
        return new Vector<>(vector);
    }
}
