package poker;

import java.util.Arrays;

/**
 * Represents a five card hand in poker.
 */
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
     * Surprise, suprise, it freaks out if hand isn't of length 5.
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
            // TODO: Finish me.
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
            // TODO: Finish me.
            return null;
        } else {
            throw new IllegalArgumentException(
                    "No card at the given index: " + index);
        }
    }

    /**
    * Returns the best poker hand you can make given this hand's cards.
    */
    public PokerHand classify() {
        // TODO: Finish me.
        return PokerHand.HIGH_CARD;
    }

    /**
     * Returns true if the hand has at least one pair (two of the same card).
     */
    public boolean containsPair() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if the hand has two pairs.
     */
    public boolean containsTwoPair() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if the hand contains at least three of the same card.
     */
    public boolean containsThreeOfAKind() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if the five cards are in sequential order.
     * Remember, A-2-3-4-5 and 10-J-Q-K-A also count.
     */
    public boolean containsStraight() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if all five cards have the same suit.
     */
    public boolean containsFlush() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if the hand contains three cards of one rank and two cards
     * of another rank. For example three aces and two kings or three twos and
     * two fives.
     */
    public boolean containsFullHouse() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if the the hand contains at least four cards of the same
     * rank.
     */
    public boolean containsFourOfAKind() {
        // TODO: Finish me.
        return false;
    }

    /**
     * Returns true if the hand contains both a straight and a flush.
     */
    public boolean containsStraightFlush() {
        // TODO: Finish me.
        return false;
    }

    @Override
    public String toString() {
        return "[ " + hand[0].toString() + "  " + hand[1].toString()
                + "  " + hand[2].toString() + "  " + hand[3].toString()
                + "  " + hand[4].toString() + " ]";
    }
}