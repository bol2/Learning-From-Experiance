package code;

import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience Main.java Purpose: Initiates
 * the start of the program by creating a tree builder class
 * 
 * @author Ben Larking
 * @version 1.6 14/03/16
 */

public class Classifier {

	private FileReader fr;
	private TreeBuilder tb;

	public static void main(String[] args) {
		Classifier c = new Classifier();
	}

	public Classifier() {
		fr = new FileReader();
		fr.readFile2("src/voteData.txt");
		tb = new TreeBuilder(fr);
		
		classify(fr.getClassificationInput());
		tb.printTree(tb.getRoot(), 0, "/-------");
	}

	/**
	 * Takes test data and initiates sorting for each instance found in the test
	 * data
	 * 
	 * @param testData
	 */
	private void classify(ArrayList<Instance> testData) {
		System.out.println();
		for (Instance i : testData) {
			System.out.println(i.toString());
		}

		for (Instance i : testData) {
			Node n = tb.getRoot();
			sortNode(n, i);
			n.setClassifiedData(i);
		}
	}

	/**
	 * Attempts to sort a given instance
	 * 
	 * @param node
	 *            currently being sorted
	 * @param i
	 *            an instance
	 */
	private void sortNode(Node node, Instance i) {

		// Get the nodes Attribute
		int attribute = node.getAttribute();
		// Get the list of possible attribute vales, could be 1 or 2 , (1,2)
		ArrayList<Integer> branches = node.getValues();

		// Get the matching Instance Attribute value, could be 1 or 2 or 3
		int instanceAttributeValue = i.getAttributeValue(attribute);

		AttributeGetter attgetter = new AttributeGetter(node.getAttribute());

		// if the value is 3 we have to randomly assign a value.
		if (i.getAttributeValue(attribute) == 3) {
			EntropyCalculator ec = new EntropyCalculator();
			ec.checkAndAssignValue(fr.getClassificationInput(), attgetter.getAttributefromValue());
		}

		// for each value in list of possible branches , could be 1 or 2 or 1
		// and 2
		for (Integer branch : branches) {

			if (branch == instanceAttributeValue) {
					Node child = null;
					int childSize = node.getChildren().size();
					
					if (childSize == 2 ){
						if (branch == 1) {
							child = node.getChildren().get(0);
						}else if (branch == 2){
							child = node.getChildren().get(1);	
						}
					}else if (childSize == 1 ){
						child = node.getChildren().get(0);
					}
					
					child.setClassifiedData(i);

					if (child.getAttribute() == 16) {
						return;
					} else {
						sortNode(child, i);
					}
			}
		}
	}

}
