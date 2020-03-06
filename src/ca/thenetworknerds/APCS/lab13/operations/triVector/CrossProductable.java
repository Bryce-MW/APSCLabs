package ca.thenetworknerds.APCS.lab13.operations.triVector;

public interface CrossProductable<T, internalType> extends MathVectorable<internalType> {
    T crossProduct(T other);
}
