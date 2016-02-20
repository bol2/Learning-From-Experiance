package code;

import java.util.ArrayList;

public class TreeBuilder {

	private EntropyCalc ec;
	private ArrayList<Instance> remaining = null;
	private ArrayList<Attribute> attributesRemaining = null;
	private FileReader fr;
	private Node first;
	private boolean firstIsSet = false;

	public TreeBuilder() {
		fr = new FileReader();
		remaining = fr.getInput();

		attributesRemaining = new ArrayList<Attribute>();
		attributesRemaining.add(Attribute.AGE);
		attributesRemaining.add(Attribute.PERSCRIPTION);
		attributesRemaining.add(Attribute.ASTIGMATIC);
		attributesRemaining.add(Attribute.TEARPRODRATE);
		first = new Node(4);
		

		ec = new EntropyCalc();

		MasterID3(remaining, attributesRemaining);
		printTree();

	}

	public void MasterID3(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining) {
		Node root = new Node(4);
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

	}

	public void ID3(ArrayList<Instance> remaining, ArrayList<Attribute> attributesRemaining, Node perant) {
		

		Node root = new Node(4);
		
		

		while (attributesRemaining.size() > 0) {

			if (root.isUsed())
				break;
			// calculate the entropy and the highest gain
			ec.calcEntropy(remaining);
			int attribute = ec.calculateHighestGain(remaining, attributesRemaining);
			System.out.println("Attribute with highest Gain = " + getAttribute(attribute));

			// set root as highest gain and get the values for the node. Create
			// branches for the

			root.setAttribute(attribute);
			root.setUsed(true);
			AttributeGetter ag = new AttributeGetter(getAttribute(attribute));
			root.setValues(ag.getAttributeValues());
			System.out.println("This is the values for root" + root.getValues());
			
			
			
			// Create branches for values
			for (int i = 0; i < root.getValues().size(); i++) {
				Node child = new Node(attribute);
				
				// Create a subset of values for each branch
				ArrayList<Instance> temp = new ArrayList<Instance>();
				for (int y = 0; y < remaining.size(); y++) {
					if (remaining.get(y).getAttribute(attribute) == root.getValues().get(i)) {
						System.out.println("Adding to temp on condition" + remaining.get(y).getAttribute(attribute)
								+ " " + root.getValues().get(i));
						temp.add(remaining.get(y));
					}
				}

				// If all values in subset are the same create a child Node
				if (allSameClassification(temp) == true) {
					// if all the same pull out
					System.out.println("All the above temp data had the same classification.");
					for (int x = 0; x < temp.size(); x++) {
						child.setData(i, temp.get(x));
					}
					System.out.println("Adding the above to a child and setting it to root's child.");
					root.setChildren(child);
					
					if (firstIsSet == false) {
						firstIsSet = true;
						System.out.println("Setting the first root inside same classification");
						perant.setChildren(root);
						first = root;
						System.out.println("Set for the original root" + root.getChildren().get(0).getData());
					} else if(firstIsSet == true){
						System.out.println("This is the passed in one "+ perant.getAttribute());
						perant.setChildren(root);
					}
				}

				// Add a subtree
				else {

					// Remove an attribute
					ArrayList<Attribute> tempAttributesRemaining = new ArrayList<Attribute>(attributesRemaining);
					tempAttributesRemaining.remove(attribute);
					
					for (int x = 0; x < temp.size(); x++) {
						child.setData(i, temp.get(x));
					}
					root.setChildren(child);
					
					if(firstIsSet == true){
						System.out.println("This is the passed in one "+ perant.getAttribute());
						perant.setChildren(root);
						for (int o = 0; o < root.getChildren().size(); o++){
							System.out.print("Set for the new root" + root.getChildren().get(o).getData());
						}
						System.out.println();
					}
					
					System.out.println("Created a child and assigned above to root.");

					if (firstIsSet == false) {
						firstIsSet = true;
						System.out.println("Setting the first root");
						first = root;
						System.out.println("Set for the original root" + root.getChildren().get(0).getData());
						System.out.println("Set for the original root" + root.getChildren().get(1).getData());
					} 
					
					ID3(temp, tempAttributesRemaining, child);

				}

			}

		}

		return;
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
			a = a.AGE;
		else if (i == 1)
			a = a.PERSCRIPTION;
		else if (i == 2)
			a = a.ASTIGMATIC;
		else if (i == 3)
			a = a.TEARPRODRATE;

		return a;
	}

	// Method to Print the Tree
	public void printTree() {
		System.out.println("This is the end!");
		System.out.println("this is the attribute of first " + first.getAttribute());
		System.out.println("this is the value of first children amount " + first.getChildren().size());
		System.out.println("These are the firsts values " + first.getValues());
		System.out.println("first child " + first.getChildren().get(0).getData());
		System.out.println("second child " + first.getChildren().get(1).getData());
		System.out.println();
		
		
		Node second = first.getChildren().get(1).getChildren().get(1);
		System.out.println("this is the value of second children amount " + second.getChildren().size());
		System.out.println("Seconds child attribute " + second.getAttribute());
		System.out.println("Seconds child data " + second.getChildren().get(0).getData());
		System.out.println("Seconds child data " + second.getChildren().get(1).getData());
		System.out.println();
		
		Node third = first.getChildren().get(1).getChildren().get(0);
		System.out.println("BIGGY TEST"+ first.getChildren().get(1).getChildren().get(0).getData());
		System.out.println("Seconds child attribute " + third.getAttribute());
		System.out.println("Seconds child data " + third.getChildren().get(0).getData());
		System.out.println("Seconds child data " + third.getChildren().get(1).getData());
		System.out.println();
		
		Node four = first.getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0);
		System.out.println("this is the value of 4th children amount " + four.getChildren().size());
		
		System.out.println("Seconds child attribute " + four.getChildren().get(0).getAttribute());
		System.out.println("Seconds child data " + four.getChildren().get(0).getData());
		System.out.println("Seconds child data " + four.getChildren().get(1).getData());
		System.out.println("Seconds child data " + four.getChildren().get(2).getData());
		
		System.out.println("BIGGY TEST"+ first.getChildren().get(1).getChildren().get(1).getData());
		
		
		
	}

}