package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
	private static AttributeGetter attgetter;

	public TreeBuilder() {

		fr = new FileReader();
		remaining = fr.getInput();

		attributesRemaining = new ArrayList<Attribute>();
		for (Attribute attribute : Attribute.values()) {
			attributesRemaining.add(attribute);
		}
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
			root.setOwnData(remaining);
			return;
		}
		
		

		ID3(remaining, attributesRemaining, root);

		System.out.println();
		System.out.println();
		printTree(root, 0);
		// printTree2(root, 0);
		first = root;

	}

	public void ID3(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining, Node perant) {

		// calculate the entropy and the highest gain
	
		
		
		ec.setEntropy(0);
		ec.calculateEntropy(remaining);
		int attribute = ec.calculateHighestGain(remaining, attributesRemaining);
		System.out.println("Attribute with highest Gain = " + attributesRemaining.get(attribute).toString());
		ec.checkAndAssignValue(remaining, attributesRemaining.get(attribute));

		// set root as highest gain and get the values for the node. Create
		// branches for the values
		Node root = perant;
		root.setOwnData(remaining);
		AttributeGetter ag = new AttributeGetter(attributesRemaining.get(attribute));
		root.setAttribute(ag.getAttribute());
		System.out.println("This is the values for root" + root.getValues().length);

		// Create branches for values
		for (int i = 0; i < root.getValues().length; i++) {

			// Create a subset of values for each branch
			ArrayList<Instance> temp = new ArrayList<Instance>();
			for (int y = 0; y < remaining.size(); y++) {

				if (remaining.get(y).getAttributeValue(ag.getAttribute()) == root.getValues()[i]) {
					// System.out.println("Adding to temp on condition" +
					// remaining.get(y).getAttributeValue(attribute)
					// + " " + root.getValues()[i]);
					temp.add(remaining.get(y));
				}
			}

			// If all values in subset are the same create a leaf Node
			if (temp.size() != 0 && allSameClassification(temp) == true) {
				// if all the same pull out
				Node child = new Node();
				child.setOwnData(temp);
				child.setAttribute(16);
				root.setChildren(child);
			}

			// Add a subtree
			else if (temp.size() != 0 && allSameClassification(temp) == false) {

				// Remove an attribute
				ArrayList<Attribute> tempAttributesRemaining = new ArrayList<Attribute>(attributesRemaining);

				tempAttributesRemaining.remove(attribute);
				// attributesRemaining.remove(attribute);

				Node child = new Node();

				child.setOwnData(temp);

				if (tempAttributesRemaining.size() != 0) {
					// child.setAttribute(attribute);
					root.setChildren(child);

					System.out.println("This is the passed in one " + perant.getAttribute());
					for (int o = 0; o < root.getChildren().size(); o++) {
						System.out.println("Set for the new root" + root.getChildren().get(o).getOwnData());
					}

					System.out.println("Created a child and assigned above to root.");

					ID3(temp, tempAttributesRemaining, child);
				} else {
					child.setAttribute(16);
					root.setChildren(child);
				}
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

	public Node getRoot() {
		return first;
	}

	public void printTree(Node root, int level) {
		if (root == null)
			return;

		printTree(root.getRight(), level + 1);
		int republicans = getNumberOfVotes(root.getOwnData(), 1);
		int democrats = getNumberOfVotes(root.getOwnData(), 2);
		attgetter = new AttributeGetter(root.getAttribute());
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			
			System.out.println("/-------" + attgetter.getAttributeString());
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|       " + root.getOwnData().size() + " [" + republicans + "R, " + democrats + "D]");
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|");
		} else
			System.out.println(attgetter.getAttributeString() + "\n" + root.getOwnData().size() + " [" + republicans + "R, " + democrats + "D]");
		printTree(root.getLeft(), level + 1);
	}

	public int getNumberOfVotes(ArrayList<Instance> data, int classification) {
		int votes = 0;

		for (Instance i : data) {
			if (i.getClassification() == classification)
				votes++;
		}
		return votes;

	}

}