package poker;

/**
 * Enum containing the various suits in a standard deck of playing cards.
 */
public enum Suit {
    CLUBS,
    SPADES,
    HEARTS,
    DIAMONDS;

    /**
    * A fancy override of toString() that prints the Unicode character (UTF-16)
    * corresponding to the suit's image.
    */
    @Override
    public String toString() {
        switch(this) {
            case SPADES:   return "\u2660";
            case HEARTS:   return "\u2661";
            case DIAMONDS: return "\u2662";
            case CLUBS:    return "\u2663";
            default:       return "";
        }
    }
}