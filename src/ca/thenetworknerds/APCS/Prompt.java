package ca.thenetworknerds.APCS;

import java.util.Scanner;

public class Prompt {
    public static int length = 30;

    private static void printPrompt(String prompt) {
        StringBuilder newPrompt = new StringBuilder(Prompt.length);
        newPrompt.append(prompt).append(" ");
        newPrompt.append("-".repeat(Math.max(0, Prompt.length - 2 - newPrompt.length())));
        newPrompt.append("> ");
        System.out.print(newPrompt);
    }

    public static String string(String prompt) {
        Prompt.printPrompt(prompt);
        String result = new Scanner(System.in).nextLine();
        System.out.println();
        return result;
    }

    public static int integer(String prompt) {
        Prompt.printPrompt(prompt);
        int result = new Scanner(System.in).nextInt();
        System.out.println();
        return result;
    }
}
