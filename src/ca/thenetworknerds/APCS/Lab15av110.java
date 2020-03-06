// Lab15av110.java
// This is the 110 point version.
// Bryce Wilson
// April 19, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.text.DecimalFormat;
import java.util.stream.IntStream;

public class Lab15av110 {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        int size = Prompt.integer("Enter the size of the magic square (odd numbers only)");

        int[][] square = new int[size][size];
        int y = (size - 1) / 2;
        int x = 0;
        int numSquares = (int) Math.pow(size, 2);
        DecimalFormat format = new DecimalFormat("0".repeat(Integer.toString(numSquares).length()));
        String calculationString = size + "×" + size + " Magic Square";
        System.out.println(calculationString + "\n" + "=".repeat(calculationString.length()) + "\n");
        for (int total = 1; total <= numSquares; total++) {
            square[x--][y++] = total;
            if (total % size == 0) {
                y -= 1;
                x += 2;
            }
            if (y >= size) {
                y = 0;
            } else if (y < 0) {
                y = size - 1;
            }
            if (x >= size) {
                x = 0;
            } else if (x < 0) {
                x = size - 1;
            }
        }
        for (int[] i :
                square) {
            for (int j :
                    i) {
                System.out.print(format.format(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        String rowsString = "Checking Rows";
        System.out.println(rowsString + "\n" + "=".repeat(rowsString.length()) + "\n");
        for (int[] i :
                square) {
            for (int j :
                    i) {
                System.out.print(format.format(j) + "+");
            }
            System.out.println("\b = " + IntStream.of(i).sum());
        }
        System.out.println();
        String columnsString = "Checking Columns";
        System.out.println(columnsString + "\n" + "=".repeat(columnsString.length()) + "\n");
        for (int i = 0; i < size; i++) {
            int[] tmp = new int[size];
            for (int j = 0; j < size; j++) {
                tmp[j] = square[j][i];
                System.out.print(format.format(square[j][i]) + "+");
            }
            System.out.println("\b = " + IntStream.of(tmp).sum());
        }
        String diagString = "Checking Diagonals";
        System.out.println(diagString + "\n" + "=".repeat(diagString.length()) + "\n");
        int[] tmp = new int[size];
        for (int i = 0; i < size; i++) {
            tmp[i] = square[i][i];
            System.out.print(format.format(square[i][i]) + "+");
        }
        System.out.println("\b = " + IntStream.of(tmp).sum());
        for (int i = 0; i < size; i++) {
            tmp[i] = square[size - 1 - i][i];
            System.out.print(format.format(square[i][i]) + "+");
        }
        System.out.println("\b = " + IntStream.of(tmp).sum());
    }
}
