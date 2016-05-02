package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * CS39440 Major Project: Learning From Experience TreeBuilder.java Purpose:
 * Builds the tree using the data read in from from the file reader.Utalises the
 * entropy calculator.
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class TreeBuilder {

	private EntropyCalculator entropyCalculator;
	private ArrayList<Instance> remainingInstances;
	private ArrayList<Attribute> attributesRemaining;
	private Node rootNode;

	public TreeBuilder(ArrayList<Instance> trainingData) {

		remainingInstances = trainingData;

		attributesRemaining = new ArrayList<Attribute>();
		for (Attribute attribute : Attribute.values()) {
			attributesRemaining.add(attribute);
		}

		entropyCalculator = new EntropyCalculator();

		MasterID3(remainingInstances, attributesRemaining);

		printTree("", true, rootNode, 0);
	}

	/**
	 * Method creates a new node that will become the root node. This method
	 * also calls another method to check if all the instances in the training
	 * data are the same, if they are then the method returns without performing
	 * ID3. Else ID3 is started.
	 * 
	 * @param remainingInstances
	 *            Array list containing all of the training data.
	 * @param attributesRemaining
	 *            Array list containing all of the attributes possible for an
	 *            instance.
	 */
	public void MasterID3(ArrayList<Instance> remainingInstances, ArrayList<Attribute> attributesRemaining) {
		rootNode = new Node();
		if (allSameClassification(remainingInstances) == true)
			rootNode.setOwnData(remainingInstances);
		else
			ID3(remainingInstances, attributesRemaining, rootNode);
	}

	/**
	 * Method performs ID3 on the remaining instances. Utilizes the entropy
	 * calculator class to measure information gain and entropy, which are used
	 * when constructing the tree.
	 * 
	 * @param remainingInstance
	 *            Instances that shall have the ID3 algorithm performed on.
	 * @param attributesRemaining
	 *            The remaining attributes that may be used to split the
	 *            decision tree.
	 * @param root
	 *            The current node in the decision tree that ID3 is being
	 *            performed on.
	 */
	public void ID3(ArrayList<Instance> remainingInstance, ArrayList<Attribute> attributesRemaining, Node root) {

		// calculate the entropy and the attribute giving the highest information gain
		entropyCalculator.setEntropy(0);
		entropyCalculator.calculateEntropy(remainingInstance);
		int attribute = entropyCalculator.calculateHighestGain(remainingInstance, attributesRemaining);

		entropyCalculator.checkAndAssignValue(remainingInstance, attributesRemaining.get(attribute));

		// set root as highest information gain attribute and get the values for the node. Create
		// branches for the values
		root.setOwnData(remainingInstance);
		AttributeGetter attributeGetter = new AttributeGetter(attributesRemaining.get(attribute));
		root.setAttribute(attributeGetter.getAttribute());
		assignLabel(root, remainingInstance);

		for (Instance instance : remainingInstance) {
			int value = instance.getAttributeValue(attributeGetter.getAttribute());
			if (!root.getValues().contains(value))
				root.setValues(value);
		}
		
		if (root.getValues().size() == 2) {
			int max = Collections.max(root.getValues());
			int min = Collections.min(root.getValues());
			if (root.getValues().get(0) != min) {
				root.getValues().set(0, min);
				root.getValues().set(1, max);
			}
		}

		for (int branchValue = 0; branchValue < root.getValues().size(); branchValue++) {
			// Create a subset of values for each branch
			ArrayList<Instance> branchInstances = new ArrayList<Instance>();
			for (int position = 0; position < remainingInstance.size(); position++) {
				if (remainingInstance.get(position).getAttributeValue(attributeGetter.getAttribute()) == root
						.getValues().get(branchValue)) {
					branchInstances.add(remainingInstance.get(position));
				}
			}
			// If all values in subset are the same create a leaf Node
			if (branchInstances.size() > 0 && allSameClassification(branchInstances) == true) {
				Node child = new Node();
				child.setOwnData(branchInstances);
				child.setAttribute(16);
				child.setParent(root);
				assignLabel(child, branchInstances);
				root.setChildren(child);
			}
			// Else add a subtree 
			else if (branchInstances.size() > 0 && allSameClassification(branchInstances) == false) {

				// Remove an attribute, use a different attribute array list to pass down the tree 
				ArrayList<Attribute> tempAttributesRemaining = new ArrayList<Attribute>(attributesRemaining);
				tempAttributesRemaining.remove(attribute);

				Node child = new Node();

				child.setOwnData(branchInstances);
				child.setParent(root);

				if (tempAttributesRemaining.size() != 0) {
					root.setChildren(child);
					ID3(branchInstances, tempAttributesRemaining, child);
				} else {
					child.setAttribute(16);
					assignLabel(child, branchInstances);
					root.setChildren(child);
				}
			}
		}
	}

	/**
	 * Method will check if all the instances of a given array list have the
	 * same classification. If all instances are of the same classification then
	 * true will be returned. Else, false will be returned.
	 * 
	 * @param remainingInstances
	 *            Array list to hold of the instances that will be checked for
	 *            the same classification.
	 * @return Boolean value to be returned. True if all the instances are the
	 *         same classification, false if not all of the instances are of the
	 *         same classification.
	 */
	public boolean allSameClassification(ArrayList<Instance> remainingInstances) {
		// Test if all examples are the same, if so return single node tree
		boolean allSame = true;
		Instance testInstance = remainingInstances.get(0);

		for (int position = 0; position < remainingInstances.size(); position++) {
			if (remainingInstances.get(position).getClassification() != testInstance.getClassification())
				allSame = false;
		}
		return allSame;
	}

	/**
	 * Returns the root node.
	 * 
	 * @return The root node.
	 */
	public Node getRoot() {
		return rootNode;
	}

	/**
	 * Method starts at the root node (passed in) and begins to print out the
	 * decision tree that has been built. Leaf nodes are highlighted.
	 * 
	 * @param tree
	 *            The text to be output in the line, the prefix.
	 * @param isLast
	 *            A boolean to say if the node is the last one on a particular
	 *            level.
	 * @param root
	 *            The node currently being printed.
	 * @param level
	 *            An integer representation of how far down the tree the
	 *            printing is.
	 */
	public void printTree(String tree, boolean isLast, Node root, int level) {
		AttributeGetter attributeGetter = new AttributeGetter(root.getAttribute());
		int republicans = getNumberOfVotes(root.getOwnData(), 1);
		int democrats = getNumberOfVotes(root.getOwnData(), 2);
		String vote = "──";
		// Set the branch to a yes or no value
		if (root != rootNode) {
			AttributeGetter parentAttributeGetter = new AttributeGetter(root.getParent().getAttribute());
			int theBranch = root.getOwnData().get(0).getAttributeValue(parentAttributeGetter.getAttribute());
			vote = (theBranch == 1 ? "Yes" : "No");
		}

		// Print the amount of republicans and democrats for each node, leaf
		// nodes are highlighted.
		if (root.getAttribute() == 16) {
			System.out.println(tree + (isLast
					? "└──" + vote + "─" + (char) 27 + "[37;40m" + attributeGetter.getAttributeString() + "\u001B[0m \n"
							+ tree + "      " + root.getOwnData().size() + " [" + republicans + "R, " + democrats + "D]"
					: "├──" + vote + "─" + (char) 27 + "[37;40m" + attributeGetter.getAttributeString() + "\u001B[0m"
							+ "\n" + tree + "|      " + root.getOwnData().size() + " [" + republicans + "R, "
							+ democrats + "D]"));
		} else {
			System.out.println(tree + (isLast
					? "└──" + vote + "─" + attributeGetter.getAttributeString() + "\n" + tree + "      "
							+ root.getOwnData().size() + " [" + republicans + "R, " + democrats + "D]"
					: "├──" + vote + "─" + attributeGetter.getAttributeString() + "\n" + tree + "|      "
							+ root.getOwnData().size() + " [" + republicans + "R, " + democrats + "D]"));
		}

		for (int position = 0; position < root.getChildren().size() - 1; position++) {
			level++;
			printTree(tree + (isLast ? "        " : "│       "), false, root.getChildren().get(position), level);
		}

		if (root.getChildren().size() > 0) {
			level++;
			printTree(tree + (isLast ? "        " : "│       "), true,
					root.getChildren().get(root.getChildren().size() - 1), level);
		}
	}

	/**
	 * Returns the number of votes in some test data for a given classification.
	 * 
	 * @param nodeData
	 *            The data that will have its classifications counted.
	 * @param classification
	 *            The value of classification to be looked at. 1 for republican,
	 *            2 for democrat.
	 * @return An integer value is returned, representing the number of votes in
	 *         a given classification for a given set of instances.
	 */
	public int getNumberOfVotes(ArrayList<Instance> nodeData, int classification) {
		int votes = 0;

		for (Instance instance : nodeData) {
			if (instance.getClassification() == classification)
				votes++;
		}
		return votes;
	}

	/**
	 * Method will assign a label to a given node which allows the software to
	 * see if the node is a republican or democrat majority node. Label is
	 * randomly assigned if the amount of republicans and democrats is the same.
	 * 
	 * @param node
	 *            The node that will have its label assigned.
	 * @param nodeData
	 *            The data belonging to the node.
	 */
	public void assignLabel(Node node, ArrayList<Instance> nodeData) {
		int numberOfRepublicans = getNumberOfVotes(nodeData, 1);
		int numberOfDemocrats = getNumberOfVotes(nodeData, 2);

		if (numberOfRepublicans == numberOfDemocrats) {
			Random random = new Random();
			double randomValue = random.nextDouble();
			if (randomValue < 0.5) {
				node.setLabel("Republican");
			} else {
				node.setLabel("Democrat");
			}
		} else if (numberOfRepublicans > numberOfDemocrats) {
			node.setLabel("Republican");
		} else if (numberOfDemocrats > numberOfRepublicans) {
			node.setLabel("Democrat");
		}
	}
}