package ca.thenetworknerds.APCS.lab13.operations;

public interface Dividable<T> extends Operable {
    T divide(T other);
}
