package code;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CS39440 Major Project: Learning From Experience Node.java Purpose: Represents
 * a node in the decision tree. Each node has an attribute, an array list of
 * children, possible values and its own data.
 * 
 * @author Ben Larking
 * @version 1.6 14/03/16
 */

public class Node implements Cloneable {

	private ArrayList<Integer> branchValues;
	private int attribute;
	private ArrayList<Node> children;

	private ArrayList<Instance> ownData;
	private String label;

	public Node() {
		this.branchValues = new ArrayList<Integer>();
		this.children = new ArrayList<Node>();
		this.ownData = new ArrayList<Instance>();
		this.attribute = 16;
	}

	public Node cloneNode(Node n, Node treeToPrune) {
		treeToPrune.setAttribute(n.getAttribute());
		treeToPrune.setLabel(n.getLabel());
		for (Integer i : n.getValues()) {
			treeToPrune.setValues(i);
		}
		ArrayList<Instance> assign = new ArrayList<Instance>();
		for (Instance i : n.getOwnData()) {
			assign.add(i);
		}
		treeToPrune.setOwnData(assign);
		for (Node copyNode : n.getChildren()) {
			Node newChild = new Node();
			treeToPrune.setChildren(newChild);
			cloneNode(copyNode, newChild);
		}
		return treeToPrune;
	}

	public Node(ArrayList<Integer> values, int attribute2, ArrayList<Node> children2, ArrayList<Instance> ownData2,
			String label2) {
		branchValues = values;
		attribute = attribute2;
		children = children2;
		ownData = ownData2;
		label = label2;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	/**
	 * 
	 * @param children
	 *            a node to be added to the child node
	 */
	public void setChildren(Node children) {
		this.children.add(children);
	}
	
	public void removeChildren(Node children) {
		this.children.remove(children);
	}

	/**
	 * 
	 * @return the possible values for this node
	 */
	public ArrayList<Integer> getValues() {
		return branchValues;
	}

	/**
	 * 
	 * @return the possible values for this node
	 */
	public void setValues(Integer i) {
		branchValues.add(i);
	}

	/**
	 * 
	 * @return integer representing the attribute of this node
	 */
	public int getAttribute() {
		return attribute;
	}

	/**
	 * given an integer this method sets the attribute for this node
	 * 
	 * @param attribute
	 *            is integer representing attribute value
	 */
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	/**
	 * returns an array list of data held by this node
	 * 
	 * @return the array list of data held by this node
	 */
	public ArrayList<Instance> getOwnData() {
		return ownData;
	}

	/**
	 * Set the data of this node to the array list that is passed in
	 * 
	 * @param remaining
	 *            the array of information passed in.
	 */
	public void setOwnData(ArrayList<Instance> remaining) {
		this.ownData = remaining;
	}

	/**
	 * Add to the nodes own data
	 * 
	 * @param i
	 *            the instance being added to the nodes data
	 */
	public void setClassifiedData(Instance i) {
		ownData.add(i);
	}

	/**
	 * removes a instance from the nodes own data
	 * 
	 * @param i
	 *            the instance being removed
	 */
	public void removeClassifyData(Instance i) {
		ownData.remove(i);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
