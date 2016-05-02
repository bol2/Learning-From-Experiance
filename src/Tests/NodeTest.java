package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import code.Instance;
import code.Node;

/**
 * CS39440 Major Project: Learning From Experience NodeTest.java Purpose: A
 * class to test the functionality of the methods belonging to Node.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class NodeTest {

	private Node node;
	private Node rootNode;
	private Node childNode1;
	private Node childNode2;

	private ArrayList<Instance> instances;
	private Instance i1;
	private Instance i5;

	/**
	 * Several nodes must be created to check parent and child links to a node.
	 * Furthermore, instances must be created so as to act as data for the nodes
	 * to be tested.
	 */
	@Before
	public void setUp() {
		node = new Node();
		rootNode = new Node();
		childNode1 = new Node();
		childNode2 = new Node();

		node.setChildren(childNode1);
		node.setChildren(childNode2);
		node.setParent(rootNode);
		rootNode.setChildren(node);
		childNode1.setParent(node);
		childNode2.setParent(node);

		instances = new ArrayList<Instance>();

		i1 = new Instance();
		Instance i2 = new Instance();
		Instance i3 = new Instance();
		Instance i4 = new Instance();
		i5 = new Instance();

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
	 * Test that a node can be cloned, cloning the node must include cloning all
	 * of the original nodes data, parents and children.
	 */
	@Test
	public void testCloneNode() {
		node.setAttribute(5);
		node.setLabel("Republican");
		Node newNode = new Node();
		node.cloneNode(node, newNode);
		assertEquals("Republican", newNode.getLabel());
		assertEquals(5, newNode.getAttribute());
		assertEquals(node.getParent(), newNode.getParent());
		assertEquals(node.getChildren().get(0), newNode.getChildren().get(0));
		assertEquals(node.getChildren().get(1), newNode.getChildren().get(1));
		assertEquals(node.getChildren().size(), newNode.getChildren().size());
	}

	@Test
	public void testGetChildren() {
		assertEquals(node.getChildren().get(0), childNode1);
		assertEquals(node.getChildren().get(1), childNode2);
	}

	/**
	 * Test that a new child node can be added to a root node.
	 */
	@Test
	public void testSetChildren() {
		assertEquals(node.getChildren().size(), 2);
		Node childNode3 = new Node();
		node.setChildren(childNode3);
		assertEquals(node.getChildren().size(), 3);
		assertEquals(node.getChildren().get(2), childNode3);
	}

	/**
	 * Test a child node can be removed.
	 */
	@Test
	public void testRemoveChildren() {

		assertEquals(node.getChildren().size(), 2);
		assertEquals(node.getChildren().get(0), childNode1);

		node.removeChildren(childNode1);

		assertEquals(node.getChildren().size(), 1);
		assertEquals(node.getChildren().get(0), childNode2);
	}

	@Test
	public void testGetAttribute() {
		node.setAttribute(5);
		assertEquals(5, node.getAttribute());
		node.setAttribute(10);
		assertEquals(10, node.getAttribute());
	}

	@Test
	public void testGetOwnData() {
		assertEquals(5, node.getOwnData().size());
		assertEquals(i1, node.getOwnData().get(0));
		assertEquals(i5, node.getOwnData().get(4));
	}

	/**
	 * Test that new data can be added to the node.
	 */
	@Test
	public void testSetOwnData() {
		assertEquals(5, node.getOwnData().size());
		instances = new ArrayList<Instance>();
		assertEquals(5, node.getOwnData().size());

		instances.add(i1);
		instances.add(i5);

		node.setOwnData(instances);

		assertEquals(2, node.getOwnData().size());
		assertEquals(i1, node.getOwnData().get(0));
		assertEquals(i5, node.getOwnData().get(1));
	}

	@Test
	public void testGetLabel() {
		node.setLabel("Republican");
		assertEquals("Republican", node.getLabel());
		node.setLabel("Democrat");
		assertEquals("Democrat", node.getLabel());
	}

	@Test
	public void testGetParent() {
		assertEquals(rootNode, node.getParent());
	}

	@Test
	public void testSetParent() {
		Node newParent = new Node();
		node.setParent(newParent);
		assertEquals(newParent, node.getParent());
	}
}
