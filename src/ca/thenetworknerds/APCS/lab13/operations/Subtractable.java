package ca.thenetworknerds.APCS.lab13.operations;

public interface Subtractable<T> extends Addable<T> {
    T subtract(T other);
}
