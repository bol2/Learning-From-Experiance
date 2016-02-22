package code;

/**
 * CS39440 Major Project: Learning From Experience
 * Instance.java
 * Purpose: Represents a single instance from the data set with named attributes
 * 
 * @author Ben Larking
 * @version 1.4 22/02/16
 */

public class Instance {
	
	private int age;
	private int perscription;
	private int astigmatic;
	private int tearProdRate;
	private int classification; 
	private int id;
	
	public Instance(){}
	
	public Instance(int age, int perscription, int astigmatic, int tearProdRate, int classification, int id){
		this.age = age;
		this.perscription = perscription;
		this.astigmatic = astigmatic;
		this.tearProdRate = tearProdRate;
		this.classification = classification;
		this.id = id;
	}

	public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPerscription() {
		return perscription;
	}

	public void setPerscription(int perscription) {
		this.perscription = perscription;
	}

	public int getAstigmatic() {
		return astigmatic;
	}

	public void setAstigmatic(int astigmatic) {
		this.astigmatic = astigmatic;
	}

	public int getTearProdRate() {
		return tearProdRate;
	}

	public void setTearProdRate(int tearProdRate) {
		this.tearProdRate = tearProdRate;
	}
	
	public int getAttributeValue(int i){
		if (i == 0) return this.getAge();
		else if(i == 1) return this.getPerscription();
		else if(i == 2) return this.getAstigmatic();
		else if(i == 3) return this.getTearProdRate();
		
		return 4;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
