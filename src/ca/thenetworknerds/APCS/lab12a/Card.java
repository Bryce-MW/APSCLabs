// Card.java
// The <Card> class is identical to the <Card03> class.
// In this "Elevens Lab" version it will used by the <Deck> class.
// //////////////////////////////////////////////////////////
// This program is designed to explain the "Elevens" lab created
// by Michael Clancy, Robert Glen Martin and Judith Hromcik.
// Divided into stages and altered August 2014 by Leon Schram.
// A re-definition of the <toString> method is added to the <Card> class.

/*
 I modified this slightly. I fixed the names of the getters and fixed the equals method because you don't have to use
 the getters for private values of the same class. I also changed up the toString function so that things show up in
 columns.
 */


package ca.thenetworknerds.APCS.lab12a;

public class Card {
    private static final int maxRank = 5;
    private static final int maxSuit = 8;
    private static final int maxValue = 2;
    private String suit;
    private String rank;
    private int pointValue;

    public Card(String s, String r, int pV) {
        this.suit = s;
        this.rank = r;
        this.pointValue = pV;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getRank() {
        return this.rank;
    }

    public int getPointValue() {
        return this.pointValue;
    }

    public boolean equals(Card otherCard) {
        return otherCard.suit.equals(suit)
                && otherCard.rank.equals(rank)
                && otherCard.pointValue == pointValue;
    }

    public String toString() {
        return this.rank + " ".repeat(Card.maxRank - this.rank.length()) + " of " + this.suit + " ".repeat(Card.maxSuit - this.suit.length()) +
               " (point value = " + " ".repeat(Card.maxValue - Integer.toString(this.pointValue).length()) + this.pointValue + ")";
    }
}

