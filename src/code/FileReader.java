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
 * @version 1.5 29/02/16
 */

public class FileReader {

	private String file;
	private ArrayList<Instance> input;

	public FileReader() {
		input = new ArrayList<Instance>();
		readFile();
	}

	public void readFile() {

		file = "src/voteData.txt";

		BufferedReader br = null;
		try {
			br = new BufferedReader(new java.io.FileReader(file));

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
				System.out.println(i.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Instance> getInput() {
		return input;
	}
}
