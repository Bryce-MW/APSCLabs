// Lab03v100.java
// This is the 100 point version.
// Bryce Wilson
// April 17, 2019
// APCS, Mr. Robinson


package ca.thenetworknerds.APCS;

import java.time.Duration;

public class Lab03v100 {
    static {
        System.out.println("\nLab03, 100 Point Version\n");
    }

    public static void main(String[] args) {
        int millis = 10000123;
        System.out.print("Starting milli-seconds:  ");
        System.out.println(millis);
        Duration duration = Duration.ofMillis(millis);
        System.out.print("Hours:                   ");
        //System.out.println(duration.toHoursPart());
        System.out.print("Minutes:                 ");
        //System.out.println(duration.toMinutesPart());
        System.out.print("Seconds:                 ");
        //System.out.println(duration.toSecondsPart());
        System.out.print("Milli-Seconds:           ");
        //System.out.println(duration.toMillisPart());
        System.out.println();
    }
}
