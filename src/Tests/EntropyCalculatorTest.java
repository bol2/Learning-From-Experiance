package tests;

import code.AttributeGetter;
import code.EntropyCalculator;
import code.Instance;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * CS39440 Major Project: Learning From Experience EntropyCalculatorTest.java Purpose:
 * A class to test the functionality of the methods belonging to
 * EntropyCalculator.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class EntropyCalculatorTest {

	private EntropyCalculator entropyCalculator;
	private ArrayList<Instance> instances;

	/**
	 * To properly test some of the methods a small array list of test instances
	 * must be created. These will be used when calculating entropy and
	 * information gain.
	 */
	@Before
	public void setUp() {
		entropyCalculator = new EntropyCalculator();
		instances = new ArrayList<Instance>();
		Instance i1 = new Instance();
		Instance i2 = new Instance();
		Instance i3 = new Instance();
		Instance i4 = new Instance();
		Instance i5 = new Instance();

		// for votes: 1 = "y", 2 = "n", 3 = "?", for classification: 1 =
		// "Republican", 2 = "Democrat"
		setInstances(i1, 1, 2, 1, 2, 1, 1, 1, 2, 2, 2, 1, 3, 1, 1, 1, 2, 1);
		setInstances(i2, 1, 2, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 3);
		setInstances(i3, 2, 3, 1, 1, 3, 1, 1, 2, 2, 2, 2, 1, 2, 1, 1, 2, 2);
		setInstances(i4, 2, 2, 1, 1, 2, 3, 1, 2, 2, 2, 2, 1, 2, 1, 2, 2, 1);
		setInstances(i5, 2, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 1, 3, 1, 1, 1, 1);

		instances.add(i1);
		instances.add(i2);
		instances.add(i3);
		instances.add(i4);
		instances.add(i5);
	}

	/**
	 * Method for assigning data to test instances.
	 */
	private void setInstances(Instance instance, int classification, int bit1, int bit2, int bit3, int bit4, int bit5,
			int bit6, int bit7, int bit8, int bit9, int bit10, int bit11, int bit12, int bit13, int bit14, int bit15,
			int bit16) {
		instance.setAttributeValue(0, classification);
		instance.setAttributeValue(1, bit1);
		instance.setAttributeValue(2, bit2);
		instance.setAttributeValue(3, bit3);
		instance.setAttributeValue(4, bit4);
		instance.setAttributeValue(5, bit5);
		instance.setAttributeValue(6, bit6);
		instance.setAttributeValue(7, bit7);
		instance.setAttributeValue(8, bit8);
		instance.setAttributeValue(9, bit9);
		instance.setAttributeValue(10, bit10);
		instance.setAttributeValue(11, bit11);
		instance.setAttributeValue(12, bit12);
		instance.setAttributeValue(13, bit13);
		instance.setAttributeValue(14, bit14);
		instance.setAttributeValue(15, bit15);
		instance.setAttributeValue(16, bit16);
	}

	/**
	 * Test that the entropy value can be manually set to 0 and retrieved.
	 */
	@Test
	public void testSetEntropy() {
		double entropy;
		entropyCalculator.setEntropy(10);
		entropy = entropyCalculator.getEntropy();
		assertEquals(entropyCalculator.getEntropy(), entropy, 0);
		entropyCalculator.setEntropy(5);
		entropy = entropyCalculator.getEntropy();
		assertEquals(entropyCalculator.getEntropy(), entropy, 0);
		entropyCalculator.setEntropy(0);
		entropy = entropyCalculator.getEntropy();
		assertEquals(entropyCalculator.getEntropy(), entropy, 0);

	}

	/**
	 * Test that unknown attribute values are removed from an array of
	 * instances.
	 */
	@Test
	public void testCheckAndAssignValue() {
		AttributeGetter attributeGetter = new AttributeGetter(15);
		entropyCalculator.checkAndAssignValue(instances, attributeGetter.getAttributefromValue());
		for (Instance instance : instances) {
			int value = instance.getAttributeValue(15);
			if (value == 3) {
				fail();
			}
		}
		attributeGetter = new AttributeGetter(0);
		entropyCalculator.checkAndAssignValue(instances, attributeGetter.getAttributefromValue());
		for (Instance instance : instances) {
			int value = instance.getAttributeValue(0);
			if (value == 3) {
				fail();
			}
		}
		attributeGetter = new AttributeGetter(11);
		entropyCalculator.checkAndAssignValue(instances, attributeGetter.getAttributefromValue());
		for (Instance instance : instances) {
			int value = instance.getAttributeValue(11);
			if (value == 3) {
				fail();
			}
		}
	}

	/**
	 * Test that entropy is calculated for a given array list of instances.
	 */
	@Test
	public void testCalculateEntropy() {
		entropyCalculator.setEntropy(0);
		entropyCalculator.calculateEntropy(instances);

		double entropy = 0;

		double dataSetSize = instances.size();
		int numberOfRepublicans = 0;
		int numberOfDemocrats = 0;

		for (int instance = 0; instance < instances.size(); instance++) {
			int thisClassification = instances.get(instance).getClassification();
			if (thisClassification == 1)
				numberOfRepublicans++;
			else if (thisClassification == 2)
				numberOfDemocrats++;
		}

		entropy += -numberOfRepublicans / dataSetSize * (Math.log(numberOfRepublicans / dataSetSize) / Math.log(2));
		entropy += -numberOfDemocrats / dataSetSize * (Math.log(numberOfDemocrats / dataSetSize) / Math.log(2));

		assertEquals(entropy, entropyCalculator.getEntropy(), 5);
	}

}
