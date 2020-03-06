// Lab08v100.java
// This is the 100 point version.
// Bryce Wilson
// May 3, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.util.Scanner;

public class Lab08v100 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter a string  ===>>  ");
            Lab08v100.multiPalindrome(input.nextLine().strip());
            System.out.print("Do you wish to repeat this program [Y/N]?  ===>>  ");
        } while (input.nextLine().strip().toLowerCase().equals("y"));
    }

    private static void multiPalindrome(String input) {
        System.out.println("\nEntered String:     " + input);
        StringBuilder cased = new StringBuilder(input.toLowerCase());
        boolean standard = Lab08v100.palindrome(cased);
        boolean almost = Lab08v100.almostPalindrome(cased);
        System.out.println("Palindrome:         " + standard);
        System.out.println("Almost Palindrome:  " + (almost && !standard));
    }

    private static boolean palindrome(StringBuilder input) {
        return input.toString().equals(input.reverse().toString());
    }

    private static boolean almostPalindrome(StringBuilder input) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder") StringBuilder striped = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char next = input.charAt(i);
            if (Character.isAlphabetic(next)) {
                striped.append(next);
            }
        }
        return striped.toString().equals(striped.reverse().toString());
    }
}
