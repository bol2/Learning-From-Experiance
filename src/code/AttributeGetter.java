package code;
import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience 
 * AttributeGetter.java
 * Purpose: A class that can link integer numbers to attributes and return an array of integers for attribute values 	
 * 
 * @author Ben Larking
 * @version 1.4 22/02/16
 */

public class AttributeGetter {

	Attribute A;

	public AttributeGetter(Attribute A) {
		this.A = A;
	}

	public int getAttribute() {
		switch (A) {
		case handicapped_infants:
			return 0;
		case water_project_cost_sharing:
			return 1;
		case adoption_of_the_budget_resolution:
			return 2;
		case physician_fee_freeze:
			return 3;
		case el_salvador_aid:
			return 4;
		case religious_groups_in_schools:
			return 5;
		case anti_satellite_test_ban:
			return 6;
		case aid_to_nicaraguan_contras:
			return 7;
		case mx_missile:
			return 8;
		case immigration:
			return 9;
		case synfuels_corporation_cutback:
			return 10;
		case education_spending:
			return 11;
		case superfund_right_to_sue:
			return 12;
		case crime:
			return 13;
		case duty_free_exports:
			return 14;
		case export_administration_act_south_africa:
			return 15;

		default:
			System.out.println("Undefined Attribute");
			return 16;
		}
	}

	/*public ArrayList<Integer> getAttributeValues() {
		ArrayList<Integer> list = new ArrayList<>();
		switch (A) {
		case AGE:
			list.add(1);
			list.add(2);
			list.add(3);
			return list;
		case PERSCRIPTION:
			list.add(1);
			list.add(2);
			return list;
		case ASTIGMATIC:
			list.add(1);
			list.add(2);
			return list;
		case TEARPRODRATE:
			list.add(1);
			list.add(2);
			return list;

		default:
			System.out.println("Undefined Attribute");
			return list;
		}
	}*/

}
