package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import code.Instance;
import code.Node;
import code.TreeBuilder;

/**
 * CS39440 Major Project: Learning From Experience TreeBuilderTest.java Purpose:
 * A class to test the functionality of the methods belonging to
 * TreeBuilder.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class TreeBuilderTest {

	Node node;
	ArrayList<Instance> instances;
	TreeBuilder treeBuilder;

	/**
	 * Instances need to be created to test some of this classes functionality.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		node = new Node();
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
		treeBuilder = new TreeBuilder(instances);
		node.setOwnData(instances);
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
	 * Method checks if all instances belonging to a node are of the same
	 * classification. First fail the test then remove the instances causing the
	 * problem.
	 */
	@Test
	public void testAllSameClassification() {
		assertFalse(treeBuilder.allSameClassification(instances));
		instances.remove(0);
		instances.remove(0);
		assertTrue(treeBuilder.allSameClassification(instances));
	}

	@Test
	public void testGetNumberOfVotes() {
		int numberOfRepublicans = treeBuilder.getNumberOfVotes(instances, 1);
		int numberOfDemocrats = treeBuilder.getNumberOfVotes(instances, 2);

		assertEquals(2, numberOfRepublicans);
		assertEquals(3, numberOfDemocrats);
	}

	@Test
	public void testAssignLabel() {
		treeBuilder.assignLabel(node, instances);
		assertEquals("Democrat", node.getLabel());
	}
}
