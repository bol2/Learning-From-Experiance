package code;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CS39440 Major Project: Learning From Experience
 * FileReader.java
 * Purpose: To read data set in from a file and create new instances from the data
 * 
 * @author Ben Larking
 * @version 1.5 29/02/16
 */

public class FileReader {

	private String file;
	private ArrayList<Instance> input;
	private ArrayList<Instance> testData;

	public FileReader() {
		input = new ArrayList<Instance>();
		testData = new ArrayList<Instance>();
		readFile();
	}

	public void readFile() {
		
		file = "src/LensData.txt";

		try {
			Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					int id_key = scanner.nextInt();
					int age = scanner.nextInt();
					int preseription = scanner.nextInt();
					int astigmatic = scanner.nextInt();
					int tearProdRate = scanner.nextInt();
					int classification = scanner.nextInt();

					Instance instance = new Instance(age, preseription, astigmatic, tearProdRate, classification, id_key);
					input.add(instance);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		file = "src/ClassificationData.txt";
		
		try {
			Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					int id_key = scanner.nextInt();
					int age = scanner.nextInt();
					int preseription = scanner.nextInt();
					int astigmatic = scanner.nextInt();
					int tearProdRate = scanner.nextInt();
					int classification = scanner.nextInt();

					Instance instance = new Instance(age, preseription, astigmatic, tearProdRate, classification, id_key);
					testData.add(instance);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Instance> getInput() {
		return input;
	}
	
	public ArrayList<Instance> getTestData() {
		return testData;
	}
}
