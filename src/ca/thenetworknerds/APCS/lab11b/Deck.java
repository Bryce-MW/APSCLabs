package ca.thenetworknerds.APCS.lab11b;

import java.util.ArrayList;

public class Deck {
    private Card[] cards;

    // Clubs, Diamonds, Hearts, Spades

    public Deck() {
        this.cards = new Card[52];
        for (int i = 0; i < 52; i++) {
            String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
                    "King", "Queen", "Ace"};
            int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
            this.cards[i] = new Card((i < 26) ? ((i < 13) ? "Clubs" : "Diamonds") : ((i < 39) ? "Hearts" : "Spades"),
                    ranks[i % 13], values[i % 13]);
        }
        this.shuffle();
    }

    private void shuffle() {
        Card[] newCards = new Card[52];
        ArrayList<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            remaining.add(i);
        }
        for (int i = 0; i < 52; i++) {
            int index = remaining.get((int) (Math.random() * remaining.size()));
            remaining.remove((Integer) index);
            newCards[index] = this.cards[i];
        }
        this.cards = newCards;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("There are 52 cards:\n");
        for (Card card :
                this.cards) {
            out.append(card);
            out.append("\n");
        }
        return out.toString();
    }
}
