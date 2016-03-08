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

	private Attribute A;
	private int id;

	public AttributeGetter(Attribute A) {
		this.A = A;
	}
	
	public AttributeGetter(int id) {
		this.id = id;
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

	public String getAttributeString() {
		switch (id) {
		case 0:
			return "handicapped_infants";
		case 1:
			return "water_project_cost_sharing";
		case 2:
			return "adoption_of_the_budget_resolution";
		case 3:
			return "physician_fee_freeze";
		case 4:
			return "el_salvador_aid";
		case 5:
			return "religious_groups_in_schools";
		case 6:
			return "anti_satellite_test_ban";
		case 7:
			return "aid_to_nicaraguan_contras";
		case 8:
			return "mx_missile";
		case 9:
			return "immigration";
		case 10:
			return "synfuels_corporation_cutback";
		case 11:
			return "education_spending";
		case 12:
			return "superfund_right_to_sue";
		case 13:
			return "crime";
		case 14:
			return "duty_free_exports";
		case 15:
			return "export_administration_act_south_africa";

		default:
			return "Leaf";
		}
	}
}
