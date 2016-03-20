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
 * @version 1.6 14/03/16
 */

public class Node {

	private ArrayList<Integer> branchValues;
	private int attribute;
	private ArrayList<Node> children;
	private ArrayList<Instance> ownData;
	
	public Node(){
		branchValues = new ArrayList<Integer>();
		this.children = new ArrayList<Node>();
		this.ownData = new ArrayList<Instance>();
		this.attribute = 16;	
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	/**
	 * 
	 * @param children a node to be added to the child node
	 */
	public void setChildren(Node children, int value) {
		this.children.add(children);
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
	 * @param attribute is integer representing attribute value
	 */
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	/**
	 * returns an array list of data held by this node
	 * @return the array list of data held by this node 
	 */
	public ArrayList<Instance> getOwnData() {
		return ownData;
	}

	/**
	 * Set the data of this node to the array list that is passed in
	 * @param remaining the array of information passed in.
	 */
	public void setOwnData(ArrayList<Instance> remaining) {
		this.ownData = remaining;
	}
	
	/**
	 * Add to the nodes own data
	 * @param i the instance being added to the nodes data
	 */
	public void setClassifiedData(Instance i) {
		ownData.add(i);
	}
	
	/**
	 * removes a instance from the nodes own data
	 * @param i the instance being removed
	 */
	public void removeClassifyData(Instance i) {
		ownData.remove(i);
	}
}
