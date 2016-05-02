package code;

import java.util.ArrayList;
import java.util.Random;

/**
 * CS39440 Major Project: Learning From Experience EntropyCalculator.java
 * Purpose: Class calculates data set entropy and information gain for
 * attributes given a data set.
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class EntropyCalculator {
	
	private AttributeGetter attributeGetter;
	private double entropy;
	
	public EntropyCalculator() {}

	/**
	 * Calculates the entropy for the remaining instances in the data being used to build the tree
	 * @param instances Array list of instances.
	 */
	public void calculateEntropy(ArrayList<Instance> instances) {
		
		int republican = 0;
		int democrat = 0;

		double dataSetSize = instances.size();
		for (int instance = 0; instance < instances.size(); instance++) {
			int thisClassification = instances.get(instance).getClassification();
			if (thisClassification == 1)
				republican++;
			else if (thisClassification == 2)
				democrat++;
		}
		if (republican != 0)
			entropy += -republican / dataSetSize * (Math.log(republican / dataSetSize) / Math.log(2));
		if (democrat != 0)
			entropy += -democrat / dataSetSize * (Math.log(democrat / dataSetSize) / Math.log(2));
	}

	/**
	 * Method finds the attribute in the list of remaining attributes that has the highest information gain, best splitting the remaining data.
	 * @param RemainingInstances Array list containing instances that will be used to calculate information gain.
	 * @param AttributesRemaining Array list containing the remaining attributes that have not been used to build the decision tree yet.
	 * @return The position in the remaining attributes array list that holds the attribute that will give the highest information gain.
	 */
	public int calculateHighestGain(ArrayList<Instance> remainingInstances, ArrayList<Attribute> attributesRemaining) {

		ArrayList<Double> doubles = new ArrayList<Double>();
		ArrayList<Attribute> addedAttributes = new ArrayList<Attribute>();

		for (Attribute attribute : attributesRemaining) {
			attributeGetter = new AttributeGetter(attribute);
			int attributeNumer = attributeGetter.getAttribute();
			doubles.add(calculateInformationGain(remainingInstances, attributeNumer));
			addedAttributes.add(attribute);
		}

		double max = -99999;

		int position = -1;
		for (int i = 0; i < doubles.size(); i++) {
			if (doubles.get(i) > max) {
				max = doubles.get(i);
				position = i;
			}
		}
		return position;
	}

	/**
	 * Method uses the entropy and number of republicans and democrats in the remaining instances to calculate information gain.
	 * @param remainingInstances Array list of remaining instances.
	 * @param attribute The attribute on which information gain is being calculated.
	 * @return Returns a value for information gain.
	 */
	public double calculateInformationGain(ArrayList<Instance> remainingInstances, int attribute) {
		double gain = 0;

		int republicanAndYes = 0;
		int republicanAndNo = 0;
		double republicanAppearances = 0;

		int democratAndYes = 0;
		int democratAndNo = 0;
		double democratAppearances = 0;

		for (int instance = 0; instance < remainingInstances.size(); instance++) {
			int thisClassification = remainingInstances.get(instance).getClassification();
			if (thisClassification == 1) {
				republicanAppearances++;
				int vote = remainingInstances.get(instance).getAttributeValue(attribute);
				if (vote == 1)
					republicanAndYes++;
				else if (vote == 2)
					republicanAndNo++;
			} else if (thisClassification == 2) {
				democratAppearances++;
				int vote = remainingInstances.get(instance).getAttributeValue(attribute);
				if (vote == 1)
					democratAndYes++;
				else if (vote == 2)
					democratAndNo++;
			}
		}
		double yesGain = 0;
		double noGain = 0;

		if (republicanAndYes != 0)
			yesGain = -republicanAndYes / republicanAppearances
					* (Math.log(republicanAndYes / republicanAppearances) / Math.log(2));
		if (republicanAndNo != 0)
			yesGain += -republicanAndNo / republicanAppearances
					* (Math.log(republicanAndNo / republicanAppearances) / Math.log(2));
		if (democratAndYes != 0)
			noGain = -democratAndYes / democratAppearances
					* (Math.log(democratAndYes / democratAppearances) / Math.log(2));
		if (democratAndNo != 0)
			noGain += -democratAndNo / democratAppearances
					* (Math.log(democratAndNo / democratAppearances) / Math.log(2));

		gain = entropy - (((republicanAppearances / remainingInstances.size()) * yesGain
				)+ ((democratAppearances / remainingInstances.size()) * noGain));
		return gain;

	}

	/**
	 * Method assigns missing attribute values.
	 * @param instances Array list of instances that may or may not have missing attribute values.
	 * @param attribute Attribute to be checked for missing values. 
	 */
	public void checkAndAssignValue(ArrayList<Instance> instances, Attribute attribute) {

		attributeGetter = new AttributeGetter(attribute);
		int attributeNumer = attributeGetter.getAttribute();

		double missingValue = 0;
		double numberOfYes = 0;

		for (Instance instance : instances) {
			int value = instance.getAttributeValue(attributeNumer);
			if (value == 3) {
				missingValue++;
			} else if (value == 1) {
				numberOfYes++;
			}
		}

		if (missingValue == 0)
			return;

		Random random = new Random();
		double percentageYes = numberOfYes / (instances.size() - missingValue);
		
		for (Instance i : instances) {
			double chanceYes = random.nextDouble();
			if (i.getAttributeValue(attributeNumer) == 3) {
				if (chanceYes <= percentageYes) {
					i.setAttributeValue(attributeNumer+1, 1);
				} else {
					i.setAttributeValue(attributeNumer+1, 2);
				}
			}
		}
	}

	/**
	 * Returns the entropy.
	 * @return A value for entropy. 
	 */
	public double getEntropy() {
		return entropy;
	}
	
	/**
	 * Sets the entropy value.
	 * @param entropy value entropy is to be set to.
	 */
	public void setEntropy(double entropy) {
		this.entropy =  entropy;
	}
}