package ca.thenetworknerds.APCS.lab13.operations;

public interface Multiplicable<T> extends Operable {
    T multiply(T other);
}
