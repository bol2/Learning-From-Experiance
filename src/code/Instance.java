package code;

/**
 * CS39440 Major Project: Learning From Experience
 * Instance.java
 * Purpose: Represents a single instance from the data set with named attributes
 * 
 * @author Ben Larking
 * @version 2.0 04/03/16
 */

public class Instance {
	
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
	
	private int classification; 
	private int id;
	
	public Instance(){}

	public Instance(int handicapped_infants, int water_project_cost_sharing, int adoption_of_the_budget_resolution,
			int physician_fee_freeze, int el_salvador_aid, int religious_groups_in_schools, int anti_satellite_test_ban,
			int aid_to_nicaraguan_contras, int mx_missile, int immigration, int synfuels_corporation_cutback,
			int education_spending, int superfund_right_to_sue, int crime, int duty_free_exports,
			int export_administration_act_south_africa, int classification, int id) {
		
		this.handicapped_infants = handicapped_infants;
		this.water_project_cost_sharing = water_project_cost_sharing;
		this.adoption_of_the_budget_resolution = adoption_of_the_budget_resolution;
		this.physician_fee_freeze = physician_fee_freeze;
		this.el_salvador_aid = el_salvador_aid;
		this.religious_groups_in_schools = religious_groups_in_schools;
		this.anti_satellite_test_ban = anti_satellite_test_ban;
		this.aid_to_nicaraguan_contras = aid_to_nicaraguan_contras;
		this.mx_missile = mx_missile;
		this.immigration = immigration;
		this.synfuels_corporation_cutback = synfuels_corporation_cutback;
		this.education_spending = education_spending;
		this.superfund_right_to_sue = superfund_right_to_sue;
		this.crime = crime;
		this.duty_free_exports = duty_free_exports;
		this.export_administration_act_south_africa = export_administration_act_south_africa;
		this.classification = classification;
		this.id = id;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAttributeValue(int i){
		if (i == 1) return this.getHandicapped_infants();
		else if(i == 2) return this.getWater_project_cost_sharing();
		else if(i == 3) return this.getAdoption_of_the_budget_resolution();
		else if(i == 4) return this.getPhysician_fee_freeze();
		else if(i == 5) return this.getEl_salvador_aid();
		else if(i == 6) return this.getReligious_groups_in_schools();
		else if(i == 7) return this.getAnti_satellite_test_ban();
		else if(i == 8) return this.getAid_to_nicaraguan_contras();
		else if(i == 9) return this.getMx_missile();
		else if(i == 10) return this.getImmigration();
		else if(i == 11) return this.getSynfuels_corporation_cutback();
		else if(i == 12) return this.getEducation_spending();
		else if(i == 13) return this.getSuperfund_right_to_sue();
		else if(i == 14) return this.getCrime();
		else if(i == 15) return this.getDuty_free_exports();
		else if(i == 16) return this.getExport_administration_act_south_africa();
		
		return 0;
	}

	
}
