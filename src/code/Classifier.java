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
	private double incorrectlyClassified;
	private Node treeToPrune;
	private Node copyOfOriginal;
	private Node originalRoot;
	double correctClassifiedOriginal;

	public static void main(String[] args) {
		Classifier c = new Classifier();
	}

	public Classifier() {
		fr = new FileReader();
		fr.readFile2("src/voteData.txt");
		tb = new TreeBuilder(fr);
		originalRoot = tb.getRoot();

		copyOfOriginal = new Node();
		copyOfOriginal = originalRoot.cloneNode(originalRoot, copyOfOriginal);

		treeToPrune = new Node();
		treeToPrune = originalRoot.cloneNode(originalRoot, treeToPrune);

		incorrectlyClassified = 0;
		classify(fr.getClassificationInput(), tb.getRoot());
		tb.printTree(originalRoot, 0, "/-------");

		correctClassifiedOriginal = (100 - ((incorrectlyClassified / fr.getClassificationInput().size()) * 100));
		System.out.println("Amount of classified data incorrectly placed " + incorrectlyClassified
				+ ". Amount Classified " + fr.getClassificationInput().size() + ". Percentage correctly classified = "
				+ correctClassifiedOriginal);

		System.out.println(treeToPrune.getOwnData().size());
		System.out.println(treeToPrune.getChildren().get(0).getOwnData().size());
		System.out.println(treeToPrune.getChildren().get(1).getOwnData().size());
		System.out.println(originalRoot.equals(treeToPrune));
		System.out.println(treeToPrune != originalRoot);

		reducedErrorPruning(treeToPrune);
		tb.printTree(treeToPrune, 0, "/-------");

	}

	/**
	 * Takes test data and initiates sorting for each instance found in the test
	 * data
	 * 
	 * @param testData
	 */
	private void classify(ArrayList<Instance> testData, Node n) {
		System.out.println();

		for (Instance i : testData) {
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

				if (childSize == 2) {
					if (branch == 1) {
						child = node.getChildren().get(0);
					} else if (branch == 2) {
						child = node.getChildren().get(1);
					}
				} else if (childSize == 1) {
					child = node.getChildren().get(0);
				}

				child.setClassifiedData(i);

				if (child.getAttribute() == 16) {
					calcIncorrectlyPlaced(child, i);
					return;
				} else {
					sortNode(child, i);
				}
			}
		}
	}

	/**
	 * Pruning a decision node consists of removing subtree rooted at that node,
	 * making it a leaf node, and assigning it the most common classification of
	 * the training examples associated with that node. Produce smallest
	 * version of most accurate subtree
	 **/

	public void reducedErrorPruning(Node root) {

		for (Node child : new ArrayList<>(root.getChildren())) {
			// if not a leaf node continue down the tree
			if (child.getAttribute() != 16) {
				reducedErrorPruning(child);
				// if the node is a leaf node
			} else if (child.getAttribute() == 16) {
				// if the nodes contain the same data we can remove the child
				// and continue
				if (child.getOwnData().size() == root.getOwnData().size()) {
					root.removeChildren(child);
					root.setAttribute(16);
					copyOfOriginal = new Node();
					copyOfOriginal = copyOriginal(copyOfOriginal, treeToPrune);
					System.out.println("Removed a child!");
					System.out.println("save tree");
				} else {
					// remove the child

					if (root.getChildren().size() == 1) {
						root.setAttribute(16);
					}
					
					
					root.removeChildren(child);

					tb.assignLabel(root, root.getOwnData());
					incorrectlyClassified = 0;

					classify(fr.getClassificationInput(), treeToPrune);
					double correctClassified = (100
							- ((incorrectlyClassified / fr.getClassificationInput().size()) * 100));
					System.out.println("Amount of classified data incorrectly placed " + incorrectlyClassified
							+ ". Amount Classified " + fr.getClassificationInput().size()
							+ ". Percentage correctly classified = " + correctClassified);

					// need to check if less or more
					if (correctClassified >= correctClassifiedOriginal) {
						copyOfOriginal = new Node();
						copyOfOriginal = copyOriginal(copyOfOriginal, treeToPrune);
						System.out.println("save tree");
					} else {
						System.out.println("reset tree");
						treeToPrune = new Node();
						treeToPrune = copyOriginal(treeToPrune, copyOfOriginal);
					}

				}
			}
		}
	}

	public Node copyOriginal(Node newNode, Node copyThis) {
		newNode = copyThis.cloneNode(copyThis, newNode);
		return newNode;
	}

	public void calcIncorrectlyPlaced(Node child, Instance i) {
		String label = child.getLabel();
		int integerRepresentationOfLabel = 2;

		if (label.equals("Republican")) {
			integerRepresentationOfLabel = 1;
		}

		if (i.getClassification() != integerRepresentationOfLabel)
			incorrectlyClassified++;

	}

}
