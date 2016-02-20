package code;

import java.util.ArrayList;

public class TreeBuilder {
	
	private EntropyCalc ec; 
	private ArrayList<Instance> remaining = null;
	private ArrayList<Attribute> attributesRemaining = null;
	private FileReader fr;
	private boolean hasRootBeenSet = false;
	
	public TreeBuilder(){
		fr = new FileReader();
		remaining = fr.getInput();
		
		attributesRemaining = new ArrayList<Attribute>();
		attributesRemaining.add(Attribute.AGE);
		attributesRemaining.add(Attribute.PERSCRIPTION);
		attributesRemaining.add(Attribute.ASTIGMATIC);
		attributesRemaining.add(Attribute.TEARPRODRATE);
		
		ec = new EntropyCalc();
		
		ID3(remaining, attributesRemaining);
		
	}
	
	public void MasterID3(){
		
	}
	
	public void ID3(ArrayList<Instance> remaining,ArrayList<Attribute> attributesRemaining){
		// Create a root node
		Node root = new Node();
		
		
		// Test if all examples are the same, if so return single node tree
		boolean allSame = true; 
		
		Instance testInstance = remaining.get(0);
		int k = 0;
		while (k < remaining.size()){
			if (remaining.get(k).getClassification() != testInstance.getClassification()) allSame = false;
			k++;
		}
		
		//Create the one leaf node tree.
		if (allSame == true){
			System.out.println("All Classifications are the same. The tree is a single node tree with the following label " + testInstance.getClassification());
			for (int i = 0; i < remaining.size(); i ++){
				root.setData(remaining.get(i).getClassification(), remaining.get(i));
			}
			return;
		}
		
		
		//
		while (!attributesRemaining.isEmpty()){
			if(root.isUsed()) break;
			// calculate the entropy and the highest gain 
			ec.calcEntropy(remaining);
			int attribute = ec.calculateHighestGain(remaining, attributesRemaining);
			System.out.println("Attribute with highest Gain = " + getAttribute(attribute));
			//set root as highest gain 
			
			root.setAttribute(attribute);
			root.setUsed(true);
			
			AttributeGetter ag = new AttributeGetter(getAttribute(attribute));
			//Add tree branch for each values 
			root.setValues(ag.getAttributeValues());
			
			ArrayList<Integer> theAttributes = root.getValues();
			int amountOfAttributes = root.getValues().size();
			
			for (int i = 0; i < amountOfAttributes; i++){
				ArrayList<Instance> temp = new ArrayList<Instance>();
				for (int y = 0; y < remaining.size(); y ++){
					if(remaining.get(y).getAttribute(attribute) == theAttributes.get(i)){
						System.out.println("Adding to temp on condition" + remaining.get(y).getAttribute(attribute) + " " + theAttributes.get(i));
						temp.add(remaining.get(y));
					}
				}
					
					if(allSameClassification(temp) == true){
						// if all the same pull out 
						System.out.println("Temp was empty so leaf node created containing the following data : ");
						for (int x = 0; x < temp.size(); x ++){
							root.setData(i, temp.get(x));
							//System.out.println(temp.get(x));
							//Instance tempInstance = root.getData().get(x).get(i);
							//System.out.println("ID: " + root.getData().get(x).get(i) + ", Classification: " + root.getData().get(x).get(i).getClassification());
						}
						
						if (attributesRemaining.isEmpty()) printTree();
					}else{
						ArrayList<Attribute> tempAttributesRemaining = new ArrayList<Attribute>(attributesRemaining);
						System.out.println(tempAttributesRemaining);
						System.out.println("Tried to remove " + getAttribute(attribute));
						
						
						tempAttributesRemaining.remove(attribute);
						System.out.println(tempAttributesRemaining);
						if(!hasRootBeenSet){
							
						hasRootBeenSet = true;
						
						}
						ID3(temp, tempAttributesRemaining);
					}
				
			}
			//if (attributesRemaining.size() == 0 ){
			//	System.out.println("This is remaining: "+ attributesRemaining);
			//	break;
			//}	
			
		}
		return;
	}
	
	public boolean allSameClassification(ArrayList<Instance> remaining){
		
		// Test if all examples are the same, if so return single node tree
				boolean allSame = true; 
				
				Instance testInstance = remaining.get(0);
				
				for (int i = 0; i < remaining.size(); i++){
					if (remaining.get(i).getClassification() != testInstance.getClassification()) allSame = false;
				}
				
		return allSame;
	}
	
	
	
	public Attribute getAttribute(int i){
		Attribute a = null;
		
		if(i == 0) a = a.AGE;
		else if(i == 1) a = a.PERSCRIPTION;
		else if(i == 2) a = a.ASTIGMATIC;
		else if(i == 3) a = a.TEARPRODRATE;
		
		return a;
	}
	
	//Method to Print the Tree
	public void printTree(){
		System.out.println("This is the end!");
	}
	
}