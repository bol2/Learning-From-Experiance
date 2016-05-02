package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import code.Instance;

/**
 * CS39440 Major Project: Learning From Experience InstanceTest.java Purpose:
 * A class to test the functionality of the methods belonging to
 * Instance.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class InstanceTest {

	Instance instance;
	
	@Before
	public void setUp() throws Exception {
		instance = new Instance();
	}

	@Test
	public void testGetAttributeValue() {
		instance.setAid_to_nicaraguan_contras(1);
		instance.setAnti_satellite_test_ban(2);
		
		assertEquals(instance.getAttributeValue(7), 1);
		assertEquals(instance.getAttributeValue(6),2);
	}

	@Test
	public void testSetAndGetAttributeValue() {
		instance.setAttributeValue(0, 1);
		instance.setAttributeValue(16, 2);
		
		assertEquals(instance.getClassification(), 1);
		assertEquals(instance.getExport_administration_act_south_africa(), 2);
	}
}
