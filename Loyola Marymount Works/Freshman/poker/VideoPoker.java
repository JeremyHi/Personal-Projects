package poker;

import java.util.Scanner;

/**
 * A command line Video Poker simulation class. Guaranteed fun or your fake
 * digital money back! The program should be run like this:
 * java poker.VideoPoker <numberOfCredits>. After the program is run, the user
 * is prompted for a wager (which should be > 0 but less than his/her number of
 * credits). Five cards are then dealt from a deck and put in a hand. The user
 * has the option of holding any number of cards while the rest are discarded and
 * the same number then drawn. After that, the user should receive a payout based
 * on whatever poker hand he/she can make out of the five cards. Lastly, the user
 * is prompted whether he/she wants to play again if he/she has any credits left.
 */
public class VideoPoker {

    /**
     * Number of times the deck should be shuffled before each round.
     */
    private static final int SHUFFLE_NUMBER = 10;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java "
                    + "edu.lmu.edu.cs.akhayat.poker.VideoPoker <credits>");
        } else if (!isValidNumber(args[0])) {
            System.out.println("Credits must be a positive integer > 0.");
        } else {
            boolean playAgain = false;
            int credits = Integer.parseInt(args[0]);
            // Constructs a Scanner object which takes in user input.
            Scanner input = new Scanner(System.in);
            // The game loop. It loops while playAgain is true;
        
            do {
                // Feel free to tweak any messages.
                System.out.println("Credits: " + credits);
                System.out.println("Type in yeer bet, matey!");

                // input.nextLine() prompts the user for input and stores it in
                // a String object.
                String wagerInput = "";
                boolean test = false;
                while (!test) { 
                    wagerInput = input.nextLine(); 
                    try {
                        int wagerInputInt = Integer.parseInt(wagerInput);
                        if (wagerInputInt > credits || wagerInputInt <= 0) {
                           test = false;
                           System.out.println("Arg, yee cant be enterin in a number like that, matey!");
                        } else {
                            test = true;
                        }
                    }  catch(NumberFormatException e) {
                            System.out.println("Arg, yee cant be enterin in a number like that, matey!"); 
                            test = false;
                        }
                }
            //TODO: Handle the wager input and deal some cards.

            //Credits is reduced by wagerInput
                credits = credits - Integer.parseInt(wagerInput);



                Deck poker = new Deck(SHUFFLE_NUMBER);
                

            //Creates first FiveCardHand by adding top 5
            //cards to hand
                Card[] handHold = new Card[5];
            //'count' holds position of top card of deck
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    handHold[i] = poker.cardAt(count++);
                }
                FiveCardHand hand = new FiveCardHand(handHold);
                System.out.println("Here is your current hand, matey!");
                System.out.println(hand.toString());

                System.out.println("Type the numbers of the slots (1-5) you "
                        + "want to get rid of separated by spaces or commas...");
                System.out.println("Or press ENTER to discard them all.");


                String inputString = "";
                // This semi-complex line of code takes whatever string was given,
                // and splits it via comma and/or space. In other words, it takes
                // anything comma/space separated and puts it in an Array. For example,
                // if the user inputs "1, 2, 5" or "1 2 5", the array ["1", "2", "5"]
                // is created.
                String[] slots = new String[5];

                boolean inputTest = false;
                while (!inputTest) { 
                    inputString = input.nextLine();
                    slots = inputString.isEmpty() ? new String[0]
                        : inputString.split(", | |,");
                    try {
                        for (int i = 0; i < slots.length; i++ ) {
                            if (Integer.parseInt(slots[i]) <= 0) {
                                inputTest = false;
                                System.out.println("Arg, yee cant be enterin in a number like that, matey!");
                            } else {
                                inputTest = true;
                            }
                        } 
                    } catch(NumberFormatException e) {
                                System.out.println("Arg, yee cant be enterin in a number like that, matey!"); 
                                inputTest = false;
                            }
                }
                //TODO: Switch out the cards they didn't want to keep.
                //      Then classify their hand and give them money based on
                //      the payout method.


                
                for (int i = 0; i < slots.length; i++) {
                    hand.setCard(Integer.parseInt(slots[i])-1, poker.cardAt(count++));
                }

                







                /*
                if (slots.length != 0 && slots.length != 1) {
                    for (int m = 0; m < slots.length; m++) {
                        if (m == Integer.parseInt(slots[m])) {
                            handHold[m]= new Card(poker.cardAt(count).getRank(), poker.cardAt(count).getSuit());
                            count++;
                        }
                    }
                } else if (slots.length == 1) {
                    for (int p = 0; p < handHold.length; p++) {
                        if (p != Integer.parseInt(slots[0])) {
                            handHold[p]= new Card(poker.cardAt(count).getRank(), poker.cardAt(count).getSuit());
                            count++;
                        }
                    }
                } else if (slots.length == 0) {
                    for (int p = 0; p < handHold.length; p++) {
                        handHold[p]= new Card(poker.cardAt(count).getRank(), poker.cardAt(count).getSuit());
                        count++;
                    }
                }
                */


                hand = new FiveCardHand(handHold);
                System.out.println(hand.toString());

                //classify
                //payout
                //new credit total

                System.out.println(hand.classify());
                int money = payout(hand,Integer.parseInt(wagerInput));
                System.out.println("Ahoy! yer luck has changed, you won: " + money + " dubloons!");
                credits += Integer.parseInt(wagerInput) + money;
                System.out.println("Your new dubloon count has changed: " + credits);

                if (credits <= 0) {
                    playAgain = false;
                    System.out.println("Sorry, you're out of dough.");
                } else {
                    System.out.println("Play again? (y/n)");
                    String yesOrNo = input.nextLine();
                    if (yesOrNo.equalsIgnoreCase("y")) {
                        playAgain = true;
                    } else {
                        playAgain = false;
                    }
                    //TODO: Find out when to quit.
                }

            } while (playAgain);
            System.out.println("Thanks for playing!");
        }
    }

    /**
     * Returns true if the given string is a valid integer greater than 0.
     */
    private static boolean isValidNumber(String text) {
        try {
            return Integer.parseInt(text) > 0;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns the the payout for the given hand and wager.
     * You can fiddle with the numbers if you want.
     */
    private static int payout(FiveCardHand hand, int wager) {
        switch(hand.classify()) {
            case STRAIGHT_FLUSH:  return wager * 50;
            case FOUR_OF_A_KIND:  return wager * 25;
            case FULL_HOUSE:      return wager * 15;
            case FLUSH:           return wager * 10;
            case STRAIGHT:        return wager * 5;
            case THREE_OF_A_KIND: return wager * 2;
            case TWO_PAIR:        return wager;
            case PAIR:            return wager / 2;
            default:              return 0;
        }
    }
}