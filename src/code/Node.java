package code;

import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience Node.java Purpose: Represents
 * a node in the decision tree. Each node has an attribute, an array list of
 * children, possible values and its own data.
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */

public class Node{

	private ArrayList<Integer> branchValues;
	private ArrayList<Node> children;
	private ArrayList<Instance> ownData;
	private int attribute;
	private String label;
	private Node parent;

	public Node() {
		this.branchValues = new ArrayList<Integer>();
		this.children = new ArrayList<Node>();
		this.ownData = new ArrayList<Instance>();
		this.attribute = 16;
		this.setParent(null);
	}

	/**
	 * Creates a copy of a node.
	 * @param node Node that will be copied.
	 * @param newNode Node that will have new values assigned to it.
	 * @return A node that has been copied. 
	 */
	public Node cloneNode(Node node, Node newNode) {
		newNode.setAttribute(node.getAttribute());
		newNode.setLabel(node.getLabel());
		for (Integer branchID : node.getValues()) {
			newNode.setValues(branchID);
		}
		ArrayList<Instance> assign = new ArrayList<Instance>();
		for (Instance instance : node.getOwnData()) {
			assign.add(instance);
		}
		newNode.setOwnData(assign);
		for (Node copyNode : node.getChildren()) {
			copyNode.setParent(newNode);
			newNode.setChildren(copyNode);
		}
		
		newNode.setParent(node.getParent());
		return newNode;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(Node children) {
		this.children.add(children);
	}
	
	public void removeChildren(Node children) {
		this.children.remove(children);
	}

	public ArrayList<Integer> getValues() {
		return branchValues;
	}

	public void setValues(int value) {
		branchValues.add(value);
	}

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	public ArrayList<Instance> getOwnData() {
		return ownData;
	}

	public void setOwnData(ArrayList<Instance> remaining) {
		this.ownData = remaining;
	}

	public void setClassifiedData(Instance instance) {
		ownData.add(instance);
	}

	public void removeClassifyData(Instance instance) {
		ownData.remove(instance);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
