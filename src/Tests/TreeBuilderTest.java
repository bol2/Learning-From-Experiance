package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.Instance;
import code.Node;
import code.TreeBuilder;

public class TreeBuilderTest {

	Node node;
	ArrayList<Instance> instances;
	TreeBuilder treeBuilder;
	
	@Before
	public void setUp() throws Exception {
		
		node = new Node();
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
		treeBuilder = new TreeBuilder(instances);
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
