package poker;

/**
 * Represents a standard playing card with a rank and a suit.
 * Implements Comparable so they can be easily sorted.
 */
public class Card implements Comparable<Card> {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
/**
 * Returns 'Strength' of Card
 * see: Rank.java class.
 */
    public int getValue() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    // The following methods are semi-advanced Java that you don't REALLY need to
    // know just yet.

    @Override
    public int compareTo(Card otherCard) {
        return getRank().compareTo(otherCard.getRank());
    }

    /**
     * Two cards are equal if they have the same rank and suit.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Card otherCard = (Card)obj;
        if (otherCard.rank != this.rank || otherCard.suit != this.suit) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
        result = prime * result + ((suit == null) ? 0 : suit.hashCode());
        return result;
    }
}