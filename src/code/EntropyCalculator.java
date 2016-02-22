package code;
import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience
 * EntropyCalculator.java
 * Purpose: Class calculates data set entropy and information gain for
 * attributes given a data set.
 * 
 * @author Ben Larking
 * @version 1.4 22/02/16
 */

public class EntropyCalculator {

	private AttributeGetter ag;

	double entropy;

	public EntropyCalculator() {
	}

	public double calculateEntropy(ArrayList<Instance> instances) {

		entropy = 0;

		int classification1 = 0;
		int classification2 = 0;
		int classification3 = 0;

		double dataSetSize = instances.size();

		for (int i = 0; i < instances.size(); i++) {
			int thisClassification = instances.get(i).getClassification();
			if (thisClassification == 1)
				classification1++;
			else if (thisClassification == 2)
				classification2++;
			else if (thisClassification == 3)
				classification3++;
		}

		if (classification1 != 0)
			entropy += -classification1 / dataSetSize * (Math.log(classification1 / dataSetSize) / Math.log(2));
		if (classification2 != 0)
			entropy += -classification2 / dataSetSize * (Math.log(classification2 / dataSetSize) / Math.log(2));
		if (classification3 != 0)
			entropy += -classification3 / dataSetSize * (Math.log(classification3 / dataSetSize) / Math.log(2));

		return entropy;
	}

	public int calculateHighestGain(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining) {

		int value = 0;

		ArrayList<Double> doubles = new ArrayList<Double>();
		ArrayList<Attribute> addedAttributes = new ArrayList<Attribute>();

		for (Attribute attribute : attributesRemaining) {
			ag = new AttributeGetter(attribute);

			if (ag.getAttribute() == 0) {
				doubles.add(calculateAgeGain(remaining));
				addedAttributes.add(attribute);
			} else if (ag.getAttribute() == 1) {
				doubles.add(calculatePerscriptionGain(remaining));
				addedAttributes.add(attribute);
			} else if (ag.getAttribute() == 2) {
				doubles.add(calculateAstigmaticGain(remaining));
				addedAttributes.add(attribute);
			} else if (ag.getAttribute() == 3) {
				doubles.add(calculateTearProdRateGain(remaining));
				addedAttributes.add(attribute);
			}
		}

		double max = Double.MIN_VALUE;

		int position = -1;
		// Doesn't really work if two values are the same
		for (int i = 0; i < doubles.size(); i++) {
			if (doubles.get(i) > max) {
				max = doubles.get(i);
				position = i;
			}
		}

		Attribute highestGain = addedAttributes.get(position);
		ag = new AttributeGetter(highestGain);
		value = ag.getAttribute();
		return value;
	}

	private double calculateTearProdRateGain(ArrayList<Instance> remaining) {
		double gain = 0;

		int reducedandHardLense = 0;
		int reducedandSoftLense = 0;
		int reducedandNoLense = 0;
		double reducedAppearances = 0;

		int normalandHardLense = 0;
		int normalandSoftLense = 0;
		int normalandNoLense = 0;
		double normalAppearances = 0;

		for (int i = 0; i < remaining.size(); i++) {
			int thisPerscription = remaining.get(i).getTearProdRate();
			if (thisPerscription == 1) {
				reducedAppearances++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					reducedandHardLense++;
				if (classification == 2)
					reducedandSoftLense++;
				if (classification == 3)
					reducedandNoLense++;
			} else if (thisPerscription == 2) {
				normalAppearances++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					normalandHardLense++;
				if (classification == 2)
					normalandSoftLense++;
				if (classification == 3)
					normalandNoLense++;
			}
		}

		double reducedGain = 0;
		double normalGain = 0;

		if (reducedandHardLense != 0)
			reducedGain = -reducedandHardLense / reducedAppearances
					* (Math.log(reducedandHardLense / reducedAppearances) / Math.log(2));
		if (reducedandSoftLense != 0)
			reducedGain += -reducedandSoftLense / reducedAppearances
					* (Math.log(reducedandSoftLense / reducedAppearances) / Math.log(2));
		if (reducedandNoLense != 0)
			reducedGain += -reducedandNoLense / reducedAppearances
					* (Math.log(reducedandNoLense / reducedAppearances) / Math.log(2));

		if (normalandHardLense != 0)
			normalGain = -normalandHardLense / normalAppearances
					* (Math.log(normalandHardLense / normalAppearances) / Math.log(2));
		if (normalandSoftLense != 0)
			normalGain += -normalandSoftLense / normalAppearances
					* (Math.log(normalandSoftLense / normalAppearances) / Math.log(2));
		if (normalandNoLense != 0)
			normalGain += -normalandNoLense / normalAppearances
					* (Math.log(normalandNoLense / normalAppearances) / Math.log(2));

		gain = entropy - (reducedAppearances / remaining.size() * reducedGain
				+ normalAppearances / remaining.size() * normalGain);

		return gain;
	}

	private double calculateAstigmaticGain(ArrayList<Instance> remaining) {
		double gain = 0;

		int noandHardLense = 0;
		int noandSoftLense = 0;
		int noandNoLense = 0;
		double noAppearances = 0;

		int yesandHardLense = 0;
		int yesandSoftLense = 0;
		int yesandNoLense = 0;
		double yesAppearances = 0;

		for (int i = 0; i < remaining.size(); i++) {
			int thisPerscription = remaining.get(i).getAstigmatic();
			if (thisPerscription == 1) {
				noAppearances++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					noandHardLense++;
				if (classification == 2)
					noandSoftLense++;
				if (classification == 3)
					noandNoLense++;
			} else if (thisPerscription == 2) {
				yesAppearances++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					yesandHardLense++;
				if (classification == 2)
					yesandSoftLense++;
				if (classification == 3)
					yesandNoLense++;
			}
		}

		double noGain = 0;
		double yesGain = 0;

		if (noandHardLense != 0)
			noGain = -noandHardLense / noAppearances * (Math.log(noandHardLense / noAppearances) / Math.log(2));
		if (noandSoftLense != 0)
			noGain += -noandSoftLense / noAppearances * (Math.log(noandSoftLense / noAppearances) / Math.log(2));
		if (noandNoLense != 0)
			noGain += -noandNoLense / noAppearances * (Math.log(noandNoLense / noAppearances) / Math.log(2));

		if (yesandHardLense != 0)
			yesGain = -yesandHardLense / yesAppearances * (Math.log(yesandHardLense / yesAppearances) / Math.log(2));
		if (yesandSoftLense != 0)
			yesGain += -yesandSoftLense / yesAppearances * (Math.log(yesandSoftLense / yesAppearances) / Math.log(2));
		if (yesandNoLense != 0)
			yesGain += -yesandNoLense / yesAppearances * (Math.log(yesandNoLense / yesAppearances) / Math.log(2));

		gain = entropy - (noAppearances / remaining.size() * noGain + yesAppearances / remaining.size() * yesGain);

		return gain;
	}

	private double calculatePerscriptionGain(ArrayList<Instance> remaining) {
		double gain = 0;

		int myopeandHardLense = 0;
		int myopeandSoftLense = 0;
		int myopeandNoLense = 0;
		double myopeAppearances = 0;

		int hyperandHardLense = 0;
		int hyperandSoftLense = 0;
		int hyperandNoLense = 0;
		double hyperAppearances = 0;

		for (int i = 0; i < remaining.size(); i++) {
			int thisPerscription = remaining.get(i).getPerscription();
			if (thisPerscription == 1) {
				myopeAppearances++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					myopeandHardLense++;
				if (classification == 2)
					myopeandSoftLense++;
				if (classification == 3)
					myopeandNoLense++;
			} else if (thisPerscription == 2) {
				hyperAppearances++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					hyperandHardLense++;
				if (classification == 2)
					hyperandSoftLense++;
				if (classification == 3)
					hyperandNoLense++;
			}
		}
		double myopeGain = 0;
		double hyperGain = 0;

		if (myopeandHardLense != 0)
			myopeGain = -myopeandHardLense / myopeAppearances
					* (Math.log(myopeandHardLense / myopeAppearances) / Math.log(2));
		if (myopeandSoftLense != 0)
			myopeGain += -myopeandSoftLense / myopeAppearances
					* (Math.log(myopeandSoftLense / myopeAppearances) / Math.log(2));
		if (myopeandNoLense != 0)
			myopeGain += -myopeandNoLense / myopeAppearances
					* (Math.log(myopeandNoLense / myopeAppearances) / Math.log(2));
		if (hyperandHardLense != 0)
			hyperGain = -hyperandHardLense / hyperAppearances
					* (Math.log(hyperandHardLense / hyperAppearances) / Math.log(2));
		if (hyperandSoftLense != 0)
			hyperGain += -hyperandSoftLense / hyperAppearances
					* (Math.log(hyperandSoftLense / hyperAppearances) / Math.log(2));
		if (hyperandNoLense != 0)
			hyperGain += -hyperandNoLense / hyperAppearances
					* (Math.log(hyperandNoLense / hyperAppearances) / Math.log(2));

		gain = entropy
				- (myopeAppearances / remaining.size() * myopeGain + hyperAppearances / remaining.size() * hyperGain);
		return gain;

	}

	private double calculateAgeGain(ArrayList<Instance> remaining) {
		double gain = 0;

		double value1 = 0;
		int age1andHardLense = 0;
		int age1andSoftLense = 0;
		int age1andNoLense = 0;

		double value2 = 0;
		int age2andHardLense = 0;
		int age2andSoftLense = 0;
		int age2andNoLense = 0;

		double value3 = 0;
		int age3andHardLense = 0;
		int age3andSoftLense = 0;
		int age3andNoLense = 0;

		for (int i = 0; i < remaining.size(); i++) {
			int thisAge = remaining.get(i).getAge();
			if (thisAge == 1) {
				value1++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					age1andHardLense++;
				if (classification == 2)
					age1andSoftLense++;
				if (classification == 3)
					age1andNoLense++;
			} else if (thisAge == 2) {
				value2++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					age2andHardLense++;
				if (classification == 2)
					age2andSoftLense++;
				if (classification == 3)
					age2andNoLense++;
			} else if (thisAge == 3) {
				value3++;
				int classification = remaining.get(i).getClassification();
				if (classification == 1)
					age3andHardLense++;
				if (classification == 2)
					age3andSoftLense++;
				if (classification == 3)
					age3andNoLense++;
			}
		}

		double age1Gain = 0;
		double age2Gain = 0;
		double age3Gain = 0;

		if (age1andHardLense != 0)
			age1Gain = -age1andHardLense / value1 * (Math.log(age1andHardLense / value1) / Math.log(2));
		if (age1andSoftLense != 0)
			age1Gain += -age1andSoftLense / value1 * (Math.log(age1andSoftLense / value1) / Math.log(2));
		if (age1andNoLense != 0)
			age1Gain += -age1andNoLense / value1 * (Math.log(age1andNoLense / value1) / Math.log(2));
		if (age2andHardLense != 0)
			age2Gain = -age2andHardLense / value2 * (Math.log(age2andHardLense / value2) / Math.log(2));
		if (age2andSoftLense != 0)
			age2Gain += -age2andSoftLense / value2 * (Math.log(age2andSoftLense / value2) / Math.log(2));
		if (age2andNoLense != 0)
			age2Gain += -age2andNoLense / value2 * (Math.log(age2andNoLense / value2) / Math.log(2));
		if (age3andHardLense != 0)
			age3Gain = -age3andHardLense / value3 * (Math.log(age3andHardLense / value3) / Math.log(2));
		if (age3andSoftLense != 0)
			age3Gain += -age3andSoftLense / value3 * (Math.log(age3andSoftLense / value3) / Math.log(2));
		if (age3andNoLense != 0)
			age3Gain += -age3andNoLense / value3 * (Math.log(age3andNoLense / value3) / Math.log(2));

		gain = entropy - (value1 / remaining.size() * age1Gain + value2 / remaining.size() * age2Gain
				+ value3 / remaining.size() * age3Gain);

		return gain;
	}
}