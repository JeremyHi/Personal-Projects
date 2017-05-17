package poker;

class DeckTest {
    public static void main(String[] args){
        cardAtTest();
        indexOfTest();
        shuffleTest();
        cutTest();
    }

    public static void cardAtTest() {
        System.out.println("Tests for cardAt():");
        try {
            System.out.println("Test #1");
            Deck deck = new Deck();
            if (deck.cardAt(0) instanceof Card){
                System.out.println("true");
            } else {
                System.out.println("false: method does not return the appropriate type");
            }
            int i = 0;
            for (Suit s: Suit.values()) {
                for (Rank r: Rank.values()) {
                    if(!deck.cardAt(i).getSuit().equals(s)){
                        System.out.println("Test #2" + '\n' + "false");
                        return;
                    }
                    if(!deck.cardAt(i).getRank().equals(r)){
                        System.out.println("Test #2" + '\n' + "false");
                        return;
                    }
                    i++;
                }
            }
            System.out.println("Test #2: " + '\n' + "true");
            System.out.println();
        } catch (Exception e) {
            System.out.println("false: Exception");
        }
    }

    public static void indexOfTest() {
        System.out.println("Tests for indexOf():");
        try {
            Rank[] rankValues = Rank.values();
            Suit[] suitValues = Suit.values();
            Deck deck = new Deck();

            Card card1 = new Card(rankValues[0], suitValues[0]);
            System.out.println("Test #1");
            System.out.println(deck.indexOf(card1) == 0);

            Card card2 = new Card(rankValues[2], suitValues[2]);
            System.out.println("Test #2");
            System.out.println(deck.indexOf(card2) == 28);

            Card card3 = new Card(rankValues[10], suitValues[3]);
            System.out.println("Test #3");
            System.out.println(deck.indexOf(card3) == 49);
            System.out.println();
        } catch (Exception e) {
            System.out.println("false: Exception");
        }
    }

    public static void shuffleTest() {
        System.out.println("Tests for shuffleTest():");
        try {
            System.out.println("Test #1");
            Deck sortedDeck = new Deck();
            Deck shuffledDeck = new Deck();
            shuffledDeck.shuffle();
            int shuffledCards = 0;
            for (int i = 0; i < 52; i++){
                if (!sortedDeck.cardAt(i).equals(shuffledDeck.cardAt(i))){
                    shuffledCards++;
                }
            }
            System.out.println(shuffledCards % 2 == 0);

            System.out.println("Test #2 (which assumes atleast two cards were swapped)");
            System.out.println(shuffledCards > 0);
            System.out.println();
        } catch (Exception e) {
            System.out.println("false: Exception");
        }
    }

    public static void cutTest() {
        System.out.println("Tests for cutTest():");
        try {
            Deck uncutDeck = new Deck();
            Deck cutDeck = new Deck();
            cutDeck.cut();
            for (int i = 0; i < 52; i++) {
                if (!uncutDeck.cardAt(i).equals(cutDeck.cardAt((i+26)%52))) {
                    System.out.println("Test #1" + '\n' + "false");
                    return;
                }
            }
            System.out.println("Test #1" + '\n' + "true");
            System.out.println();
        } catch (Exception e){
            System.out.println("false: Exception");
        }
    }


}