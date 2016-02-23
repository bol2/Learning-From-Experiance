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
 * @version 1.5 23/02/16
 */

public class Node {

	private ArrayList<Integer> branchValues;
	private int attribute;
	private ArrayList<Node> children;
	private ArrayList<HashMap<Integer, Instance>> childData;
	private ArrayList<Instance> ownData;
	
	public Node(){	
		this.childData = new ArrayList<HashMap<Integer, Instance>>();
		this.children = new ArrayList<Node>();
		this.branchValues = new ArrayList<Integer>();
		this.ownData = new ArrayList<Instance>();
		this.attribute = 4;	
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(Node children) {
		this.children.add(children);
	}

	public ArrayList<HashMap<Integer, Instance>> getData() {
		return childData;
	}

	public void setData(Integer key, Instance value) {
		HashMap<Integer, Instance> data1 = new HashMap<Integer, Instance>();
		data1.put(key, value);
		this.childData.add(data1);
	}
	
	public ArrayList<Integer> getValues() {
		return branchValues;
	}

	public void setValues(ArrayList<Integer> values) {
		this.branchValues = values;
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
}
