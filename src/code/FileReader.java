package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * CS39440 Major Project: Learning From Experience FileReader.java Purpose: To
 * read data set in from a file and create new instances from the data
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class FileReader {

	private ArrayList<Instance> trainingData;
	private ArrayList<Instance> testData;

	public FileReader() {
		trainingData = new ArrayList<Instance>();
		testData = new ArrayList<Instance>();
	}

	/**
	 * Reads a given file and assigns to a given array list. Used to read in the
	 * training and test data.
	 * 
	 * @param fileString The location of the file containing data to be read in
	 * @throws IOException 
	 */
	public void readFile(String fileString) throws IOException {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new java.io.FileReader(fileString));
			
			String line = null;
			Random rn = new Random();
			
			while ((line = br.readLine()) != null) {
				Instance i = new Instance();
				String[] split = line.split(",");
				int attribute = 0;
				for (String bit : split) {
					if (bit.equals("republican")) {

						i.setAttributeValue(attribute, 1);
					} else if (bit.equals("democrat")) {
						i.setAttributeValue(attribute, 2);
					} else if (bit.equals("y")) {
						i.setAttributeValue(attribute, 1);
					} else if (bit.equals("n")) {
						i.setAttributeValue(attribute, 2);
					} else if (bit.equals("?")) {
						i.setAttributeValue(attribute, 3);
					}else {
						br.close();
						throw new IOException();
					}
					attribute++;
				}
				
				double randomValue = rn.nextDouble();
				double percentTraining = 0.6;

				if (randomValue <= percentTraining) {
					trainingData.add(i);
				} else if (randomValue > percentTraining) {
					testData.add(i);
				} 
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	}

	/**
	 * Method returns an array list of data to be used when building the tree.
	 * @return Array list of training data.
	 */
	public ArrayList<Instance> getTrainingInput() {
		return trainingData;
	}

	/**
	 * Method returns an array list of data to be used for testing the built tree.
	 * @return Array list of test data.
	 */
	public ArrayList<Instance> getTestInput() {
		return testData;
	}
}
