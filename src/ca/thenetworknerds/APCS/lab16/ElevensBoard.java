package ca.thenetworknerds.APCS.lab16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 * ***********************************************************************
 * This is the original AP Elevens Lab Java program code.
 * 03-26-15 slightly altered by Leon Schram
 * who likes curly braces aligned.
 * ************************************************************************
 * This is the only file that students alter for Lab16.
 * This is the student, starting file of Lab16.
 */
public class ElevensBoard {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};
    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;
    /**
     * The cards on this board.
     */
    private Card[] cards;
    /**
     * The deck of cards being used to play the current game.
     */
    private Deck deck;


    /**
     * Creates a new {@code ElevensBoard} instance.
     */
    public ElevensBoard() {
        cards = new Card[BOARD_SIZE];
        deck = new Deck(RANKS, SUITS, POINT_VALUES);
        if (I_AM_DEBUGGING) {
            System.out.println(deck);
            System.out.println("----------");
        }
        dealMyCards();
    }

    /**
     * Start a new game by shuffling the deck and
     * dealing some cards to this board.
     */
    public void newGame() {
        deck.shuffle();
        dealMyCards();
    }

    /**
     * Accesses the size of the board.
     * Note that this is not the number of cards it contains,
     * which will be smaller near the end of a winning game.
     *
     * @return the size of the board
     */
    public int size() {
        return cards.length;
    }

    /**
     * Determines if the board is empty (has no cards).
     *
     * @return true if this board is empty; false otherwise.
     */
    public boolean isEmpty() {
        for (Card card : cards) {
            //noinspection VariableNotUsedInsideIf
            if (card != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Deal a card to the kth position in this board.
     * If the deck is empty, the kth card is set to null.
     *
     * @param k the index of the card to be dealt.
     */
    public void deal(int k) {
        cards[k] = deck.deal();
    }

    /**
     * Accesses the deck's size.
     *
     * @return the number of undealt cards left in the deck.
     */
    public int deckSize() {
        return deck.size();
    }

    /**
     * Accesses a card on the board.
     *
     * @param k is the board position of the card to return.
     * @return the card at position k on the board.
     */
    public Card cardAt(int k) {
        return cards[k];
    }

    /**
     * Replaces selected cards on the board by dealing new cards.
     *
     * @param selectedCards is a list of the indices of the
     *                      cards to be replaced.
     */
    public void replaceSelectedCards(List<Integer> selectedCards) {
        for (Integer k : selectedCards) {
            deal(k);
        }
    }

    /**
     * Gets the indexes of the actual (non-null) cards on the board.
     *
     * @return a List that contains the locations (indexes)
     * of the non-null entries on the board.
     */
    public List<Integer> cardIndexes() {
        List<Integer> selected = new ArrayList<>();
        for (int k = 0; k < cards.length; k++) {
            if (cards[k] != null) {
                selected.add(k);
            }
        }
        return selected;
    }

    /**
     * Generates and returns a string representation of this board.
     *
     * @return the string version of this board.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int k = 0; k < cards.length; k++) {
            s.append(k);
            s.append(": ");
            s.append(cards[k]);
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Determine whether or not the game has been won,
     * i.e. neither the board nor the deck has any more cards.
     *
     * @return true when the current game has been won;
     * false otherwise.
     */
    public boolean isGameWon() {
        if (deck.isEmpty()) {
            for (Card c : cards) {
                //noinspection VariableNotUsedInsideIf
                if (c != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Deal cards to this board to start the game.
     */
    private void dealMyCards() {
        for (int k = 0; k < cards.length; k++) {
            cards[k] = deck.deal();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// DO NOT CHANGE ANY METHODS ABOVE THIS LINE.
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////  ONLY ALTER THE METHODS BELOW THIS LINE.
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     *
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     * false otherwise.
     */
    public boolean isLegal(List<Integer> selectedCards) {
        return containsPairSum11(selectedCards) || containsJQK(selectedCards);
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     *
     * @return true if there is a legal play left on the board;
     * false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isAnotherPlayPossible() {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                ArrayList<Integer> temp = new ArrayList<>(2);
                temp.add(i);
                temp.add(j);
                if (containsPairSum11(temp) && i != j) {
                    return true;
                }
            }
        }
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                for (int k = 0; k < cards.length; k++) {
                    ArrayList<Integer> temp = new ArrayList<>(3);
                    temp.add(i);
                    temp.add(j);
                    temp.add(k);
                    if (containsJQK(temp) && i != j && i != k && j != k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int cardToInt(Card card) {
        return switch (card.rank()) {
            case "2" -> 1;
            case "3" -> 2;
            case "4" -> 3;
            case "5" -> 4;
            case "6" -> 5;
            case "7" -> 6;
            case "8" -> 7;
            case "9" -> 8;
            case "10" -> 9;
            case "jack" -> 10;
            case "queen" -> 11;
            case "king" -> 12;
            default -> 0;
        };
    }

    /**
     * Check for an 11-pair in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     * contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
        return (selectedCards.size() == 2) &&
               (cardToInt(cards[selectedCards.get(0)]) < 10) &&
               (cardToInt(cards[selectedCards.get(1)]) < 10) &&
               (cardToInt(cards[selectedCards.get(0)]) + cardToInt(cards[selectedCards.get(1)]) + 2 == 11);
    }

    /**
     * Check for a JQK in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     * include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
        Boolean[] found = new Boolean[3];
        for (int i = 0; i < 3; i++) {
            found[i] = false;
        }
        if (selectedCards.size() != 3) {
            return false;
        }
        for (int i :
                selectedCards) {
            switch (cardToInt(cards[i])) {
                case 10:
                    if (found[0]) {
                        return false;
                    }
                    found[0] = true;
                    break;
                case 11:
                    if (found[1]) {
                        return false;
                    }
                    found[1] = true;
                    break;
                case 12:
                    if (found[2]) {
                        return false;
                    }
                    found[2] = true;
                    break;
                default:
                    return false;
            }
        }
        return Arrays.asList(found).contains(Boolean.FALSE);
    }

}
