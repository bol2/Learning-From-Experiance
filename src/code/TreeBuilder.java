package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * CS39440 Major Project: Learning From Experience TreeBuilder.java Purpose:
 * Builds the tree using the data read in from from the file reader.Utalises the
 * entropy calculator.
 * 
 * @author Ben Larking
 * @version 1.4 23/02/16
 */

public class TreeBuilder {

	private EntropyCalculator ec;
	private ArrayList<Instance> remaining = null;
	private ArrayList<Attribute> attributesRemaining = null;
	private FileReader fr;
	private Node first;

	public TreeBuilder() {

		fr = new FileReader();
		remaining = fr.getInput();

		attributesRemaining = new ArrayList<Attribute>();
		attributesRemaining.add(Attribute.AGE);
		attributesRemaining.add(Attribute.PERSCRIPTION);
		attributesRemaining.add(Attribute.ASTIGMATIC);
		attributesRemaining.add(Attribute.TEARPRODRATE);

		ec = new EntropyCalculator();

		MasterID3(remaining, attributesRemaining);
	}

	public void MasterID3(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining) {
		Node root = new Node();
		boolean allSame = true;

		Instance testInstance = remaining.get(0);
		int k = 0;
		while (k < remaining.size()) {
			if (remaining.get(k).getClassification() != testInstance.getClassification())
				allSame = false;
			k++;
		}

		// Create the one leaf node tree.
		if (allSame == true) {
			System.out.println(
					"All Classifications are the same. The tree is a single node tree with the following label "
							+ testInstance.getClassification());
			for (int i = 0; i < remaining.size(); i++) {
				root.setData(remaining.get(i).getClassification(), remaining.get(i));
			}
			return;
		}

		ID3(remaining, attributesRemaining, root);
		printTree(root, 0);
		System.out.println();
		System.out.println();
		printTree2(root, 0);
		first = root;

	}

	public void ID3(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining, Node perant) {

		// calculate the entropy and the highest gain
		ec.calculateEntropy(remaining);
		int attribute = ec.calculateHighestGain(remaining, attributesRemaining);
		System.out.println("Attribute with highest Gain = " + getAttribute(attribute));

		// set root as highest gain and get the values for the node. Create
		// branches for the values
		Node root = perant;
		root.setOwnData(remaining);

		root.setAttribute(attribute);
		AttributeGetter ag = new AttributeGetter(getAttribute(attribute));
		root.setValues(ag.getAttributeValues());
		System.out.println("This is the values for root" + root.getValues());

		// Create branches for values
		for (int i = 0; i < root.getValues().size(); i++) {

			// Create a subset of values for each branch
			ArrayList<Instance> temp = new ArrayList<Instance>();
			for (int y = 0; y < remaining.size(); y++) {

				if (remaining.get(y).getAttributeValue(attribute) == root.getValues().get(i)) {
					System.out.println("Adding to temp on condition" + remaining.get(y).getAttributeValue(attribute)
							+ " " + root.getValues().get(i));
					temp.add(remaining.get(y));
				}
			}

			// If all values in subset are the same create a child Node
			if (allSameClassification(temp) == true) {
				// if all the same pull out
				Node child = new Node();
				child.setOwnData(temp);
				System.out.println("All the above temp data had the same classification.");
				for (int x = 0; x < temp.size(); x++) {
					child.setData(i, temp.get(x));
				}
				System.out.println("Adding the above to a child and setting it to root's child.");

				root.setChildren(child);

			}

			// Add a subtree
			else if (allSameClassification(temp) == false) {

				// Remove an attribute
				ArrayList<Attribute> tempAttributesRemaining = new ArrayList<Attribute>(attributesRemaining);
				tempAttributesRemaining.remove(attribute);

				Node child = new Node();

				for (int x = 0; x < temp.size(); x++) {
					child.setData(i, temp.get(x));
					// root.setOwnData(remaining.get(x));
				}

				child.setAttribute(attribute);
				root.setChildren(child);

				System.out.println("This is the passed in one " + perant.getAttribute());
				for (int o = 0; o < root.getChildren().size(); o++) {
					System.out.println("Set for the new root" + root.getChildren().get(o).getData());
				}

				System.out.println();
				System.out.println("Created a child and assigned above to root.");

				ID3(temp, tempAttributesRemaining, child);
			}
		}
	}

	public boolean allSameClassification(ArrayList<Instance> remaining) {
		// Test if all examples are the same, if so return single node tree
		boolean allSame = true;

		Instance testInstance = remaining.get(0);

		for (int i = 0; i < remaining.size(); i++) {
			if (remaining.get(i).getClassification() != testInstance.getClassification())
				allSame = false;
		}
		return allSame;
	}

	public Attribute getAttribute(int i) {
		Attribute a = null;
		if (i == 0)
			a = Attribute.AGE;
		else if (i == 1)
			a = Attribute.PERSCRIPTION;
		else if (i == 2)
			a = Attribute.ASTIGMATIC;
		else if (i == 3)
			a = Attribute.TEARPRODRATE;
		else if (i == 4)
			a = Attribute.LEAF;
		return a;
	}

	// Method to Print the Tree
	public void printTree(Node root, int level) {

		System.out.println("\nAtribute: " + getAttribute(root.getAttribute()));
		for (int i = 0; i < root.getOwnData().size(); i++) {
			System.out.print(root.getOwnData().get(i).getId());
		}

		for (int k = 0; k < root.getValues().size(); k++) {
			printTree(root.getChildren().get(k), 0);
		}

	}

	public void printTree2(Node root, int level) {
		// Get amount of children ( -1 will be a leaf)
		int k = root.getChildren().size() - 1;

		// If not a leaf continue going down tree
		if (k != -1)
			printTree2(root.getChildren().get(k), level + 1);

		// If we're not at the top of the tree, first time will be at the bottom
		if (level != 0) {

			// For every level print some white space
			for (int i = 0; i < level - 1; i++) {
				System.out.print("|\t");
			}
			if (root.getAttribute() == 4) {

				// Print a line of data
				System.out.print("|-------");
				for (int i = 0; i < root.getOwnData().size(); i++) {
					System.out.print(root.getOwnData().get(i).getId() + ", ");
				}
				System.out.println();
			}else {
				// Print a line of data
				System.out.print("|-------");
				for (int i = 0; i < root.getOwnData().size(); i++) {
					System.out.print(root.getOwnData().get(i).getId() + ", ");
				}
				System.out.println();
				if (k!= 1)
				printTree2(root.getChildren().get(k -2), level + 1);
				if (k!= 0)
					printTree2(root.getChildren().get(k -1), level + 1);
				
				
			}

		} else {
			for (int i = 0; i < root.getOwnData().size(); i++) {
				System.out.print(root.getOwnData().get(i).getId() + ", ");
			}
			
			System.out.println();
			printTree2(root.getChildren().get(k-1), level + 1);
		}
	}

	public Node getRoot() {
		return first;
	}

}