// Lab11av100.java
// This is the 100 point version.
// Bryce Wilson
// May 6, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.text.DecimalFormat;

public class Lab11av100 {
    private static final int columnSize = 15;

    public static void main(String[] args) {
        Prompt.length = 35;
        int bound = Prompt.integer("Enter the prime's upper bound");
        boolean[] prime = new boolean[bound + 1];
        System.out.println("Initializing array\n");
        for (int i = 0; i < bound + 1; i++) {
            prime[i] = true;
        }

        int max = 0;
        System.out.println("Computing primes\n");
        for (int i = 2; i < bound; i++) {
            if (prime[i]) {
                max = i;
                for (int j = 1; j < bound / i; j++) {
                    prime[i + j * i] = false;
                }
            }
            if ((i + 1) % 1000000 == 0) {
                System.out.println("Computed " + (i + 1) + " numbers so far");
            }
        }
        DecimalFormat format = new DecimalFormat("0".repeat((int) Math.log10(max) + 1));
        int columnCount = 0;
        System.out.println("\nPrinting all primes less than " + bound + "\n");
        for (int i = 2; i < bound; i++) {
            if (prime[i]) {
                System.out.print(format.format(i) + " ");
                if (columnCount++ == Lab11av100.columnSize) {
                    columnCount = 0;
                    System.out.println();
                }
            }
        }
    }
}
