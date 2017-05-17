package poker;

import java.util.Arrays;

public class FiveCardHand {

    /**
     * This class ain't called SixCardHand...
     */
    public static final byte HAND_SIZE = 5;

    /**
     * Represents the hand in whatever order it was created.
     */
    private Card[] hand;
    /**
     * Represents the hand, but ordered from lowest rank to highest.
     */
    private Card[] ordered;

    /**
     * Constructs a five card hand.
     * Surprise, suprise, it freaks the fuck out if hand isn't of length 5.
     */
    public FiveCardHand(Card[] hand) {
        if (hand.length != HAND_SIZE) {
            throw new IllegalArgumentException(
                    "Card array must contain " + HAND_SIZE + " cards.");
        }
        this.hand = hand;
        setOrdered();
    }

    /**
     * Creates a copy of the hand array, sticks it in ordered, and sorts it.
     */
    private void setOrdered() {
        ordered = Arrays.copyOf(hand, hand.length);
        Arrays.sort(ordered);
    }

    /**
    * Returns a COPY of the card array representing the hand.
    */
    public Card[] getHand() {
        return Arrays.copyOf(hand, hand.length);
    }

    /**
     * Sets the card at the given index and reorders the ordered array,
     * or throws a hissy fit if the index is bad.
     */
    public void setCard(int index, Card card) {
        if (index < HAND_SIZE && index >= 0) {
            hand[index] = card;
            setOrdered();
        } else {
            throw new IllegalArgumentException(
                    "No card at the given index: " + index);
        }
    }

    /**
     * Returns a COPY of the card at the given index. Or freaks out out if
     * it's a bad index.
     */
    public Card getCard(int index) {
        if (index >= 0 && index < HAND_SIZE) {
            Card ans = hand[index];
            return ans;
        } else {
            throw new IllegalArgumentException(
                    "No card at the given index: " + index);
        }
    }

    /**
    * Returns the best poker hand you can make given this hand's cards.
    */

    public PokerHand classify() {
        for (int i = 0; i < ordered.length-1; i++ ) {
            if (this.containsStraightFlush()) {
                return PokerHand.STRAIGHT_FLUSH;
            } else if (this.containsFourOfAKind()) {
                return PokerHand.FOUR_OF_A_KIND;
            } else if (this.containsFullHouse()) {
                return PokerHand.FULL_HOUSE;
            } else if (this.containsFlush()) {
                return PokerHand.FLUSH;
            } else if (this.containsStraight()) {
                return PokerHand.STRAIGHT;
            } else if (this.containsThreeOfAKind()) {
                return PokerHand.THREE_OF_A_KIND;
            } else if (this.containsTwoPair()) {
                return PokerHand.TWO_PAIR;
            } else if (this.containsPair()) {
                return PokerHand.PAIR;
            } 
        }
        return PokerHand.HIGH_CARD;
    }

    /**
     * Returns true if the hand has at least one pair (two of the same card).
     */
    public boolean containsPair() {
        for (int i = 0; i < hand.length; i++) {
            for (int p = i+1; p < hand.length; p++) {
                if (hand[i].getRank() == hand[p].getRank()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the hand has two pairs.
     */
    public boolean containsTwoPair() {
        int num = 0;
        for (int i = 0; i < hand.length; i++) {
            for (int p = i+1; p < hand.length; p++) {
                if (hand[i].getRank() == hand[p].getRank()) {
                    num++;
                } else if (num == 2) {
                    return true; 
                }   
            }
        }
        return false;
    }
        

    /**
     * Returns true if the hand contains at least three of the same card.
     */
    public boolean containsThreeOfAKind() {
        int num = 0;
        for (int i = 0; i < hand.length; i++) {
            for (int p = i+1; p < hand.length; p++) {
                if (hand[i].getRank() == hand[p].getRank()) {
                    num++;
                } else if (num == 3) {
                    return true; 
                }   
            }
        }
        return false;
    }

    /**
     * Returns true if the five cards are in sequential order.
     * Remember, A-2-3-4-5 and 10-J-Q-K-A also count.
     */
    public boolean containsStraight() {
        int ans = 0;
        if (ordered[4].getValue() == 14 && ordered[0].getValue() == 2 && ordered[1].getValue() == 3) {
            ans++; 
        }
        for (int i = 0; i < ordered.length; i++) {
            for (int p = i+1; p < ordered.length; p++) {
                int num = ordered[p].getValue()-1;
                if (ordered[i].getValue() == num) {
                ans++;
                }
            }
        }
        if (ans == 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if all five cards have the same suit.
     */
    public boolean containsFlush() {
        if (ordered[0].getSuit().equals(ordered[1].getSuit()) && (ordered[1].getSuit().equals(ordered[2].getSuit())) && (ordered[2].getSuit().equals(ordered[3].getSuit())) && (ordered[3].getSuit().equals(ordered[4].getSuit()))) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the hand contains three cards of one rank and two cards
     * of another rank. For example three aces and two kings or three twos and
     * two fives.
     */
    public boolean containsFullHouse() {
        int count = 0;
        for (int i = 0; i < ordered.length-2; i++) {
            for (int p = i+1; p < ordered.length-2; p++) {
                if (ordered[i].getRank() == ordered[p].getRank()) {
                    count++;
                }
            }
        }
        if (count == 1) {
            if (ordered[2].getRank() == ordered[3].getRank() && ordered[2].getRank() == ordered[4].getRank()) {
                return true;
            }
        } else if (count == 3) {
            if (ordered[3].getRank() == ordered[4].getRank()) {
                return true;
            }
        } 
        return false;
    }



    /**
     * Returns true if the the hand contains at least four cards of the same
     * rank.
     */
    public boolean containsFourOfAKind() {
        int count = 0;
        for (int i = 0; i < ordered.length; i++) {
            for (int p = i+1; p < ordered.length; p++) {
                if (ordered[i].getRank() == ordered[p].getRank()) {
                    count++;
                }
            }
        }
        if (count == 6) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the hand contains both a straight and a flush.
     */
    public boolean containsStraightFlush() {
        if (this.containsStraight() && this.containsFlush()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[ " + hand[0].toString() + "  " + hand[1].toString()
                + "  " + hand[2].toString() + "  " + hand[3].toString()
                + "  " + hand[4].toString() + " ]";
    }
}