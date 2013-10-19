/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 5, 2013
 */
public class ternarianweightsTest extends junit.framework.TestCase {

    public void setUp() {
	new ternarianweights();
	ternarianweights.constructPowerOf3Array();
    }

    public void test_findIndexOfClosestPowerOf3To() {
	assertEquals(0, ternarianweights.findIndexOfClosestPowerOf3To(1));
	assertEquals(1, ternarianweights.findIndexOfClosestPowerOf3To(2));
	assertEquals(1, ternarianweights.findIndexOfClosestPowerOf3To(3));
	assertEquals(1, ternarianweights.findIndexOfClosestPowerOf3To(4));
	assertEquals(1, ternarianweights.findIndexOfClosestPowerOf3To(5));
	assertEquals(2, ternarianweights.findIndexOfClosestPowerOf3To(6));
	assertEquals(2, ternarianweights.findIndexOfClosestPowerOf3To(7));
	assertEquals(2, ternarianweights.findIndexOfClosestPowerOf3To(8));
	assertEquals(2, ternarianweights.findIndexOfClosestPowerOf3To(9));
	assertEquals(3, ternarianweights.findIndexOfClosestPowerOf3To(21));
	assertEquals(3, ternarianweights.findIndexOfClosestPowerOf3To(27));
	// ...
	assertEquals(19, ternarianweights.findIndexOfClosestPowerOf3To(1000000000));
	assertEquals(19, ternarianweights.findIndexOfClosestPowerOf3To(1162261467));
	assertEquals(19, ternarianweights.findIndexOfClosestPowerOf3To(1200000000));
    }

    /**
     * 1
     * 3
     * 9
     * 27
     * 81
     * 243
     * 729
     * 2187
     * 6561
     * 19683
     * 59049
     * 177147
     * 531441
     * 1594323
     * 4782969
     * 14348907
     * 43046721
     * 129140163
     * 387420489
     * 1162261467
     */
    public void test_get3ToThePowerOf() {
	assertEquals(1, ternarianweights.get3ToThePowerOf(0));
	assertEquals(3, ternarianweights.get3ToThePowerOf(1));
	assertEquals(9, ternarianweights.get3ToThePowerOf(2));
	assertEquals(27, ternarianweights.get3ToThePowerOf(3));
	assertEquals(81, ternarianweights.get3ToThePowerOf(4));
	assertEquals(243, ternarianweights.get3ToThePowerOf(5));
	assertEquals(729, ternarianweights.get3ToThePowerOf(6));
	assertEquals(2187, ternarianweights.get3ToThePowerOf(7));
	// ...
	assertEquals(387420489, ternarianweights.get3ToThePowerOf(18));
	assertEquals(1162261467, ternarianweights.get3ToThePowerOf(19));
    }

    public void test_recursiveWeightAdd() {
	ternarianweights.leftScaleWeight = 2;
	ternarianweights.rightScaleWeight = 0;
	ternarianweights.recursiveWeightAdd();
	assertEquals(3, ternarianweights.leftScaleWeight);
	assertEquals(3, ternarianweights.rightScaleWeight);

	ternarianweights.leftScaleWeight = 3;
	ternarianweights.rightScaleWeight = 0;
	ternarianweights.recursiveWeightAdd();
	assertEquals(3, ternarianweights.leftScaleWeight);
	assertEquals(3, ternarianweights.rightScaleWeight);

	ternarianweights.leftScaleWeight = 21;
	ternarianweights.rightScaleWeight = 0;
	ternarianweights.recursiveWeightAdd();
	assertEquals(30, ternarianweights.leftScaleWeight);
	assertEquals(30, ternarianweights.rightScaleWeight);

	ternarianweights.leftScaleWeight = 250;
	ternarianweights.rightScaleWeight = 0;
	ternarianweights.recursiveWeightAdd();
	assertEquals(253, ternarianweights.leftScaleWeight);
	assertEquals(253, ternarianweights.rightScaleWeight);
    }
}
