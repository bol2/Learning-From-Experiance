package code;

import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience EntropyCalculator.java
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

		return entropy;
	}

	public int calculateHighestGain(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining) {

		int value = 0;

		ArrayList<Double> doubles = new ArrayList<Double>();
		ArrayList<Attribute> addedAttributes = new ArrayList<Attribute>();

		for (Attribute attribute : attributesRemaining) {
			ag = new AttributeGetter(attribute);
			int attributeNumer = ag.getAttribute();
			doubles.add(calculateInformationGain(remaining, attributeNumer));
			addedAttributes.add(attribute);
			System.out.println("Added to added Attributes " + calculateInformationGain(remaining, attributeNumer));
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
	
	public double calculateInformationGain(ArrayList<Instance> remaining, int attribute){
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

		gain = entropy
				- (republicanAppearances / remaining.size() * yesGain + democratAppearances / remaining.size() * noGain);
		return gain;
		
	}
}