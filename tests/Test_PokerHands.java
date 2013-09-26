import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Tests all logic within class PokerHands.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 22, 2013
 */
public class Test_PokerHands extends junit.framework.TestCase {
    private String[] handWithoutPair = { "2H", "1D", "5S", "9C", "KD" };
    private String[] handWith1Pair = { "2H", "2D", "5S", "9C", "KD" };
    private String[] handWith2Pair = { "2H", "2D", "5S", "5C", "KD" };
    private String[] handWith3OfAKind = { "2H", "2D", "2S", "5C", "KD" };
    private String[] handWithStraight = { "2H", "3D", "4S", "5C", "6D" };
    private String[] handWithFlush = { "2H", "7H", "4H", "5H", "6H" };
    private String[] handWithFullHouse = { "2H", "2H", "2H", "5H", "5D" };
    private String[] handWith4OfAKind = { "1H", "2H", "2H", "2H", "2D" };
    private String[] handWithStraightFlush = { "QH", "JH", "TH", "KH", "AH" };

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void setUp() {
	new Main();

	// set up stream
	System.setOut(new PrintStream(outContent));
    }

    public void test_printBlackWins() {
	Main.printWhichHandWins(this.handWithStraightFlush,
		this.handWith4OfAKind);
	assertEquals("Black wins.", outContent.toString().trim());
    }

    public void test_printWhiteWins() {
	Main.printWhichHandWins(this.handWithFlush, this.handWithFullHouse);
	assertEquals("White wins.", outContent.toString().trim());
    }

    public void test_printTie() {
	Main.printWhichHandWins(this.handWithFlush, this.handWithFlush);
	assertEquals("Tie.", outContent.toString().trim());
    }

    public void test_getHigherHandWithOnlyHighCards() {
	String[] hand1 = {"2H", "3D", "5S", "9C", "KD"};
	String[] hand2 = {"2C", "3H", "4S", "8C", "KH"};
	assertEquals(1, Main.getHigherHandWithOnlyHighCards(hand1, hand2));
    }

    public void test_getHighCardWitinHand() {
	assertEquals(13, Main.getHighCardWithinHand(this.handWithoutPair));
    }

    public void test_removeHighCardWithinHand() {
	// TODO:
    }

    public void test_getHandValue() {
	assertEquals(8, Main.getHandValue(this.handWithStraightFlush));
	assertEquals(7, Main.getHandValue(this.handWith4OfAKind));
	assertEquals(6, Main.getHandValue(this.handWithFullHouse));
	assertEquals(5, Main.getHandValue(this.handWithFlush));
	assertEquals(4, Main.getHandValue(this.handWithStraight));
	assertEquals(3, Main.getHandValue(this.handWith3OfAKind));
	assertEquals(2, Main.getHandValue(this.handWith2Pair));
	assertEquals(1, Main.getHandValue(this.handWith1Pair));
	assertEquals(0, Main.getHandValue(this.handWithoutPair));
    }

    public void test_isStraightFlush() {
	assertFalse(Main.isStraightFlush(this.handWithoutPair));
	assertFalse(Main.isStraightFlush(this.handWith1Pair));
	assertFalse(Main.isStraightFlush(this.handWith2Pair));
	assertFalse(Main.isStraightFlush(this.handWith3OfAKind));
	assertFalse(Main.isStraightFlush(this.handWithStraight));
	assertFalse(Main.isStraightFlush(this.handWithFlush));
	assertFalse(Main.isStraightFlush(this.handWithFullHouse));
	assertFalse(Main.isStraightFlush(this.handWith4OfAKind));
	assertTrue(Main.isStraightFlush(this.handWithStraightFlush));
    }

    public void test_isFourOfAKind() {
	assertFalse(Main.isFourOfAKind(this.handWithoutPair));
	assertFalse(Main.isFourOfAKind(this.handWith1Pair));
	assertFalse(Main.isFourOfAKind(this.handWith2Pair));
	assertFalse(Main.isFourOfAKind(this.handWith3OfAKind));
	assertFalse(Main.isFourOfAKind(this.handWithStraight));
	assertFalse(Main.isFourOfAKind(this.handWithFlush));
	assertFalse(Main.isFourOfAKind(this.handWithFullHouse));
	assertTrue(Main.isFourOfAKind(this.handWith4OfAKind));
	assertFalse(Main.isFourOfAKind(this.handWithStraightFlush));
    }

    public void test_remove4OfAKindInHand() {
	Main.remove4OfAKindInHand(this.handWith4OfAKind);

	String[] handWith4OfAKindRemoved = { "1H", "00", "00", "00", "00" };
	for (int i = 0; i < this.handWith3OfAKind.length; i++) {
	    assertEquals(handWith4OfAKindRemoved[i], this.handWith4OfAKind[i]);
	}
    }

    public void test_get4OfAKind() {
	assertEquals(0, Main.get4OfAKind(this.handWithoutPair));
	assertEquals(0, Main.get4OfAKind(this.handWith1Pair));
	assertEquals(0, Main.get4OfAKind(this.handWith2Pair));
	assertEquals(0, Main.get4OfAKind(this.handWith3OfAKind));
	assertEquals(0, Main.get4OfAKind(this.handWithStraight));
	assertEquals(0, Main.get4OfAKind(this.handWithFlush));
	assertEquals(0, Main.get4OfAKind(this.handWithFullHouse));
	assertEquals(2, Main.get4OfAKind(this.handWith4OfAKind));
	assertEquals(0, Main.get4OfAKind(this.handWithStraightFlush));
    }

    public void test_isFullHouse() {
	assertFalse(Main.isFullHouse(this.handWithoutPair));
	assertFalse(Main.isFullHouse(this.handWith1Pair));
	assertFalse(Main.isFullHouse(this.handWith2Pair));
	assertFalse(Main.isFullHouse(this.handWith3OfAKind));
	assertFalse(Main.isFullHouse(this.handWithStraight));
	assertFalse(Main.isFullHouse(this.handWithFlush));
	assertTrue(Main.isFullHouse(this.handWithFullHouse));
	assertFalse(Main.isFullHouse(this.handWith4OfAKind));
	assertFalse(Main.isFullHouse(this.handWithStraightFlush));
    }

    public void test_isFlush() {
	assertFalse(Main.isFlush(this.handWithoutPair));
	assertFalse(Main.isFlush(this.handWith1Pair));
	assertFalse(Main.isFlush(this.handWith2Pair));
	assertFalse(Main.isFlush(this.handWith3OfAKind));
	assertFalse(Main.isFlush(this.handWithStraight));
	assertTrue(Main.isFlush(this.handWithFlush));
	assertFalse(Main.isFlush(this.handWithFullHouse));
	assertFalse(Main.isFlush(this.handWith4OfAKind));
	assertTrue(Main.isFlush(this.handWithStraightFlush));
    }

    public void test_isStraight() {
	assertFalse(Main.isStraight(this.handWithoutPair));
	assertFalse(Main.isStraight(this.handWith1Pair));
	assertFalse(Main.isStraight(this.handWith2Pair));
	assertFalse(Main.isStraight(this.handWith3OfAKind));
	assertTrue(Main.isStraight(this.handWithStraight));
	assertFalse(Main.isStraight(this.handWithFlush));
	assertFalse(Main.isStraight(this.handWithFullHouse));
	assertFalse(Main.isStraight(this.handWith4OfAKind));
	assertTrue(Main.isStraight(this.handWithStraightFlush));
    }

    public void test_has3OfAKind() {
	assertFalse(Main.has3OfAKind(this.handWithoutPair));
	assertFalse(Main.has3OfAKind(this.handWith1Pair));
	assertFalse(Main.has3OfAKind(this.handWith2Pair));
	assertTrue(Main.has3OfAKind(this.handWith3OfAKind));
	assertFalse(Main.has3OfAKind(this.handWithStraight));
	assertFalse(Main.has3OfAKind(this.handWithFlush));
	assertTrue(Main.has3OfAKind(this.handWithFullHouse));
	assertFalse(Main.has3OfAKind(this.handWith4OfAKind));
	assertFalse(Main.has3OfAKind(this.handWithStraightFlush));
    }

    public void test_remove3OfAKind() {
	Main.remove3OfAKind(this.handWith3OfAKind);

	String[] handWith3OfAKindRemoved = { "00", "00", "00", "5C", "KD" };
	for (int i = 0; i < this.handWith3OfAKind.length; i++) {
	    assertEquals(handWith3OfAKindRemoved[i], this.handWith3OfAKind[i]);
	}

	Main.remove3OfAKind(this.handWithFullHouse);
	String[] handWith3OfAKindRemoved2 = { "00", "00", "00", "5H", "5D" };
	for (int i = 0; i < this.handWith3OfAKind.length; i++) {
	    assertEquals(handWith3OfAKindRemoved2[i], this.handWithFullHouse[i]);
	}
    }

    public void test_get3OfAKind() {
	assertEquals(0, Main.get3OfAKind(this.handWithoutPair));
	assertEquals(0, Main.get3OfAKind(this.handWith1Pair));
	assertEquals(0, Main.get3OfAKind(this.handWith2Pair));
	assertEquals(2, Main.get3OfAKind(this.handWith3OfAKind));
	assertEquals(0, Main.get3OfAKind(this.handWithStraight));
	assertEquals(0, Main.get3OfAKind(this.handWithFlush));
	assertEquals(2, Main.get3OfAKind(this.handWithFullHouse));
	assertEquals(0, Main.get3OfAKind(this.handWith4OfAKind));
	assertEquals(0, Main.get3OfAKind(this.handWithStraightFlush));
    }

    public void test_has2Pair() {
	assertFalse(Main.has2Pair(this.handWithoutPair));
	assertFalse(Main.has2Pair(this.handWith1Pair));
	assertTrue(Main.has2Pair(this.handWith2Pair));
	assertFalse(Main.has2Pair(this.handWith3OfAKind));
	assertFalse(Main.has2Pair(this.handWithStraight));
	assertFalse(Main.has2Pair(this.handWithFlush));
	assertFalse(Main.has2Pair(this.handWithFullHouse));
	assertFalse(Main.has2Pair(this.handWith4OfAKind));
	assertFalse(Main.has2Pair(this.handWithStraightFlush));
    }

    public void test_removeHighestPairInHand() {
	Main.removeHighestPairInHand(this.handWith1Pair);

	String[] handWithAPairRemoved = { "00", "00", "5S", "9C", "KD" };
	for (int i = 0; i < this.handWith1Pair.length; i++) {
	    assertEquals(handWithAPairRemoved[i], this.handWith1Pair[i]);
	}

	Main.removeHighestPairInHand(this.handWith2Pair);
	String[] handWithAPairRemoved2 = { "2H", "2D", "00", "00", "KD" };
	for (int i = 0; i < this.handWith2Pair.length; i++) {
	    assertEquals(handWithAPairRemoved2[i], this.handWith2Pair[i]);
	}

	Main.removeHighestPairInHand(this.handWith2Pair);
	String[] handWithAPairRemoved3 = { "00", "00", "00", "00", "KD" };
	for (int i = 0; i < this.handWith2Pair.length; i++) {
	    assertEquals(handWithAPairRemoved3[i], this.handWith2Pair[i]);
	}
    }

    public void test_hasPair() {
	assertFalse(Main.hasPair(this.handWithoutPair));
	assertTrue(Main.hasPair(this.handWith1Pair));
	assertTrue(Main.hasPair(this.handWith2Pair));
	assertFalse(Main.hasPair(this.handWith3OfAKind));
	assertFalse(Main.hasPair(this.handWithStraight));
	assertFalse(Main.hasPair(this.handWithFlush));
	assertTrue(Main.hasPair(this.handWithFullHouse));
	assertFalse(Main.hasPair(this.handWith4OfAKind));
	assertFalse(Main.hasPair(this.handWithStraightFlush));
    }

    public void test_getNumberOfCard() {
	assertEquals(1, Main.getNumberOfCard("1H"));
	assertEquals(2, Main.getNumberOfCard("2H"));
	assertEquals(3, Main.getNumberOfCard("3H"));
	assertEquals(4, Main.getNumberOfCard("4H"));
	assertEquals(5, Main.getNumberOfCard("5H"));
	assertEquals(6, Main.getNumberOfCard("6H"));
	assertEquals(7, Main.getNumberOfCard("7H"));
	assertEquals(8, Main.getNumberOfCard("8H"));
	assertEquals(9, Main.getNumberOfCard("9H"));
	assertEquals(10, Main.getNumberOfCard("TH"));
	assertEquals(11, Main.getNumberOfCard("JH"));
	assertEquals(12, Main.getNumberOfCard("QH"));
	assertEquals(13, Main.getNumberOfCard("KH"));
	assertEquals(14, Main.getNumberOfCard("AH"));
    }

    public void test_getSuit() {
	// H = hearts, C = clubs, S = spades, D = diamonds
	assertEquals("H", Main.getSuit("1H"));
	assertEquals("C", Main.getSuit("1C"));
	assertEquals("S", Main.getSuit("1S"));
	assertEquals("D", Main.getSuit("1D"));
    }
}
