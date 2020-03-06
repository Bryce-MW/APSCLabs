package ca.thenetworknerds.APCS;

import java.util.ArrayList;
import java.util.Arrays;

class MagpieLab12b {
    private ArrayList<String> familyResponses;

    MagpieLab12b() {
        this.familyResponses = new ArrayList<>();
    }

    void addFamilyResponses(String[] responses) {
        this.familyResponses.addAll(Arrays.asList(responses));
    }

    String getResponse(String statement) {
        String[] familyInputs = {
                "mother",
                "father",
                "sister",
                "brother",
                "family",
                "parent",
        };

        statement = statement.toLowerCase();
        if (statement.contains("no")) {
            return "Why so negative?";
        } else if (MagpieLab12b.stringContains(statement, familyInputs)) {
            return (this.familyResponses.size() != 0) ?
                    this.familyResponses.get((int) (Math.random() * this.familyResponses.size())) : MagpieLab12b.getRandomResponse();
        } else {
            return MagpieLab12b.getRandomResponse();
        }
    }

    static String getGreeting() {
        return "Hello, let's talk.";
    }

    private static String getRandomResponse() {
        String[] responses = {
            "Interesting, tell me more.",
            "Hmm.",
            "Do you really think so?",
            "You don't say.",
            "That's interesting.",
            "I don't know anything about that.",
            "It is beyond my abilities to respond to that.",
            "I don't know what to say.",
            "That's an interesting way to view things.",
            "I have not heard of that before.",
        };
        return responses[(int) (Math.random() * responses.length)];
    }

    private static boolean stringContains(String input, String[] matches) {
        return Arrays.stream(matches).anyMatch(input::contains);
    }
}
