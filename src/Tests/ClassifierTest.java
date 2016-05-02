package tests;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import code.Classifier;
import code.Instance;
import code.Node;

/**
 * CS39440 Major Project: Learning From Experience ClassifierTest.java Purpose:
 * A class to test the functionality of the methods belonging to Classifier.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class ClassifierTest {

	private ArrayList<Instance> instances;
	private Classifier classifier;
	private Node node;

	/**
	 * To properly test some of the methods a small array list of test instances
	 * must be created that will belong to a node.
	 */
	@Before
	public void setUp() {
		instances = new ArrayList<Instance>();
		classifier = new Classifier();
		node = new Node();
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

		node.setOwnData(instances);
		node.setLabel("Democrat");
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
	 * Method required to test that the RemoveClassifiedData method is only
	 * removing the classified data and not the training data from a given node.
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRemoveClassifiedData()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = classifier.getClass().getDeclaredField("originalRoot");
		field.setAccessible(true);
		Node original = new Node();
		original = (Node) field.get(classifier);
		assertEquals(435, original.getOwnData().size());

		Field testDataField = classifier.getClass().getDeclaredField("testData");
		testDataField.setAccessible(true);
		ArrayList<Instance> testData = new ArrayList<Instance>();
		testData = (ArrayList<Instance>) testDataField.get(classifier);

		int sizeOfTestData = testData.size();

		int sizeOfExpectedArrayList = original.getOwnData().size() - sizeOfTestData;

		classifier.removeClassifiedData(original);

		assertEquals(sizeOfExpectedArrayList, original.getOwnData().size());

	}

	/**
	 * Two of the instances created for these tests are republican, three are
	 * democrats. The label of the node should be democrat. Therefore, 2 will be
	 * placed incorrectly.
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testCalcIncorrectlyPlaced()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = classifier.getClass().getDeclaredField("incorrectlyClassified");
		field.setAccessible(true);
		field.set(classifier, 0);
		for (Instance instance : node.getOwnData()) {
			classifier.calcIncorrectlyPlaced(node, instance);
		}
		double fieldValue = (double) field.get(classifier);
		assertEquals(2, fieldValue, 0);
	}
}
