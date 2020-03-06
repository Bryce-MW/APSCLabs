package ca.thenetworknerds.APCS.lab13.operations;

public interface Addable<T> extends Operable {
    T add(T other);
}
