package code;


import java.util.ArrayList;
import java.util.HashMap;

public class Node {


	
	
	private ArrayList<Integer> values;
	private int attribute;
	private boolean isUsed;
	private ArrayList<Node> children;
	private ArrayList<HashMap<Integer, Instance>> data;
	
	public Node(){
		
		this.data = new ArrayList<HashMap<Integer, Instance>>();
		this.children = new ArrayList<Node>();
		this.values = new ArrayList<Integer>();
		this.attribute = 0;
		this.isUsed = false;
		
	}

	
	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(Node children) {
		this.children.add(children);
	}

	
	public ArrayList<HashMap<Integer, Instance>> getData() {
		return data;
	}

	public void setData(Integer key, Instance value) {
		HashMap<Integer, Instance> data1 = new HashMap<Integer, Instance>();
		data1.put(key, value);
		this.data.add(data1);
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	
	public ArrayList<Integer> getValues() {
		return values;
	}

	public void setValues(ArrayList<Integer> values) {
		this.values = values;
	}

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
}
