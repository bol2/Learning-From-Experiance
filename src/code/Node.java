package code;


import java.util.ArrayList;
import java.util.HashMap;

public class Node {


	
	
	private ArrayList<Integer> values = null;
	private int attribute;
	private boolean isUsed = false;
	private Node[] children = null;
	private ArrayList<HashMap<Integer, Instance>> data;
	
	public Node(){
		this.data = new ArrayList<HashMap<Integer, Instance>>();
		this.setValues(new ArrayList<Integer>());
		
	}

	
	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
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
