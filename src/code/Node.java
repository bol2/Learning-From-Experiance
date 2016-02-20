package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {

	private Node parent = null;
	private Node[] children = null;
	private double gain = 0.0;
	private ArrayList<HashMap<String, Instance>> data;
	private boolean isUsed = false;
	private String name = null;
	private int nameValue = 0;
	private ArrayList<String> values = null;
	
	public Node(){
		this.data = new ArrayList<HashMap<String, Instance>>();
		this.setValues(new ArrayList<String>());
		
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

	public ArrayList<HashMap<String, Instance>> getData() {
		return data;
	}

	public void setData(String key, Instance value) {
		HashMap<String, Instance> data1 = new HashMap<String, Instance>();
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

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}

	public int getNameValue() {
		return nameValue;
	}

	public void setNameValue(int nameValue) {
		this.nameValue = nameValue;
	}
}
