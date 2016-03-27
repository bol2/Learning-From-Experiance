package code;

import java.util.ArrayList;
import java.util.Random;

/**
 * CS39440 Major Project: Learning From Experience EntropyCalculator.java
 * Purpose: Class calculates data set entropy and information gain for
 * attributes given a data set.
 * 
 * @author Ben Larking
 * @version 1.6 14/03/16
 */

public class EntropyCalculator {

	private AttributeGetter ag;

	double entropy;

	public EntropyCalculator() {
		this.entropy = 0;
	}

	/**
	 * Calculates the entropy for the remaining instances in the data being used to build the tree
	 * @param instances
	 */
	public void calculateEntropy(ArrayList<Instance> instances) {
		int republican = 0;
		int democrat = 0;

		double dataSetSize = instances.size();
		for (int i = 0; i < instances.size(); i++) {
			int thisClassification = instances.get(i).getClassification();
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

	
	public int calculateHighestGain(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining) {

		ArrayList<Double> doubles = new ArrayList<Double>();
		ArrayList<Attribute> addedAttributes = new ArrayList<Attribute>();

		for (Attribute attribute : attributesRemaining) {
			ag = new AttributeGetter(attribute);
			int attributeNumer = ag.getAttribute();
			//checkAndAssignValue(remaining, attribute);
			doubles.add(calculateInformationGain(remaining, attributeNumer));
			addedAttributes.add(attribute);
		}

		double max = -99999;

		int position = -1;
		// Doesn't really work if two values are the same
		for (int i = 0; i < doubles.size(); i++) {
			if (doubles.get(i) > max) {
				max = doubles.get(i);
				position = i;
			}
		}

		return position;
	}

	public double calculateInformationGain(ArrayList<Instance> remaining, int attribute) {
		double gain = 0;

		int republicanAndYes = 0;
		int republicanAndNo = 0;
		double republicanAppearances = 0;

		int democratAndYes = 0;
		int democratAndNo = 0;
		double democratAppearances = 0;

		for (int i = 0; i < remaining.size(); i++) {
			int thisClassification = remaining.get(i).getClassification();
			if (thisClassification == 1) {
				republicanAppearances++;
				int vote = remaining.get(i).getAttributeValue(attribute);
				if (vote == 1)
					republicanAndYes++;
				else if (vote == 2)
					republicanAndNo++;
			} else if (thisClassification == 2) {
				democratAppearances++;
				int vote = remaining.get(i).getAttributeValue(attribute);
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

		gain = entropy - (republicanAppearances / remaining.size() * yesGain
				+ democratAppearances / remaining.size() * noGain);
		return gain;

	}

	/**
	 * A second, more complex procedure is to assign a probability to each of
	 * the possible values of A rather than simply assigning the most common
	 * value to A(x). These probabilities can be estimated again based on the
	 * observed frequencies of the various values for A among the examples at
	 * node n. For example, given a boolean attribute A, if node n contains six
	 * known examples with A = 1 and four with A = 0, then we would say the
	 * probability that A(x) = 1 is 0.6, and the probability that A(x) = 0 is
	 * 0.4. A fractional 0.6 of instance x is now distributed down the branch
	 * for A = 1, and a fractional 0.4 of x down the other tree branch. These
	 * fractional examples are used for the purpose of computing information
	 * Gain and can be further subdivided at subsequent branches of the tree if
	 * a second missing attribute value must be tested. This same fractioning of
	 * examples can also be applied after learning, to classify new instances
	 * whose attribute values are unknown. In this case, the classification of
	 * the new instance is simply the most probable classification, computed by
	 * summing the weights of the instance fragments classified in different
	 * ways at the leaf nodes of the tree. This method for handling missing
	 * attribute values is used in C4.5 (Quinlan 1993).
	 * 
	 * @param attribute
	 **/

	public void checkAndAssignValue(ArrayList<Instance> instances, Attribute attribute) {

		// for every attribute

		ag = new AttributeGetter(attribute);
		int attributeNumer = ag.getAttribute();

		double missingValue = 0;
		double numberOfYes = 0;
		double numberOfNo = 0;

		for (Instance i : instances) {
			int value = i.getAttributeValue(attributeNumer);
			if (value == 3) {
				missingValue++;
			} else if (value == 1) {
				numberOfYes++;
			} else if (value == 2) {
				numberOfNo++;
			}
		}

		if (missingValue == 0)
			return;

		Random r = new Random();

		// Should it be

		double percentageYes = numberOfYes / (instances.size() - missingValue);
		double percentageNo = numberOfNo / (instances.size() - missingValue);

		for (Instance i : instances) {
			double chanceYes = r.nextDouble();

			if (i.getAttributeValue(attributeNumer) == 3) {
				if (chanceYes <= percentageYes) {
					i.setAttributeValue(attributeNumer+1, 1);
				} else {
					i.setAttributeValue(attributeNumer+1, 2);
				}
			}

		}

	}

	public double getEntropy() {
		return entropy;
	}

	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}

}