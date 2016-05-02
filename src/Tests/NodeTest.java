package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.Instance;
import code.Node;

public class NodeTest {
	
	private Node node;
	private Node rootNode;
	private Node childNode1;
	private Node childNode2;
	
	private ArrayList<Instance> instances;
	private Instance i1;
	private Instance i5;

	@Before
	public void setUp() throws Exception {
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
		node.setOwnData(instances);
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

	@Test
	public void testSetChildren() {
		assertEquals(node.getChildren().size(), 2);
		Node childNode3 = new Node();
		node.setChildren(childNode3);
		assertEquals(node.getChildren().size(), 3);
		assertEquals(node.getChildren().get(2), childNode3);	
	}

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
