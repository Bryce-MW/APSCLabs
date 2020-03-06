// Lab17av110.java
// This is the 100 point version.
// Bryce Wilson
// May 24, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

public class Lab17av100 {
    public static void main(String[] args) {
        System.out.println("Counting from 1 up to 10");
        Test.countUp(1, 10);
        skip3();
        System.out.println("Counting from 10 down to 1");
        Test.countDown(10, 1);
        skip3();
        System.out.println("The sum of all integers 0 to 100 " + Test.sum(100));
        skip2();
        System.out.println("The factorial of 8 is " + Test.fact(8));
        skip2();
        System.out.println("The 10th Fibonacci Number is " + Test.fibo(10));
        skip2();
        System.out.println("The GCF of 120 and 108 is " + Test.gcf(120, 108));
        skip2();
        System.out.println("2 raised to the 8th power is " + Test.pow(2, 8));
        skip2();
    }

    public static void skip2() {
        System.out.println("\n");
    }

    public static void skip3() {
        System.out.println("\n\n");
    }
}


class Test {
    // displays consecutive integers from k to n
    public static void countUp(int k, int n) {
        System.out.print(k + " ");
        if (k < n) {
            countUp(k + 1, n);
        }
    }

    // displays consecutive integers backwards from k to n
    public static void countDown(int k, int n) {
        System.out.print(k + " ");
        if (k > n) {
            countDown(k - 1, n);
        }
    }

    // returns the sum of all integers 1 + 2 + .... + n-1 + n
    public static int sum(int n) {
        if (n > 1) {
            return n + sum(n - 1);
        }
        return 1;
    }

    // returns n factorial
    public static int fact(int n) {
        if (n > 1) {
             return n * fact(n - 1);
        }
        return 1;
    }

    // returns the nth Fibonacci number
    public static int fibo(int n) {
        if (n > 2) {
            return fibo(n - 1) + fibo(n - 2);
        }
        return 1;
    }

    // returns the gcf of n1 and n2
    public static int gcf(int n1, int n2) {
        if (n2 != 0) {
            return gcf(n2, n1 % n2);
        }
        return n1;
    }

    // returns n1 raised to the n2 power
    public static int pow(int n1, int n2) {
        if (n2 > 1) {
            return n1 * pow(n1, n2 - 1);
        }
        return n1;
    }

}    


