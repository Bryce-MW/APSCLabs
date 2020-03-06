// Lab12bav100.java
// This is the 100 point version.
// Bryce Wilson
// May 11, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.util.Scanner;

public class Lab12bv100 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println(MagpieLab12b.getGreeting());

        MagpieLab12b magpie = new MagpieLab12b();

        String[] newResponses = {
                "Tell me more about your family.",
                "I'm interested in hearing about your family.",
                "What else can you tell me about your family?",
                "I wish that I had a family, all I have is a programmer.",
                "What's it like having a family like that?",
                "Do you have any other family members?",
                "Are you happy with your family?",
                "Who is your favorite family member?",
                "How large is your family?",
                "What are your favorite family memories.",
        };
        magpie.addFamilyResponses(newResponses);

        String statement;
        do {
            statement = input.nextLine();
            System.out.println(magpie.getResponse(statement));
        } while (!statement.equals("Bye"));
    }
}
