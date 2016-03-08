package code;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * CS39440 Major Project: Learning From Experience
 * Node.java
 * Purpose: Represents a node in the decision tree. Each node has 
 * an attribute, an array list of children, possible values and its own data. 
 * 
 * @author Ben Larking
 * @version 1.5 29/02/16
 */

public class Node {

	private int[] branchValues = {1, 2};
	private int attribute;
	private ArrayList<Node> children;
	private ArrayList<Instance> ownData;
	private Node right = null;;
	private Node left = null;
	
	public Node(){	
		this.children = new ArrayList<Node>();
		this.ownData = new ArrayList<Instance>();
		this.attribute = 16;	
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(Node children) {
		if (this.getRight() == null) right = children;
		else if (this.getLeft() == null) left = children;
		this.children.add(children);
	}
	
	public int[] getValues() {
		return branchValues;
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
		//this.ownData.add(remaining);
		this.ownData = remaining;
	}
	
	public void setClassifiedData(Instance i) {
		ownData.add(i);
	}
	
	public void removeClassifyData(Instance i) {
		ownData.remove(i);
	}

	public Node getRight() {
		return right;
	}

	public Node getLeft() {
		return left;
	}
}
