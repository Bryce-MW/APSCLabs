// Lab09av100.java
// This is the 100 point version.
// Bryce Wilson
// May 3, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.math.BigInteger;
import java.util.Scanner;

public class Lab09av100 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the numerator ----> ");
        int numerator = input.nextInt();
        System.out.println("\nEnter the denominator --> ");
        int denominator = input.nextInt();
        Rational rational = new Rational(numerator, denominator);
        System.out.println("\n" + numerator + "∕" + denominator + " equals " + rational.getdoubleValue());
        System.out.println("\nand reduces to " + rational.getNumerator() + "/" + rational.getDenominator());
    }
}

class Rational {
    private boolean sign = true;

    private long numerator;
    private long denominator;

    public Rational(int numerator, int denominator) {
        if (numerator < 0) {
            this.sign = false;
            numerator *= -1;
        }
        if (denominator < 0) {
            this.sign = !this.sign;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.simplify();
    }

    private void simplify() {
        int gcd = BigInteger.valueOf(this.numerator).gcd(BigInteger.valueOf(this.denominator)).intValue();
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    public int getNumerator() {
        return (int) ((this.sign) ? this.numerator : -this.numerator);
    }

    public void setNumerator(long numerator) {
        if (numerator < 0) {
            this.sign = !this.sign;
            numerator *= -1;
        }
        this.numerator = numerator;
        this.simplify();
    }

    public long getDenominator() {
        return this.denominator;
    }

    public void setDenominator(long denominator) {
        if (denominator < 0) {
            this.sign = !this.sign;
            denominator *= -1;
        }
        this.denominator = denominator;
        this.simplify();
    }

    public double getdoubleValue() {
        return ((this.sign) ? 1 : -1) * ((double) this.numerator / (double) this.denominator);
    }
}