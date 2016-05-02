package code;

/**
 * CS39440 Major Project: Learning From Experience Instance.java Purpose:
 * Represents a single instance from the data set with named attributes
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class Instance {

	private int classification;
	private int handicapped_infants;
	private int water_project_cost_sharing;
	private int adoption_of_the_budget_resolution;
	private int physician_fee_freeze;
	private int el_salvador_aid;
	private int religious_groups_in_schools;
	private int anti_satellite_test_ban;
	private int aid_to_nicaraguan_contras;
	private int mx_missile;
	private int immigration;
	private int synfuels_corporation_cutback;
	private int education_spending;
	private int superfund_right_to_sue;
	private int crime;
	private int duty_free_exports;
	private int export_administration_act_south_africa;

	public Instance(){}

	public int getHandicapped_infants() {
		return handicapped_infants;
	}

	public void setHandicapped_infants(int handicapped_infants) {
		this.handicapped_infants = handicapped_infants;
	}

	public int getWater_project_cost_sharing() {
		return water_project_cost_sharing;
	}

	public void setWater_project_cost_sharing(int water_project_cost_sharing) {
		this.water_project_cost_sharing = water_project_cost_sharing;
	}

	public int getAdoption_of_the_budget_resolution() {
		return adoption_of_the_budget_resolution;
	}

	public void setAdoption_of_the_budget_resolution(int adoption_of_the_budget_resolution) {
		this.adoption_of_the_budget_resolution = adoption_of_the_budget_resolution;
	}

	public int getPhysician_fee_freeze() {
		return physician_fee_freeze;
	}

	public void setPhysician_fee_freeze(int physician_fee_freeze) {
		this.physician_fee_freeze = physician_fee_freeze;
	}

	public int getEl_salvador_aid() {
		return el_salvador_aid;
	}

	public void setEl_salvador_aid(int el_salvador_aid) {
		this.el_salvador_aid = el_salvador_aid;
	}

	public int getReligious_groups_in_schools() {
		return religious_groups_in_schools;
	}

	public void setReligious_groups_in_schools(int religious_groups_in_schools) {
		this.religious_groups_in_schools = religious_groups_in_schools;
	}

	public int getAnti_satellite_test_ban() {
		return anti_satellite_test_ban;
	}

	public void setAnti_satellite_test_ban(int anti_satellite_test_ban) {
		this.anti_satellite_test_ban = anti_satellite_test_ban;
	}

	public int getAid_to_nicaraguan_contras() {
		return aid_to_nicaraguan_contras;
	}

	public void setAid_to_nicaraguan_contras(int aid_to_nicaraguan_contras) {
		this.aid_to_nicaraguan_contras = aid_to_nicaraguan_contras;
	}

	public int getMx_missile() {
		return mx_missile;
	}

	public void setMx_missile(int mx_missile) {
		this.mx_missile = mx_missile;
	}

	public int getImmigration() {
		return immigration;
	}

	public void setImmigration(int immigration) {
		this.immigration = immigration;
	}

	public int getSynfuels_corporation_cutback() {
		return synfuels_corporation_cutback;
	}

	public void setSynfuels_corporation_cutback(int synfuels_corporation_cutback) {
		this.synfuels_corporation_cutback = synfuels_corporation_cutback;
	}

	public int getEducation_spending() {
		return education_spending;
	}

	public void setEducation_spending(int education_spending) {
		this.education_spending = education_spending;
	}

	public int getSuperfund_right_to_sue() {
		return superfund_right_to_sue;
	}

	public void setSuperfund_right_to_sue(int superfund_right_to_sue) {
		this.superfund_right_to_sue = superfund_right_to_sue;
	}

	public int getCrime() {
		return crime;
	}

	public void setCrime(int crime) {
		this.crime = crime;
	}

	public int getDuty_free_exports() {
		return duty_free_exports;
	}

	public void setDuty_free_exports(int duty_free_exports) {
		this.duty_free_exports = duty_free_exports;
	}

	public int getExport_administration_act_south_africa() {
		return export_administration_act_south_africa;
	}

	public void setExport_administration_act_south_africa(int export_administration_act_south_africa) {
		this.export_administration_act_south_africa = export_administration_act_south_africa;
	}

	public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	/**
	 * Returns the value of a given attribute.
	 * 
	 * @param attributeID
	 *            integer representing attribute
	 * @return integer representing if the vote is democrat, republican or ?
	 */
	public int getAttributeValue(int attributeID) {
		if (attributeID == 0)
			return this.getHandicapped_infants();
		else if (attributeID == 1)
			return this.getWater_project_cost_sharing();
		else if (attributeID == 2)
			return this.getAdoption_of_the_budget_resolution();
		else if (attributeID == 3)
			return this.getPhysician_fee_freeze();
		else if (attributeID == 4)
			return this.getEl_salvador_aid();
		else if (attributeID == 5)
			return this.getReligious_groups_in_schools();
		else if (attributeID == 6)
			return this.getAnti_satellite_test_ban();
		else if (attributeID == 7)
			return this.getAid_to_nicaraguan_contras();
		else if (attributeID == 8)
			return this.getMx_missile();
		else if (attributeID == 9)
			return this.getImmigration();
		else if (attributeID == 10)
			return this.getSynfuels_corporation_cutback();
		else if (attributeID == 11)
			return this.getEducation_spending();
		else if (attributeID == 12)
			return this.getSuperfund_right_to_sue();
		else if (attributeID == 13)
			return this.getCrime();
		else if (attributeID == 14)
			return this.getDuty_free_exports();
		else if (attributeID == 15)
			return this.getExport_administration_act_south_africa();
		return 16;
	}

	/**
	 * Given an integer representing an attribute this method sets the value of the attribute to the integer value passed in.
	 * 
	 * @param instanceID
	 *            integer representing the attribute
	 * @param value
	 *            is the value to be assigned to an attribute
	 */
	public void setAttributeValue(int instanceID, int value) {
		if (instanceID == 0)
			this.setClassification(value);
		else if (instanceID == 1)
			this.setHandicapped_infants(value);
		else if (instanceID == 2)
			this.setWater_project_cost_sharing(value);
		else if (instanceID == 3)
			this.setAdoption_of_the_budget_resolution(value);
		else if (instanceID == 4)
			this.setPhysician_fee_freeze(value);
		else if (instanceID == 5)
			this.setEl_salvador_aid(value);
		else if (instanceID == 6)
			this.setReligious_groups_in_schools(value);
		else if (instanceID == 7)
			this.setAnti_satellite_test_ban(value);
		else if (instanceID == 8)
			this.setAid_to_nicaraguan_contras(value);
		else if (instanceID == 9)
			this.setMx_missile(value);
		else if (instanceID == 10)
			this.setImmigration(value);
		else if (instanceID == 11)
			this.setSynfuels_corporation_cutback(value);
		else if (instanceID == 12)
			this.setEducation_spending(value);
		else if (instanceID == 13)
			this.setSuperfund_right_to_sue(value);
		else if (instanceID == 14)
			this.setCrime(value);
		else if (instanceID == 15)
			this.setDuty_free_exports(value);
		else if (instanceID == 16)
			this.setExport_administration_act_south_africa(value);
	}
}
