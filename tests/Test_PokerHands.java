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
    private String[] handWithFullHouse = { "2H", "2H", "2H", "5H", "5H" };
    private String[] handWithFourOfAKind = { "1H", "2H", "2H", "2H", "2H" };
    private String[] handWithStraightFlush = { "QH", "JH", "TH", "KH", "AH" };

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void setUp() {
	new Main();

	// set up stream
	System.setOut(new PrintStream(outContent));
    }

    public void test_printWhichHandWins() {
	Main.printWhichHandWins(this.handWithStraightFlush,
		this.handWithFourOfAKind);
	assertEquals("Black wins.", outContent.toString());

	Main.printWhichHandWins(this.handWithFlush, this.handWithFullHouse);
	assertEquals("White wins.", outContent.toString());
    }

    public void test_getHandValue() {
	assertEquals(8, Main.getHandValue(this.handWithStraightFlush));
	assertEquals(7, Main.getHandValue(this.handWithFourOfAKind));
	assertEquals(6, Main.getHandValue(this.handWithFullHouse));
	assertEquals(5, Main.getHandValue(this.handWithFlush));
	assertEquals(4, Main.getHandValue(this.handWithStraight));
	assertEquals(3, Main.getHandValue(this.handWith3OfAKind));
	assertEquals(2, Main.getHandValue(this.handWith2Pair));
	assertEquals(1, Main.getHandValue(this.handWith1Pair));
	assertEquals(0, Main.getHandValue(this.handWithoutPair));
    }

    public void test_isStraightFlush() {
	assertTrue(Main.isStraightFlush(this.handWithStraightFlush));
	assertFalse(Main.isStraightFlush(this.handWithFourOfAKind));
	assertFalse(Main.isStraightFlush(this.handWithFullHouse));
	assertFalse(Main.isStraightFlush(this.handWithFlush));
	assertFalse(Main.isStraightFlush(this.handWithStraight));
	assertFalse(Main.isStraightFlush(this.handWith3OfAKind));
	assertFalse(Main.isStraightFlush(this.handWith2Pair));
	assertFalse(Main.isStraightFlush(this.handWith1Pair));
	assertFalse(Main.isStraightFlush(this.handWithoutPair));
    }

    public void test_isFourOfAKind() {
	assertTrue(Main.isFourOfAKind(this.handWithFourOfAKind));
	assertFalse(Main.isFourOfAKind(this.handWithFullHouse));
	assertFalse(Main.isFourOfAKind(this.handWithFlush));
	assertFalse(Main.isFourOfAKind(this.handWithStraight));
	assertFalse(Main.isFourOfAKind(this.handWith3OfAKind));
	assertFalse(Main.isFourOfAKind(this.handWith2Pair));
	assertFalse(Main.isFourOfAKind(this.handWith1Pair));
	assertFalse(Main.isFourOfAKind(this.handWithoutPair));
    }

    public void test_isFullHouse() {
	assertTrue(Main.isFullHouse(this.handWithFullHouse));
	assertFalse(Main.isFullHouse(this.handWithFlush));
	assertFalse(Main.isFullHouse(this.handWithStraight));
	assertFalse(Main.isFullHouse(this.handWith3OfAKind));
	assertFalse(Main.isFullHouse(this.handWith2Pair));
	assertFalse(Main.isFullHouse(this.handWith1Pair));
	assertFalse(Main.isFullHouse(this.handWithoutPair));
    }

    public void test_isFlush() {
	assertFalse(Main.has3OfAKind(this.handWith1Pair));
	assertFalse(Main.has3OfAKind(this.handWith2Pair));
	assertFalse(Main.has3OfAKind(this.handWith3OfAKind));
	assertFalse(Main.has3OfAKind(this.handWithStraight));
	assertTrue(Main.has3OfAKind(this.handWithFlush));
	assertFalse(Main.has3OfAKind(this.handWithFullHouse));
	assertFalse(Main.has3OfAKind(this.handWithFourOfAKind));
	assertTrue(Main.has3OfAKind(this.handWithStraightFlush));
    }

    public void test_isStraight() {
	assertFalse(Main.has3OfAKind(this.handWith1Pair));
	assertFalse(Main.has3OfAKind(this.handWith2Pair));
	assertFalse(Main.has3OfAKind(this.handWith3OfAKind));
	assertTrue(Main.has3OfAKind(this.handWithStraight));
	assertFalse(Main.has3OfAKind(this.handWithFlush));
	assertFalse(Main.has3OfAKind(this.handWithFullHouse));
	assertFalse(Main.has3OfAKind(this.handWithFourOfAKind));
	assertTrue(Main.has3OfAKind(this.handWithStraightFlush));
    }

    public void test_has3OfAKind() {
	assertFalse(Main.has3OfAKind(this.handWith1Pair));
	assertFalse(Main.has3OfAKind(this.handWith2Pair));
	assertTrue(Main.has3OfAKind(this.handWith3OfAKind));
	assertFalse(Main.has3OfAKind(this.handWithStraight));
	assertFalse(Main.has3OfAKind(this.handWithFlush));
	assertTrue(Main.has3OfAKind(this.handWithFullHouse));
	assertFalse(Main.has3OfAKind(this.handWithFourOfAKind));
	assertFalse(Main.has3OfAKind(this.handWithStraightFlush));
    }

    public void test_remove3OfAKind() {
	// TODO:
    }

    public void test_get3OfAKind() {
	assertEquals(2, Main.get3OfAKind(this.handWith3OfAKind));
	assertEquals(0, Main.get3OfAKind(this.handWithFourOfAKind));
	assertEquals(2, Main.get3OfAKind(this.handWithFullHouse));
    }

    public void test_has2Pair() {
	assertTrue(Main.has2Pair(this.handWith2Pair));
	assertFalse(Main.has2Pair(this.handWith1Pair));
	assertFalse(Main.has2Pair(this.handWithoutPair));
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
	assertTrue(Main.hasPair(this.handWith1Pair));
	assertFalse(Main.hasPair(this.handWithoutPair));
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
