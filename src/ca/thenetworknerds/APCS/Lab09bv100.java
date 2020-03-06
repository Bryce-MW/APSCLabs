// Lab09bv100.java
// This is the 100 point version.
// Bryce Wilson
// May 4, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.math.BigInteger;

public class Lab09bv100 {
    public static void main(String[] args) {
        RationalB rationalA = new RationalB(
                Prompt.integer("Enter the 1st numerator"),
                Prompt.integer("Enter the 1st denominator")
        );
        RationalB rationalB = new RationalB(
                Prompt.integer("Enter the 2nd numerator"),
                Prompt.integer("Enter the 2nd denominator")
        );
        System.out.println("\n" +
               rationalA + " + " + rationalB + " = " + rationalA.add(rationalB));
        System.out.println("\n" +
                rationalA + " - " + rationalB + " = " + rationalA.subtract(rationalB));
        System.out.println("\n" +
                rationalA + " * " + rationalB + " = " + rationalA.multiply(rationalB));
        System.out.println("\n" +
                rationalA + " / " + rationalB + " = " + rationalA.divide(rationalB));
    }
}

class RationalB {
    private boolean sign = true;

    private long numerator;
    private long denominator;

    RationalB(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Rational numbers can not have 0 as denominator");
        }
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

    private RationalB(long numerator, long denominator, boolean sign) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.sign = sign;
        this.simplify();
    }

    private void simplify() {
        int gcd = BigInteger.valueOf(this.numerator).gcd(BigInteger.valueOf(this.denominator)).intValue();
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    int getNumerator() {
        return (int) ((this.sign) ? this.numerator : -this.numerator);
    }

     void setNumerator(long numerator) {
        if (numerator < 0) {
            this.sign = !this.sign;
            numerator *= -1;
        }
        this.numerator = numerator;
         this.simplify();
    }

    long getDenominator() {
        return this.denominator;
    }

    void setDenominator(long denominator) {
        if (denominator < 0) {
            this.sign = !this.sign;
            denominator *= -1;
        }
        this.denominator = denominator;
        this.simplify();
    }

    double getDoubleValue() {
        return ((this.sign) ? 1 : -1) * ((double) this.numerator / (double) this.denominator);
    }

    RationalB add(RationalB other) {
        long newNumeratorA = this.numerator * other.denominator;
        long newNumeratorB = other.numerator * this.denominator;
        if (!this.sign && !other.sign) {
            return new RationalB(newNumeratorA + newNumeratorB,
                    this.denominator * other.denominator, false);
        } else if (!this.sign) {
            if (newNumeratorA > newNumeratorB) {
                return new RationalB(newNumeratorA - newNumeratorB,
                        this.denominator * other.denominator, false);
            } else {
                return new RationalB(newNumeratorB - newNumeratorA,
                        this.denominator * other.denominator, true);
            }
        } else if (!other.sign) {
            if (newNumeratorB > newNumeratorA) {
                return new RationalB(newNumeratorB - newNumeratorA,
                        this.denominator * other.denominator, false);
            } else {
                return new RationalB(newNumeratorA - newNumeratorB,
                        this.denominator * other.denominator, true);
            }
        } else {
            return new RationalB(newNumeratorA + newNumeratorB,
                    this.denominator * other.denominator, true);
        }
    }

    RationalB subtract(RationalB other) {
        return add(new RationalB(other.numerator, other.denominator, !other.sign));
    }

    RationalB multiply(RationalB other) {
        return new RationalB(this.numerator * other.numerator,
                this.denominator * other.denominator, this.sign == other.sign);
    }

    RationalB divide(RationalB other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return this.multiply(new RationalB(other.denominator, other.numerator, other.sign));
    }

    public String toString() {
        return ((this.sign) ? "" : "-") + this.numerator + "/" + this.denominator;
    }
}