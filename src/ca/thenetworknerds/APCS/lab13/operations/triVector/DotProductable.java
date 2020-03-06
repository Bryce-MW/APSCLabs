package ca.thenetworknerds.APCS.lab13.operations.triVector;

public interface DotProductable<T, internalType> extends MathVectorable<internalType> {
    internalType dotProduct(T other);
}
