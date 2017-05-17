package poker;

/**
 * Enum containing the various ranks of a standard playing card.
 * It initializes the rank with a value equal to the strength of the card
 * (higher strength means better card).
 */
public enum Rank {
    DEUCE (2),
    THREE (3),
    FOUR  (4),
    FIVE  (5),
    SIX   (6),
    SEVEN (7),
    EIGHT (8),
    NINE  (9),
    TEN   (10),
    JACK  (11),
    QUEEN (12),
    KING  (13),
    ACE   (14);

    private final int value;

    private Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * The string representation of Rank is either the number of the card,
     * or the first letter of the rank (J,Q,K,A) if it's a face card.
     */
    @Override
    public String toString() {
        if (value > 1 && value < 11) {
            return Integer.toString(value);
        } else {
            return name().toString().substring(0, 1);
        }
    }
}s