package poker;

public class FiveCardHandTest {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_setCardAndGetCard();
        test_containsPair();
        test_containsTwoPair();
        test_containsThreeOfAKind();
        test_containsStraight();
        test_containsFlush();
        test_containsFullHouse();
        test_containsFourOfAKind();
        test_containsStraightFlush();
        test_classify();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    private static void test_setCardAndGetCard() {
        System.out.println("Testing getCard and setCard...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.FOUR, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.getCard(0).equals(new Card(Rank.ACE, Suit.CLUBS)));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        try {
            displaySuccessIfTrue(hand.getCard(4).equals(new Card(Rank.JACK, Suit.HEARTS)));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        try {
            displaySuccessIfTrue(hand.getCard(4).equals(new Card(Rank.JACK, Suit.HEARTS)));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        try {
            hand.setCard(2, new Card(Rank.JACK, Suit.SPADES));
            displaySuccessIfTrue(hand.getCard(2).equals(new Card(Rank.JACK, Suit.SPADES)));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        try {
            Card test = hand.getCard(1);
            test = new Card(Rank.FIVE, Suit.SPADES);
            displaySuccessIfTrue(hand.getCard(1).equals(new Card(Rank.DEUCE, Suit.CLUBS)));
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }
        System.out.println();
    }

    private static void test_containsPair() {
        System.out.println("Testing containsPair...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.FOUR, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsPair());
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }


        hand.setCard(3, new Card(Rank.ACE, Suit.SPADES));
        try {
            displaySuccessIfTrue(hand.containsPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.SPADES),
            new Card(Rank.KING, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.containsPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(2, new Card(Rank.JACK, Suit.SPADES));
        try {
            displaySuccessIfTrue(hand.containsPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(2, new Card(Rank.SEVEN, Suit.SPADES));
        try {
            displaySuccessIfTrue(!hand.containsPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }
        System.out.println();
    }

    private static void test_containsTwoPair() {
        System.out.println("Testing containsTwoPair...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.ACE, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsTwoPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(2, new Card(Rank.JACK, Suit.SPADES));
        try {
            displaySuccessIfTrue(hand.containsTwoPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.SEVEN, Suit.SPADES),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsTwoPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(2, new Card(Rank.JACK, Suit.SPADES));
        hand.setCard(3, new Card(Rank.DEUCE, Suit.SPADES));
        try {
            displaySuccessIfTrue(hand.containsTwoPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.DIAMONDS),
            new Card(Rank.FOUR, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.containsTwoPair());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }
        System.out.println();
    }

    private static void test_containsThreeOfAKind() {
        System.out.println("Testing containsThreeOfAKind...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.SPADES),
            new Card(Rank.ACE, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsThreeOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(4, new Card(Rank.DEUCE, Suit.CLUBS));
        try {
            displaySuccessIfTrue(hand.containsThreeOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.SEVEN, Suit.CLUBS),
            new Card(Rank.SIX, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsThreeOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(1, new Card(Rank.SIX, Suit.CLUBS));
        hand.setCard(4, new Card(Rank.SIX, Suit.HEARTS));
        try {
            displaySuccessIfTrue(hand.containsThreeOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(3, new Card(Rank.FOUR, Suit.HEARTS));
        try {
            displaySuccessIfTrue(hand.containsThreeOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        System.out.println();
    }

    private static void test_containsStraight() {
        System.out.println("Testing containsStraight...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.SIX, Suit.DIAMONDS),
            new Card(Rank.DEUCE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsStraight());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }


        hand.setCard(0, new Card(Rank.FIVE, Suit.CLUBS));
        try {
            displaySuccessIfTrue(hand.containsStraight());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.FIVE, Suit.DIAMONDS),
            new Card(Rank.DEUCE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.containsStraight());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.ACE, Suit.DIAMONDS),
            new Card(Rank.TEN, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.containsStraight());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(4, new Card(Rank.DEUCE, Suit.HEARTS));
        try {
            displaySuccessIfTrue(!hand.containsStraight());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }
        System.out.println();
    }

    private static void test_containsFlush() {
        System.out.println("Testing containsFlush...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.SIX, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.SPADES)
        });

        try {
            displaySuccessIfTrue(!hand.containsFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(4, new Card(Rank.FIVE, Suit.CLUBS));
        try {
            displaySuccessIfTrue(hand.containsFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.FOUR, Suit.HEARTS),
            new Card(Rank.NINE, Suit.HEARTS),
            new Card(Rank.THREE, Suit.HEARTS),
            new Card(Rank.SIX, Suit.DIAMONDS),
            new Card(Rank.DEUCE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(!hand.containsFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(3, new Card(Rank.FIVE, Suit.HEARTS));
        try {
            displaySuccessIfTrue(hand.containsFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(0, new Card(Rank.FIVE, Suit.SPADES));
        try {
            displaySuccessIfTrue(!hand.containsFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }
        System.out.println();
    }

    private static void test_containsFullHouse() {
        System.out.println("Testing containsFullHouse...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.SPADES)
        });

        try {
            displaySuccessIfTrue(!hand.containsFullHouse());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }


        hand.setCard(1, new Card(Rank.DEUCE, Suit.CLUBS));
        try {
            displaySuccessIfTrue(hand.containsFullHouse());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.SPADES)
        });

        try {
            displaySuccessIfTrue(!hand.containsFullHouse());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(2, new Card(Rank.KING, Suit.DIAMONDS));
        hand.setCard(4, new Card(Rank.ACE, Suit.DIAMONDS));
        hand.setCard(3, new Card(Rank.KING, Suit.HEARTS));
        try {
            displaySuccessIfTrue(hand.containsFullHouse());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }
        System.out.println();
    }

    private static void test_containsFourOfAKind() {
        System.out.println("Testing containsFourOfAKind...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.SPADES),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.HEARTS),
            new Card(Rank.DEUCE, Suit.SPADES)
        });

        try {
            displaySuccessIfTrue(!hand.containsFourOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(2, new Card(Rank.FOUR, Suit.DIAMONDS));
        try {
            displaySuccessIfTrue(hand.containsFourOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.ACE, Suit.HEARTS),
            new Card(Rank.DEUCE, Suit.SPADES)
        });

        try {
            displaySuccessIfTrue(!hand.containsFourOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(0, new Card(Rank.JACK, Suit.DIAMONDS));
        hand.setCard(2, new Card(Rank.JACK, Suit.HEARTS));
        hand.setCard(4, new Card(Rank.JACK, Suit.SPADES));
        try {
            displaySuccessIfTrue(hand.containsFourOfAKind());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }


        System.out.println();
    }

    private static void test_containsStraightFlush() {
        System.out.println("Testing containsStraightFlush...");
        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.TEN, Suit.SPADES),
            new Card(Rank.NINE, Suit.SPADES),
            new Card(Rank.DEUCE, Suit.SPADES)
        });

        try {
            displaySuccessIfTrue(!hand.containsStraightFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(4, new Card(Rank.SEVEN, Suit.SPADES));
        try {
            displaySuccessIfTrue(!hand.containsStraightFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand.setCard(4, new Card(Rank.EIGHT, Suit.DIAMONDS));
        try {
            displaySuccessIfTrue(!hand.containsStraightFlush());
        } catch(Exception exc) {
            displayFailure();
            exc.printStackTrace();
        }

        hand.setCard(4, new Card(Rank.EIGHT, Suit.SPADES));
        try {
            displaySuccessIfTrue(hand.containsStraightFlush());
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        System.out.println();
    }

    private static void test_classify() {
        System.out.println("Testing classify...");

        FiveCardHand hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.FOUR, Suit.DIAMONDS),
            new Card(Rank.TEN, Suit.SPADES),
            new Card(Rank.NINE, Suit.SPADES),
            new Card(Rank.DEUCE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.HIGH_CARD);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.ACE, Suit.DIAMONDS),
            new Card(Rank.QUEEN, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.PAIR);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.DEUCE, Suit.DIAMONDS),
            new Card(Rank.ACE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.TWO_PAIR);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.DEUCE, Suit.DIAMONDS),
            new Card(Rank.ACE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.THREE_OF_A_KIND);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.SEVEN, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.SIX, Suit.DIAMONDS),
            new Card(Rank.EIGHT, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.STRAIGHT);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.ACE, Suit.CLUBS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.FLUSH);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.CLUBS),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.DEUCE, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.FULL_HOUSE);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.DEUCE, Suit.SPADES),
            new Card(Rank.THREE, Suit.DIAMONDS),
            new Card(Rank.THREE, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.FOUR_OF_A_KIND);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        hand = new FiveCardHand(new Card[] {
            new Card(Rank.NINE, Suit.HEARTS),
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.EIGHT, Suit.HEARTS),
            new Card(Rank.TEN, Suit.HEARTS)
        });

        try {
            displaySuccessIfTrue(hand.classify() == PokerHand.STRAIGHT_FLUSH);
        } catch(Exception exc) {
            exc.printStackTrace();
            displayFailure();
        }

        System.out.println();
    }
}