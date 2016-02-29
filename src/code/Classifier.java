package code;

import java.util.ArrayList;

/**
 * CS39440 Major Project: Learning From Experience
 * Main.java
 * Purpose: Initiates the start of the program by creating a tree builder class
 * 
 * @author Ben Larking
 * @version 1.5 29/02/16
 */

public class Classifier {

	private FileReader fr;
	private TreeBuilder tb;
	
	public static void main(String[] args){
		Classifier c = new Classifier();
	}
	
	
	
	public Classifier(){
		tb = new TreeBuilder();
		fr = new FileReader();
		classify(fr.getTestData());
	}
	
	private void classify(ArrayList<Instance> testData){
		
		for (Instance i : testData){
			System.out.println(i.getId());
		}
		
		for (Instance i : testData){
			Node n = tb.getRoot();
			sortNode(n, i);
			tb.printTree2(n, 0);
		}
	}
	
	private void sortNode(Node node, Instance i){
		int attribute = node.getAttribute();
		int instanceAttributeValue = i.getAttributeValue(attribute);
		ArrayList<Integer> values = node.getValues();
		for (Integer value : values){
			if (value == instanceAttributeValue){
				Node child = node.getChildren().get(value-1);
				child.setClassifiedData(i);
				
				if (child.getAttribute() == 4){
					return;
				}else {
				 //child.removeClassifyData(i);
				 sortNode(child, i);
				}
			}
		}
	}

}
