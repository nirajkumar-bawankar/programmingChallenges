import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110303&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sept 21, 2013
 */
public class Main {
    private static int hand1HighCard;
    private static int hand2HighCard;

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
	    // TODO:

	    // special case of full house

	    System.out.println("Tie.");
	} else {
	    System.out.println("White wins.");
	}
    }

    static int getIndexOfHighCardWithinHand(String[] hand) {
	// remove all cards except the highest card
	int highCard = 1;
	int highCardIndex = 0;
	for (int i = 0; i < hand.length; i++) {
	    if (getNumberOfCard(hand[i]) > highCard) {
		highCard = getNumberOfCard(hand[i]);
		highCardIndex = i;
	    }
	}
	return highCardIndex;
    }

    static int getHandValue(String[] hand) {
	if (isStraightFlush(hand)) {
	    // remove all cards except highest card
	    return 8;
	} else if (isFourOfAKind(hand)) {
	    // remove 4 of a kind from hand
	    return 7;
	} else if (isFullHouse(hand)) {
	    return 6;
	} else if (isFlush(hand)) {
	    // remove all cards except highest card
	    return 5;
	} else if (isStraight(hand)) {
	    // save only highest card
	    return 4;
	} else if (has3OfAKind(hand)) {
	    // save highest card
	    return 3;
	} else if (has2Pair(hand)) {
	    // save highest card
	    return 2;
	} else if (hasPair(hand)) {
	    return 1;
	} else {
	    return 0; // highcard
	}
    }

    static boolean isStraightFlush(String[] hand) {
	if (isStraight(hand) && isFlush(hand)) {
	    int highCardindex = getIndexOfHighCardWithinHand(hand);
	    // remove all cards in the hand except the largest card to compare
	    // against other straight flush hands
	    for (int i = 0; i < hand.length; i++) {
		if (i == highCardindex) {
		    // do nothing
		} else {
		    // remove card from consideration by setting it to 00
		    hand[i] = "00";
		}
	    }
	    return true;
	} else {
	    return false;
	}
    }

    static boolean isFourOfAKind(String[] hand) {
	int[] cardValues = new int[14]; // 14 different card values
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}

	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 4) {
		// i+1 is the the four of a kind
		for (int j = 0; j < hand.length; j++) {
		    if (j == (i + 1)) {
			// remove card from consideration by setting it to 00
			hand[j] = "00";
		    } else {
			// do nothing leaving the remaining card
		    }
		}
		return true;
	    }
	}
	return false;
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
	for (String card : hand) {
	    if (!getSuit(card).equals(suit)) {
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
	int[] cardValues = new int[14]; // 14 different card values
	for (String card : hand) {
	    cardValues[getNumberOfCard(card) - 1]++;
	}

	for (int i = 0; i < cardValues.length; i++) {
	    if (cardValues[i] == 3) {
		// remove 3 of a kind from this hand
		// i+1 is the the three of a kind
		for (int j = 0; j < hand.length; j++) {
		    if (j == (i + 1)) {
			// remove card from consideration by setting it to 00
			hand[j] = "00";
		    } else {
			// do nothing leaving the remaining card
		    }
		}
		return true;
	    }
	}
	return false;
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
	    // remove 2 pair from hand
	    removeOnePairInHand(hand);
	    removeOnePairInHand(hand);
	    return true;
	} else {
	    return false;
	}
    }

    static boolean removeOnePairInHand(String[] hand) {
	int valueOfPair = 0;

	int[] cardValues = new int[14];
	for (int i = 0; i < hand.length; i++) {
	    cardValues[getNumberOfCard(hand[i]) - 1]++;
	    // cardValues[i] == 1 means that the number (i + 1) appeared twice
	    // in the hand
	    if (cardValues[i] == 1) {
		valueOfPair = i + 1;
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
	    // remove single pair from hand with card value i + 1
	    removeOnePairInHand(hand);
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
