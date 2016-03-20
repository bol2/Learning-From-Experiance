package code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CS39440 Major Project: Learning From Experience FileReader.java Purpose: To
 * read data set in from a file and create new instances from the data
 * 
 * @author Ben Larking
 * @version 1.6 14/03/16
 */

public class FileReader {

	private String trainingFile;
	private String classificationFile;
	private ArrayList<Instance> trainingData;
	private ArrayList<Instance> classificationData;

	public FileReader() {
		trainingData = new ArrayList<Instance>();
		classificationData = new ArrayList<Instance>();
		trainingFile = "src/voteData.txt";
		classificationFile = "src/ClassificationData.txt";
		readFile(trainingFile, trainingData);
		readFile(classificationFile, classificationData);
		
		
	}

	/**
	 * Reads a given file and assigns to a given array list. Used to read in the training and classification data.
	 * @param fileString
	 * @param data
	 */
	public void readFile(String fileString, ArrayList<Instance> data) {


		BufferedReader br = null;
		try {
			br = new BufferedReader(new java.io.FileReader(fileString));

			String line = null;

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
					}
					attribute ++;
				}
				data.add(i);
				//System.out.println(i.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Instance> getTrainingInput() {
		return trainingData;
	}
	
	public ArrayList<Instance> getClassificationInput() {
		return classificationData;
	}
}
