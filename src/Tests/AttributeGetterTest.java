package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import code.Attribute;
import code.AttributeGetter;

/**
 * CS39440 Major Project: Learning From Experience
 * AttributeGetterTest.java
 * Purpose: A class to test the functionality of the methods belonging to
 * AttributeGetter.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class AttributeGetterTest {

	AttributeGetter attributeGetter;

	@Test
	public void testGetAttribute() {
		attributeGetter = new AttributeGetter(Attribute.handicapped_infants);
		assertEquals(attributeGetter.getAttribute(), 0);
		attributeGetter = new AttributeGetter(Attribute.religious_groups_in_schools);
		assertEquals(attributeGetter.getAttribute(), 5);
		attributeGetter = new AttributeGetter(Attribute.synfuels_corporation_cutback);
		assertEquals(attributeGetter.getAttribute(), 10);
		attributeGetter = new AttributeGetter(Attribute.export_administration_act_south_africa);
		assertEquals(attributeGetter.getAttribute(), 15);
	}

	@Test
	public void testGetAttributeString() {
		attributeGetter = new AttributeGetter(1);
		assertEquals(attributeGetter.getAttributeString(), "water_project_cost_sharing");
		attributeGetter = new AttributeGetter(6);
		assertEquals(attributeGetter.getAttributeString(), "anti_satellite_test_ban");
		attributeGetter = new AttributeGetter(11);
		assertEquals(attributeGetter.getAttributeString(), "education_spending");
		attributeGetter = new AttributeGetter(16);
		assertEquals(attributeGetter.getAttributeString(), "Leaf");
	}

	@Test
	public void testGetAttributefromValue() {
		attributeGetter = new AttributeGetter(1);
		assertEquals(attributeGetter.getAttributefromValue(), Attribute.water_project_cost_sharing);
		attributeGetter = new AttributeGetter(6);
		assertEquals(attributeGetter.getAttributefromValue(), Attribute.anti_satellite_test_ban);
		attributeGetter = new AttributeGetter(11);
		assertEquals(attributeGetter.getAttributefromValue(), Attribute.education_spending);
		attributeGetter = new AttributeGetter(16);
		assertEquals(attributeGetter.getAttributefromValue(), null);
	}
}
