package code;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {

	private Node parent = null;
	private ArrayList<Instance> leaf; 
	private Node[] children = null;
	private double gain = 0.0;
	private ArrayList<HashMap<Integer, Instance>> data;
	
	private String name = null;
	private int nameValue = 0;
	
	
	private ArrayList<Integer> values = null;
	private int attribute;
	private boolean isUsed = false;
	
	public Node(){
		leaf = new ArrayList<Instance>();
		this.data = new ArrayList<HashMap<Integer, Instance>>();
		this.setValues(new ArrayList<Integer>());
		
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getValues() {
		return values;
	}

	public void setValues(ArrayList<Integer> values) {
		this.values = values;
	}

	public int getNameValue() {
		return nameValue;
	}

	public void setNameValue(int nameValue) {
		this.nameValue = nameValue;
	}
	
	public void addToLeaf(Instance i){
		leaf.add(i);
	}

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
}
