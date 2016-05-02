package tests;

import code.AttributeGetter;
import code.EntropyCalculator;
import code.Instance;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * CS39440 Major Project: Learning From Experience ClassifierTest.java Purpose:
 * A class to test the functionality of the methods belonging to EntropyCalculator.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class EntropyCalculatorTest {

	private EntropyCalculator entropyCalculator;
	private ArrayList<Instance> instances;

	@Before
	public void setUp() {
		entropyCalculator = new EntropyCalculator();
		instances = new ArrayList<Instance>();
		Instance i1 = new Instance();
		Instance i2 = new Instance();
		Instance i3 = new Instance();
		Instance i4 = new Instance();
		Instance i5 = new Instance();
		
		setInstances(i1,1,2,1,2,1,1,1,2,2,2,1,3,1,1,1,2,1);
		setInstances(i2,1,2,1,2,1,1,1,2,2,2,2,2,1,1,1,2,3);
		setInstances(i3,2,3,1,1,3,1,1,2,2,2,2,1,2,1,1,2,2);
		setInstances(i4,2,2,1,1,2,3,1,2,2,2,2,1,2,1,2,2,1);
		setInstances(i5,2,1,1,1,2,1,1,2,2,2,2,1,3,1,1,1,1);
		
		instances.add(i1);
		instances.add(i2);
		instances.add(i3);
		instances.add(i4);
		instances.add(i5);
	}

	private void setInstances(Instance i ,int classification, int bit1, int bit2, int bit3, int bit4, int bit5,
			int bit6, int bit7, int bit8, int bit9, int bit10, int bit11, int bit12, int bit13,
			int bit14, int bit15, int bit16) {
		i.setAttributeValue(0, classification);
		i.setAttributeValue(1, bit1);
		i.setAttributeValue(2, bit2);
		i.setAttributeValue(3, bit3);
		i.setAttributeValue(4, bit4);
		i.setAttributeValue(5, bit5);
		i.setAttributeValue(6, bit6);
		i.setAttributeValue(7, bit7);
		i.setAttributeValue(8, bit8);
		i.setAttributeValue(9, bit9);
		i.setAttributeValue(10, bit10);
		i.setAttributeValue(11, bit11);
		i.setAttributeValue(12, bit12);
		i.setAttributeValue(13, bit13);
		i.setAttributeValue(14, bit14);
		i.setAttributeValue(15, bit15);
		i.setAttributeValue(16, bit16);
	}

	@Test
	public void testSetEntropy() {
		double entropy;
		entropyCalculator.setEntropy(10);
		entropy = entropyCalculator.getEntropy();
		assertEquals(entropyCalculator.getEntropy(), entropy, 0);
		entropyCalculator.setEntropy(5);
		entropy = entropyCalculator.getEntropy();
		assertEquals(entropyCalculator.getEntropy(), entropy, 0);

	}
	
	@Test
	public void testCheckAndAssignValue() {
		AttributeGetter ag = new AttributeGetter(15);
		entropyCalculator.checkAndAssignValue(instances, ag.getAttributefromValue());
		
		for (Instance instance : instances){
			int value = instance.getAttributeValue(15);
			if (value == 3){
				fail();
			}
		}
		
		ag = new AttributeGetter(0);
		entropyCalculator.checkAndAssignValue(instances, ag.getAttributefromValue());
		for (Instance instance : instances){
			int value = instance.getAttributeValue(0);
			if (value == 3){
				fail();
			}
		}
		
		ag = new AttributeGetter(11);
		entropyCalculator.checkAndAssignValue(instances, ag.getAttributefromValue());
		for (Instance instance : instances){
			int value = instance.getAttributeValue(11);
			if (value == 3){
				fail();
			}
		}	
	}
	
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
