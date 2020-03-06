package ca.thenetworknerds.APCS.lab13;

import ca.thenetworknerds.APCS.lab13.vectorables.*;

public class Lab13v100 {
    public static void main(String[] args) {
        Vector<DoubleAdapter> left0 = Vector.createRandomVector(5, new DoubleAdapter());
        Vector<DoubleAdapter> right0 = Vector.createRandomVector(5, new DoubleAdapter());
        Vector<DoubleAdapter> result0 = left0.subtract(right0);
        System.out.println(Vector.displayOperation(left0, '-', right0, result0));

        Vector<IntegerAdapter> left1 = Vector.createRandomVector(5, new IntegerAdapter());
        Vector<IntegerAdapter> right1 = Vector.createRandomVector(5, new IntegerAdapter());
        Vector<IntegerAdapter> result1 = left1.multiply(right1);
        System.out.println(Vector.displayOperation(left1, '\u00D7', right1, result1));

        Vector<MathVector<IntegerAdapter>> left2 = Vector.createRandomVector(5, new MathVector<>());
        Vector<MathVector<IntegerAdapter>> right2 = Vector.createRandomVector(5, new MathVector<>());
        Vector<MathVector<IntegerAdapter>> result2 = left2.add(right2);
        System.out.println(Vector.displayOperation(left2, '+', right2, result2));

        Vector<MathVectorThree<IntegerAdapter>> left3 = Vector.createRandomVector(5, new MathVectorThree<>());
        Vector<MathVectorThree<IntegerAdapter>> right3 = Vector.createRandomVector(5, new MathVectorThree<>());
        Vector<MathVectorThree<IntegerAdapter>> result3 = left3.dotProduct(right3);
        System.out.println(Vector.displayOperation(left3, '\u2219', right3, result3));

        Vector<StringAdapter> left4 = Vector.createRandomVector(5, new StringAdapter());
        Vector<StringAdapter> right4 = Vector.createRandomVector(5, new StringAdapter());
        Vector<StringAdapter> result5 = left4.add(right4);
        System.out.println(Vector.displayOperation(left4, '+', right4, result5));
    }
}
