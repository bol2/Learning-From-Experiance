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
	private double lowestIncorrectlyClassified;
	private Node originalRoot;
	double correctClassifiedOriginal;
	double correctClassifiedNew;
	private int totalsorted = 0;
	private ArrayList<Instance> tempy;
	private ArrayList<Instance> classificationData;

	public static void main(String[] args) {
		Classifier c = new Classifier();
	}

	public Classifier() {
		fr = new FileReader();
		fr.readFile2("src/voteData.txt");
		tb = new TreeBuilder(fr);
		originalRoot = tb.getRoot();
		classificationData = fr.getClassificationInput();
		tempy = new ArrayList<Instance>(fr.getTrainingInput());
		lowestIncorrectlyClassified = 0;

		incorrectlyClassified = 0;
		classify(classificationData, originalRoot);
		tb.printTree(originalRoot, 0, "/-------");

		correctClassifiedOriginal = (100 - ((incorrectlyClassified / classificationData.size()) * 100));
		System.out.println("Amount of classified data incorrectly placed " + incorrectlyClassified
				+ ". Amount Classified " + classificationData.size() + ". Percentage correctly classified = "
				+ correctClassifiedOriginal);

		//prune(originalRoot);
		// cleanPath(originalRoot);
		 prune2(originalRoot);
		
		  //for(Instance i : new ArrayList<>(originalRoot.getOwnData())){ if
		  //(tempy.contains(i)){ originalRoot.removeClassifyData(i); } }
		  
		 //removeOriginal(originalRoot);
		// cleanPath(originalRoot);

		tb.printTree(originalRoot, 0, "/-------");

		System.out.println("Amount of classified data incorrectly placed " + lowestIncorrectlyClassified
				+ ". Amount Classified " + classificationData.size() + ". Percentage correctly classified = "
				+ correctClassifiedOriginal);

		}


	public void removeOriginal(Node n) {
		for (Node child : new ArrayList<>(n.getChildren())) {
			for (Instance i : new ArrayList<>(child.getOwnData())) {
				if (tempy.contains(i)) {
					child.removeClassifyData(i);
				}
			}
			removeOriginal(child);
		}
	}

	/**
	 * Takes test data and initiates sorting for each instance found in the test
	 * data
	 * 
	 * @param testData
	 */
	private void classify(ArrayList<Instance> testData, Node n) {
		System.out.println();
		int counter = 0;
		totalsorted = 0;

		for (Instance i : testData) {
			n.setClassifiedData(i);
			sortNode(n, i);
			counter++;
		}

		System.out.println(counter + "counter" + totalsorted);
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
		if (instanceAttributeValue == 3) {
			EntropyCalculator ec = new EntropyCalculator();
			ec.checkAndAssignValue(classificationData, attgetter.getAttributefromValue());
			instanceAttributeValue = i.getAttributeValue(attribute);
		}

		// for each value in list of possible branches , could be 1 or 2 or 1
		// and 2

		int childSize = node.getChildren().size();
		for (Node child : node.getChildren()) {
			if (childSize == 2) {
				if (instanceAttributeValue == 1 && child == node.getChildren().get(0)) {
					if (child.getAttribute() == 16) {
						child.setClassifiedData(i);
						calcIncorrectlyPlaced(child, i);
						totalsorted++;
					} else {
						child.setClassifiedData(i);
						sortNode(child, i);
					}
				} else if (instanceAttributeValue == 2 && child == node.getChildren().get(1)) {
					if (child.getAttribute() == 16) {
						child.setClassifiedData(i);
						calcIncorrectlyPlaced(child, i);
						totalsorted++;
					} else {
						child.setClassifiedData(i);
						sortNode(child, i);
					}
				}

			} else if (childSize == 1) {
				if (child.getAttribute() == 16) {
					child.setClassifiedData(i);
					calcIncorrectlyPlaced(child, i);
					totalsorted++;
				} else {
					child.setClassifiedData(i);
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

	public void prune(Node node) {
		prune2(node);
		cleanPath(node);
	}

	/*public void prune2(Node node) {
		// Loop backwards through the array list, pick last child first.
		for (int x = new ArrayList<>(node.getChildren()).size() - 1; x >= 0; x--) {
			Node child = node.getChildren().get(x);
			// If we havent reached a child node yet then we go back to the algorithm and recall it.
			if (child.getAttribute() != 16) {
				prune2(child);
			} else {
				// Prune a temporary array? If the result is better prune the original. 
				
				// If there is more than one i'm placing the first in the array at the end! 
				removeClassifiedData(originalRoot);
				incorrectlyClassified = 0;
				node.removeChildren(child);
				int tempAttribute = node.getAttribute();
				if (node.getChildren().size() == 0) {
					node.setAttribute(16);
				}
				
				classify(classificationData, originalRoot);
				correctClassifiedNew = (100 - ((incorrectlyClassified / classificationData.size()) * 100));
				if (correctClassifiedNew <= correctClassifiedOriginal) {
					System.out.println("put the node back!" + correctClassifiedNew + ", " + correctClassifiedOriginal);
					node.setChildren(child);
					node.setAttribute(tempAttribute);
				} else {
					System.out.println("Removed a Node!");	
					System.out.println("" + correctClassifiedNew + "vs " + correctClassifiedOriginal);
					correctClassifiedOriginal = correctClassifiedNew;
					lowestIncorrectlyClassified = incorrectlyClassified;

				}
			}
		}
	}*/
	
	public void prune2(Node node) {
		
		// check if all children
		boolean allLeaf = true;
		for (Node child : node.getChildren()) {
		 if (child.getAttribute() != 16) allLeaf = false;
		}
		
		if (allLeaf == false){
			for (Node child : node.getChildren()){
				prune2(child);
			}
		}
		// if all nodes are children, prune the node and check classification
		else{
			Node tempSave = new Node();
			tempSave = node.cloneNode(node, tempSave);
			
			node.setAttribute(16);
			for (Node child : new ArrayList<Node>(node.getChildren())){
				node.removeChildren(child);
			}
			
			//remove classification data 
			removeClassifiedData(originalRoot);
			incorrectlyClassified = 0;
			
			// re classify and compaire 
			classify(classificationData, originalRoot);
			correctClassifiedNew = (100 - ((incorrectlyClassified / classificationData.size()) * 100));
			
			if (correctClassifiedNew <= correctClassifiedOriginal) {
				System.out.println("put the node back!" + correctClassifiedNew + ", " + correctClassifiedOriginal);
				node = tempSave;
			} else {
				System.out.println("Removed a Node!");	
				System.out.println("" + correctClassifiedNew + "vs " + correctClassifiedOriginal);
				correctClassifiedOriginal = correctClassifiedNew;
				lowestIncorrectlyClassified = incorrectlyClassified;
			}
			
		}
	}

	public void cleanPath(Node node) {
		for (Node child : new ArrayList<>(node.getChildren())) {
			// If not child continue
			if (child.getAttribute() != 16) {
				cleanPath(child);
			} else {
				if (node.getChildren().size() == 1) {
					if (node.getOwnData().size() == child.getOwnData().size()) {

						node.removeChildren(child);
						node.setAttribute(16);
						System.out.println("Removed a single child node!");
						cleanPath(node.getPerant());
					}
				}
			}
		}
	}

	public void removeClassifiedData(Node n) {

		for (Instance i : new ArrayList<>(n.getOwnData())) {
			if (classificationData.contains(i)) {
				n.removeClassifyData(i);
			}
		}
		for (Node child : n.getChildren()) {
			removeClassifiedData(child);
		}
	}

	public void calcIncorrectlyPlaced(Node child, Instance i) {
		String label = child.getLabel();
		int integerRepresentationOfLabel = 2;

		if (label.equals("Republican")) {
			integerRepresentationOfLabel = 1;
		}

		if (i.getClassification() != integerRepresentationOfLabel){
			incorrectlyClassified++;
		}

	}

}
