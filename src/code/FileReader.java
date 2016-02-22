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
 * @version 1.4 22/02/16
 */

public class FileReader {

	public static final String file = "src/LensData.txt";
	private ArrayList<Instance> input = null;

	public FileReader() {
		input = new ArrayList<Instance>();
		readFile();
	}

	public void readFile() {

		try {
			Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					int id_key = scanner.nextInt();
					int first_key = scanner.nextInt();
					int second_key = scanner.nextInt();
					int third_key = scanner.nextInt();
					int fourth_key = scanner.nextInt();
					int fith_key = scanner.nextInt();

					Instance instance = new Instance(first_key, second_key, third_key, fourth_key, fith_key, id_key);
					input.add(instance);
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
}
