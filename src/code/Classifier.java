package code;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience Classifier.java Purpose:
 * Initiates the start of the program by instructing the decision tree to be
 * built by the tree builder class, using data read in from the file reader
 * class. This class then uses the built tree to perform classification and
 * pruning.
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class Classifier {

	private FileReader fileReader;
	private TreeBuilder treeBuilder;
	private Node originalRoot;
	private double incorrectlyClassified;
	private double correctClassifiedOriginal;
	private double correctClassifiedNew;
	private ArrayList<Instance> trainingData;
	private ArrayList<Instance> testData;
	private String filename = "src/voteData.txt";

	public static void main(String[] args) {
		new Classifier();
	}

	public Classifier() {

		fileReader = new FileReader();

		try {
			fileReader.readFile(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		trainingData = fileReader.getTrainingInput();
		testData = fileReader.getTestInput();
		System.out.println(
				"========================================================================================================");
		System.out.println("Tree built from training data");
		System.out.println(
				"========================================================================================================");
		treeBuilder = new TreeBuilder(trainingData);
		originalRoot = treeBuilder.getRoot();

		incorrectlyClassified = 0;
		classify(testData, originalRoot);

		System.out.println(
				"========================================================================================================");
		System.out.println("Tree with test data being classified on the original tree");
		System.out.println(
				"========================================================================================================");
		treeBuilder.printTree("", true, originalRoot, 0);

		correctClassifiedOriginal = (100 - ((incorrectlyClassified / testData.size()) * 100));

		System.out.println(
				"Amount of classified data incorrectly placed " + incorrectlyClassified + ". Amount Classified "
						+ testData.size() + ". Percentage correctly classified = " + correctClassifiedOriginal);
		System.out.println(
				
				"========================================================================================================");
		System.out.println("Pruning the tree...");
		prune(originalRoot);

		System.out.println(
				"========================================================================================================");
System.out.println(
				
				"========================================================================================================");
		System.out.println("Pruned Tree");
System.out.println(
				
				"========================================================================================================");
		treeBuilder.printTree("", true, originalRoot, 0);

		System.out.println(
				"Amount of classified data incorrectly placed " + incorrectlyClassified + ". Amount Classified "
						+ testData.size() + ". Percentage correctly classified = " + correctClassifiedOriginal);
	}

	/**
	 * Takes test data and initiates sorting for each instance found in the test
	 * data.
	 * 
	 * @param testData
	 *            The data to be classified on the tree.
	 * @param node
	 *            The node from which data will be classified.
	 */
	private void classify(ArrayList<Instance> testData, Node node) {
		System.out.println();
		for (Instance instance : testData) {
			node.setClassifiedData(instance);
			sortNode(node, instance);
		}
	}

	/**
	 * Attempts to sort a given instance
	 * 
	 * @param node
	 *            currently being sorted
	 * @param instance
	 *            an instance
	 */
	private void sortNode(Node node, Instance instance) {

		int attribute = node.getAttribute();

		// Get the matching Instance Attribute value, could be 1 or 2 or 3
		int instanceAttributeValue = instance.getAttributeValue(attribute);

		AttributeGetter attgetter = new AttributeGetter(node.getAttribute());

		// if the value is 3 we have to randomly assign a value.
		if (instanceAttributeValue == 3) {
			EntropyCalculator entropyCalculator = new EntropyCalculator();
			entropyCalculator.checkAndAssignValue(testData, attgetter.getAttributefromValue());
			instanceAttributeValue = instance.getAttributeValue(attribute);
		}

		int childSize = node.getChildren().size();
		if (childSize == 0) {
			calcIncorrectlyPlaced(node, instance);
		}
		//Move the new instance down the tree until a leaf is found
		for (Node child : node.getChildren()) {
			if (childSize == 2) {
				if (instanceAttributeValue == 1 && child == node.getChildren().get(0)) {
					if (child.getAttribute() == 16) {
						child.setClassifiedData(instance);
						calcIncorrectlyPlaced(child, instance);
					} else {
						child.setClassifiedData(instance);
						sortNode(child, instance);
					}
				} else if (instanceAttributeValue == 2 && child == node.getChildren().get(1)) {
					if (child.getAttribute() == 16) {
						child.setClassifiedData(instance);
						calcIncorrectlyPlaced(child, instance);
					} else {
						child.setClassifiedData(instance);
						sortNode(child, instance);
					}
				}
			} else if (childSize == 1) {
				if (child.getAttribute() == 16) {
					child.setClassifiedData(instance);
					calcIncorrectlyPlaced(child, instance);
				} else {
					child.setClassifiedData(instance);
					sortNode(child, instance);
				}
			}
		}
	}

	/**
	 * Initiates tree pruning and tree clean up.
	 * 
	 * @param node
	 *            The node to be pruned.
	 **/
	public void prune(Node node) {
		pruneTree(node);
		cleanPath(node);
	}

	/**
	 * Pruning a decision node consists of removing subtree rooted at that node,
	 * making it a leaf node, and assigning it the most common classification of
	 * the training examples associated with that node.
	 * 
	 * @param node
	 *            The node to be pruned from.
	 */
	public void pruneTree(Node node) {

		//Copy the node before removing all its child nodes
		Node tempNode = new Node();
		tempNode = node.cloneNode(node, tempNode);

		for (Node child : new ArrayList<Node>(node.getChildren())) {
			node.removeChildren(child);
		}
		// Remove all the classification data and then re-classify 
		removeClassifiedData(originalRoot);
		double tempWrong = incorrectlyClassified;

		incorrectlyClassified = 0;
		classify(testData, originalRoot);

		correctClassifiedNew = (100 - ((incorrectlyClassified / testData.size()) * 100));

		if (correctClassifiedNew < correctClassifiedOriginal) {
			System.out.println("Removed a node, new classification = %" + correctClassifiedNew + ", old classification = %" + correctClassifiedOriginal + ". Putting the node back!");
			node = tempNode.cloneNode(tempNode, node);
			incorrectlyClassified = tempWrong;
		} else {
			node.setAttribute(16);
			System.out.println("Removed a node, new classification = %" + correctClassifiedNew + ", old classification = %" + correctClassifiedOriginal);
			correctClassifiedOriginal = correctClassifiedNew;
		}

		for (Node child : node.getChildren()) {
			pruneTree(child);
		}
	}

	/**
	 * If a node and a leaf node have the same amount of instances the leaf node
	 * can be removed and the parent node turned into a leaf node.
	 * 
	 * @param node
	 *            The node from which the downstream path should be cleaned.
	 */
	public void cleanPath(Node node) {
		for (Node child : new ArrayList<>(node.getChildren())) {
			if (child.getAttribute() != 16) {
				cleanPath(child);
			} else {
				if (node.getChildren().size() == 1) {
					if (node.getOwnData().size() == child.getOwnData().size()) {
						node.removeChildren(child);
						node.setAttribute(16);
						System.out.println("Removed a single child node to clear a pathway.");
						if (node.getParent() != null)
							cleanPath(node.getParent());
					}
				}
			}
		}
	}

	/**
	 * Removes all test data from nodes on the tree from the point of the node
	 * past into the method.
	 * 
	 * @param node
	 *            The node from which all test data will be removed.
	 */
	public void removeClassifiedData(Node node) {
		for (Instance instance : new ArrayList<>(node.getOwnData())) {
			if (testData.contains(instance)) {
				node.removeClassifyData(instance);
			}
		}
		for (Node child : node.getChildren()) {
			removeClassifiedData(child);
		}
	}

	/**
	 * Method required to find out how many instances are incorrectly classified
	 * on a node.
	 * 
	 * @param child
	 *            The node that will have the amount of incorrectly classified
	 *            test data assigned to it counted.
	 * @param instance
	 *            The instance to be compared against the rest of the instances
	 *            in a node.
	 */
	public void calcIncorrectlyPlaced(Node child, Instance instance) {
		String label = child.getLabel();
		int integerRepresentationOfLabel = 2;

		if (label.equals("Republican")) {
			integerRepresentationOfLabel = 1;
		}

		if (instance.getClassification() != integerRepresentationOfLabel) {
			incorrectlyClassified++;
		}
	}
}
