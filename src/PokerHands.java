import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110303&format=html
 *
 * The following is a solution for the above problem.
 *
 * TODO: The following solution is correct but needs to be made object oriented
 * with a private Hand class that holds it's hand value and a list of it's
 * remaining high cards.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Jason Riddle (jr1285@vt.edu)
 * @version Sept 26, 2013
 */
public class PokerHands {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	String[] hand1 = new String[5];
	String[] hand2 = new String[5];
	while (scanner.hasNextLine()) {
	    StringTokenizer stringTokenizer = new StringTokenizer(
		    scanner.nextLine());
	    while (stringTokenizer.hasMoreTokens()) {
		for (int i = 0; i < hand1.length; i++) {
		    hand1[i] = stringTokenizer.nextToken();
		}
		for (int i = 0; i < hand2.length; i++) {
		    hand2[i] = stringTokenizer.nextToken();
		}
	    }
	    printWhichHandWins(hand1, hand2);
	}
    }

    public static void printWhichHandWins(String[] hand1, String[] hand2) {
	int hand1Value = getHandValue(hand1);
	int hand2Value = getHandValue(hand2);

	if (hand1Value > hand2Value) {
	    System.out.println("Black wins.");
	} else if (hand1Value == hand2Value) {
	    if (hand1Value == 6 && hand2Value == 6) { // when both hands are a full house
		// Get Highest Card in Three of a Kind
		int highHand1Value = get3OfAKind(hand1);
		int highHand2Value = get3OfAKind(hand2);
		if (highHand1Value > highHand2Value) {
		    System.out.println("Black wins.");
		} else {
		    System.out.println("White wins.");
		}
	    } else {
		// all hands have had their valuable cards removed to make
		// both hands have a hand value of 0
		if (getHigherHandWithOnlyHighCards(hand1, hand2) == 1) {
		    System.out.println("Black wins.");
		} else if (getHigherHandWithOnlyHighCards(hand1, hand2) == 2) {
		    System.out.println("White wins.");
		} else { // returned 0
		    System.out.println("Tie.");
		}
	    }
	} else {
	    System.out.println("White wins.");
	}
    }

    /**
     * @return 0 if hand1 ties hand2, 1 if hand1 beats hand2, or 2 if hand1
     *         loses to hand2.
     */
    static int getHigherHandWithOnlyHighCards(String[] hand1, String[] hand2) {
	List<Integer> hand1CardNumbers = new ArrayList<Integer>(5);
	List<Integer> hand2CardNumbers = new ArrayList<Integer>(5);

	for (int i = 0; i < hand1.length; i++) {
	    hand1CardNumbers.add(new Integer(getNumberOfCard(hand1[i])));
	    hand2CardNumbers.add(new Integer(getNumberOfCard(hand2[i])));
	}
	// sort both arraylists
	Collections.sort(hand1CardNumbers);
	Collections.sort(hand2CardNumbers);

	// iterate through the arraylist to starting at the highest card
	// within the hand
	for (int i = hand1CardNumbers.size()-1; i >= 0; i--) {
	    if (hand1CardNumbers.get(i) > hand2CardNumbers.get(i)) {
		return 1;
	    } else if (hand1CardNumbers.get(i) < hand2CardNumbers.get(i)) {
		return 2;
	    } else {
		// do nothing if equal to each other
	    }
	}
	return 0;
    }

    static int getHighCardWithinHand(String[] hand) {
	// remove all cards except the highest card
	int highCard = 1;
	for (int i = 0; i < hand.length; i++) {
	    if (getNumberOfCard(hand[i]) > highCard) {
		highCard = getNumberOfCard(hand[i]);
	    }
	}
	return highCard;
    }

    static int removeHighCardWithinHand(String[] hand) {
	int highCard = getHighCardWithinHand(hand);
	if (highCard != 0) {
	    // there is a high card to be removed
	    for (int i = 0; i < hand.length; i++) {
		if (getNumberOfCard(hand[i]) == highCard) {
		    hand[i] = "00";
		}
	    }
	    return highCard;
	} else {
	    return highCard;
	}
    }

    static int getHandValue(String[] hand) {
	if (isStraightFlush(hand)) {
	    return 8;
	} else if (isFourOfAKind(hand)) {
	    // remove 4 of a kind from hand
	    remove4OfAKindInHand(hand);
	    return 7;
	} else if (isFullHouse(hand)) {
	    return 6;
	} else if (isFlush(hand)) {
	    return 5;
	} else if (isStraight(hand)) {
	    return 4;
	} else if (has3OfAKind(hand)) {
	    // save highest card
	    remove3OfAKind(hand);
	    return 3;
	} else if (has2Pair(hand)) {
	    // save highest card by
	    // removing 2 pair from hand
	    removeHighestPairInHand(hand);
	    removeHighestPairInHand(hand);
	    return 2;
	} else if (hasPair(hand)) {
	    removeHighestPairInHand(hand);
	    return 1;
	} else {
	    return 0; // highcard
	}
    }

    static boolean isStraightFlush(String[] hand) {
	if (isStraight(hand) && isFlush(hand)) {
	    return true;
	} else {
	    return false;
	}
    }

    static boolean isFourOfAKind(String[] hand) {
	if (get4OfAKind(hand) != 0) {
	    return true;
	} else {
	    return false;
	}
    }

    static void remove4OfAKindInHand(String[] hand) {
	int cardValue = get4OfAKind(hand);
	// remove 4 of a kind from this hand
	// i+1 is the the 4 of a kind
	for (int j = 0; j < hand.length; j++) {
	    if (getNumberOfCard(hand[j]) == cardValue) {
		// remove card from consideration by setting it to 00
		hand[j] = "00";
	    } else {
		// do nothing leaving the remaining card
	    }
	}
    }

    static int get4OfAKind(String[] hand) {
	int[] cardValues = new int[14]; // 14 different card values
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}

	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 4) {
		return i + 1;
	    }
	}
	return 0;
    }

    static boolean isFullHouse(String[] hand) {
	if (has3OfAKind(hand) && hasPair(hand)) {
	    return true;
	} else {
	    return false;
	}
    }

    static boolean isFlush(String[] hand) {
	String suit = getSuit(hand[0]);
	for (int i = 1; i < hand.length; i++) {
	    if (!getSuit(hand[i]).equals(suit)) {
		return false;
	    }
	}
	return true;
    }

    static boolean isStraight(String[] hand) {
	int[] cardValues = new int[14];
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}
	boolean has5ConsecutiveNumbers = true; // assume true first
	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 1 && (i + 4) < cardValues.length) {
		for (int j = i; j < (i + 5); j++) {
		    if (cardValues[j] != 1) {
			has5ConsecutiveNumbers = false;
		    }
		}
		if (has5ConsecutiveNumbers) {
		    return true;
		}
	    }
	}
	return false;
    }

    static boolean has3OfAKind(String[] hand) {
	if (get3OfAKind(hand) != 0) {
	    return true;
	} else {
	    return false;
	}
    }

    static void remove3OfAKind(String[] hand) {
	int cardValue = get3OfAKind(hand);
	// remove 3 of a kind from this hand
	// i+1 is the the three of a kind
	for (int j = 0; j < hand.length; j++) {
	    if (getNumberOfCard(hand[j]) == cardValue) {
		// remove card from consideration by setting it to 00
		hand[j] = "00";
	    } else {
		// do nothing leaving the remaining card
	    }
	}
    }

    static int get3OfAKind(String[] hand) {
	int[] cardValues = new int[14]; // 14 different card values
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}

	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 3) {
		return i + 1;
	    }
	}
	return 0;
    }

    static boolean has2Pair(String[] hand) {
	int numberOfPairs = 0;
	int[] cardValues = new int[14]; // 14 different card values
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}
	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 2) {
		numberOfPairs++;
	    }
	}

	if (numberOfPairs == 2) {
	    return true;
	} else {
	    return false;
	}
    }

    static boolean removeHighestPairInHand(String[] hand) {
	int valueOfPair = 0;

	int[] cardValues = new int[14];
	for (int i = 0; i < hand.length; i++) {
	    // if cards have already been removed from the hand
	    // they are set to 0
	    if (getNumberOfCard(hand[i]) == 0) {
		// do nothing
	    } else {
		cardValues[getNumberOfCard(hand[i]) - 1]++;
		// cardValues[i] == 1 means that the number (i + 1) appeared
		// twice in the hand
		if (cardValues[i] == 2) { // when a pair has occured
		    valueOfPair = i + 1;
		}
	    }
	}

	if (valueOfPair != 0) {
	    // there is a pair to be removed
	    for (int i = 0; i < hand.length; i++) {
		if (getNumberOfCard(hand[i]) == valueOfPair) {
		    hand[i] = "00";
		}
	    }
	    return true;
	} else {
	    return false;
	}
    }

    static boolean hasPair(String[] hand) {
	int numberOfPairs = 0;
	int[] cardValues = new int[14]; // 14 different card values
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}

	int pairNumber = 0;
	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 2) {
		pairNumber = i + 1;
		numberOfPairs++;
	    }
	}

	if (numberOfPairs >= 1) {
	    return true;
	} else {
	    return false;
	}
    }

    static int getNumberOfCard(String card) {
	if (card.contains("A")) {
	    return 14;
	} else if (card.contains("K")) {
	    return 13;
	} else if (card.contains("Q")) {
	    return 12;
	} else if (card.contains("J")) {
	    return 11;
	} else if (card.contains("T")) {
	    return 10;
	} else {
	    // example card = 2H
	    return Integer.parseInt(card.substring(0, 1));
	}
    }

    static String getSuit(String card) {
	return card.substring(1, 2);
    }
}
