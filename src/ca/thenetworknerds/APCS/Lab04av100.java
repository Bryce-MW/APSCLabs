// Lab03v100.java
// This is the 100 point version.
// Bryce Wilson
// April 17, 2019
// APCS, Mr. Robinson


package ca.thenetworknerds.APCS;

public class Lab04av100 {
    static {
        System.out.println("\nLab03, 100 Point Version\n");
    }

    public static void main(String[] args) {
        double principal = 250000D;
        double interestRate = 4.85D;
        double paybackTime = 30D;
        double paymentsPerYear = 12D;

        double numberOfPeriods = paymentsPerYear * paybackTime;
        double interestPerPayment = interestRate / (100 * paymentsPerYear);
        double temp = Math.pow(1 + interestPerPayment, numberOfPeriods);
        double payment = principal * ((interestPerPayment * temp) / (temp - 1));

        System.out.println("Principal:              " + principal);
        System.out.println("Annual Rate:            " + interestRate + "%");
        System.out.println("Number of Years:        " + paybackTime);
        System.out.println("Monthly Payment:        " + ((double) Math.round(payment * 100) / 100));
        System.out.println("Total Payments:         " + ((double) Math.round((payment*numberOfPeriods) * 100) / 100));
        System.out.println("Total Interest:         " + ((double) Math.round((payment*numberOfPeriods - principal) * 100) / 100));
    }
}
