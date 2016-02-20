package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TreeBuilder {
	
	private EntropyCalc ec;
	private Node root;
	private int NumAttributes = 4; 
	private ArrayList<Instance> remaining = null;
	private ArrayList<Attribute> attributesRemaining = null;
	
	public TreeBuilder(){
		this.ec = new EntropyCalc();
		//this.remaining = ec.getInstances();
		
		root = getRoot();
		// Need to build method for if all instances are same label then return a 1 node tree
		System.out.println("This is the root node " + root.getName() + " These are the values "+ root.getValues());
		buildTree(root.getNameValue());
		printLeafs();
		
	}
	
	public void ID3(){
		// Create a root node
		Node r = new Node();
		
		// Test if all examples are the same, if so return single node tree
		boolean allSame = true; 
		
		Instance testInstance = remaining.get(0);
		
		for (int i = 0; i < remaining.size(); i++){
			if (remaining.get(i).getClassification() != testInstance.getClassification()) allSame = false;
		}
		
		if (allSame == true){
			System.out.println("All Classifications are the same. The tree is a single node tree with the following label " + testInstance.getClassification());
			return; 
		}
		
		
		while (NumAttributes != 0 ){
			//int attribute = calculateHighestGain(remaining);
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	public Node getRoot(){
		ec.calcGainAge();
		ec.calcGainAstigmatic();
		ec.calcGainPerscription();
		ec.calcGainTearProdRate();
		
		Node thisRoot = new Node();
		String attributeName = null;
		int attributeValue = 0;
		
		int attribute = ec.returnHighestGain();
		if (attribute == 1) attributeName = "Age";
		else if (attribute == 2) attributeName = "Specticle Perscription";
		else if (attribute == 3) attributeName = "Astigmatic";
		else if (attribute == 4){
			attributeName = "Tear Production Rate";
			attributeValue = 4;
			thisRoot.getValues().add("Reduced");
			thisRoot.getValues().add("Normal");
			
			
		}
		
		thisRoot.setName(attributeName);
		thisRoot.setNameValue(attributeValue);
		
		NumAttributes = NumAttributes - 1;
		
		return thisRoot;
		
	}
	
	public void buildTree(int attributeValue){
			for (int i = 0; i < remaining.size(); i++){
				if (root.getNameValue() == attributeValue){
					Instance current = remaining.get(i);
					if (current.getTearProdRate() == 1) root.setData("Reduced", current);
					else if (current.getTearProdRate() == 2) root.setData("Normal", current);
				}	
			}
			
			
			
			while (NumAttributes != 0){
				
			}
	}
	
	
	public void printLeafs(){
		System.out.println("Should be printing leafs");
		System.out.println(root.getData().size());
		for (int i =0; i < root.getData().size(); i ++){
			Iterator<HashMap.Entry<String, Instance>> entries = root.getData().get(i).entrySet().iterator();
			while (entries.hasNext()) {
				  Map.Entry<String, Instance> entry = entries.next();
				  String key = entry.getKey();
				  int value = entry.getValue().getTearProdRate();
				  if (key == "Reduced") System.out.println(entry + " entry number " + i + "This is the outcome of this leaf " + entry.getValue().getClassification());
				  
				}
		}
		
		for (int i =0; i < root.getData().size(); i ++){
			Iterator<HashMap.Entry<String, Instance>> entries = root.getData().get(i).entrySet().iterator();
			while (entries.hasNext()) {
				  Map.Entry<String, Instance> entry = entries.next();
				  String key = entry.getKey();
				  int value = entry.getValue().getTearProdRate();
				  if (key == "Normal") System.out.println(entry + " entry number " + i + "This is the outcome of this leaf " + entry.getValue().getClassification());
				  
				}
		}
		
		
	}

}
