package code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EntropyCalc {
	
	private String file;
	private ArrayList<Instance> instances;
	private double entropy;
	double gain;
	double gainPerscrip;
	double gainAstigmatic;
	double gainTearProdRate;
	private ArrayList<Instance> remaining;
	
	public EntropyCalc(){
		file = "src/LensData.txt";
		instances = new ArrayList<Instance>();
		readFile();
		calcEntropy();
		gain = calcGainAge();
		gainPerscrip = calcGainPerscription();
		gainAstigmatic = calcGainAstigmatic();
		gainTearProdRate = calcGainTearProdRate();
		
		System.out.println("This is Age Gain!" + gain);
		System.out.println("This is Perception Gain!" + gainPerscrip);
		System.out.println("This is Astigmatic Gain!" + gainAstigmatic);
		System.out.println("This is Tear Prod Rate Gain!" + gainTearProdRate);
		
		this.remaining = new ArrayList<Instance>();
	}
	
	public void readFile(){
		
		try {
			//Files.readAllLines(new File("t").toPath()).parallelStream();
			BufferedReader infile = new BufferedReader(new InputStreamReader 
					(new FileInputStream(file)));
			String values;
			while((values = infile.readLine()) != null){
				String[] broken_text = values.split(" ");
			    String first_key = broken_text[0];
			    String second_key = broken_text[2];
			    String third_key = broken_text[4];
			    String fourth_key = broken_text[6];
			    String fith_key = broken_text[8];
			    
			    Instance i = new Instance(Integer.parseInt(first_key),Integer.parseInt(second_key),Integer.parseInt(third_key),Integer.parseInt(fourth_key), Integer.parseInt(fith_key));
			    instances.add(i);
			}		
			infile.close();
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void calcEntropy(){
		
		int classification1 = 0;
		int classification2 = 0;
		int classification3 = 0;
		
		double dataSetSize = instances.size();
		
		for (int i = 0; i < instances.size(); i++) {
           int thisClassification = instances.get(i).getClassification();
           if (thisClassification == 1 ) classification1 ++;
           else if (thisClassification == 2 ) classification2 ++;
           else if (thisClassification == 3 ) classification3 ++;   
        }
				
		entropy += -classification1/dataSetSize * (Math.log(classification1/dataSetSize) / Math.log(2));
		entropy += -classification2/dataSetSize * (Math.log(classification2/dataSetSize) / Math.log(2));
		entropy += -classification3/dataSetSize * (Math.log(classification3/dataSetSize) / Math.log(2));
		System.out.println(entropy);
			
	}
	
	public double getEntropy(){
		return entropy;
	}
	
	public double calcGainAge(){
		double gain = 0;
		
		int age1andHardLense = 0;
		int age1andSoftLense = 0;
		int age1andNoLense = 0;
		double value1 = 0;
		
		double value2 = 0;
		int age2andHardLense = 0;
		int age2andSoftLense = 0;
		int age2andNoLense = 0;
		
		double value3 = 0;
		int age3andHardLense = 0;
		int age3andSoftLense = 0;
		int age3andNoLense = 0;
		
		for (int i = 0; i < instances.size(); i++) {
	           int thisAge= instances.get(i).getAge();
	           if (thisAge == 1 ){
	        	   value1 ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) age1andHardLense++;
	        	   if (classification == 2) age1andSoftLense++;
	        	   if (classification == 3) age1andNoLense++;
	           }
	           else if (thisAge == 2 ) {
	        	   value2 ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) age2andHardLense++;
	        	   if (classification == 2) age2andSoftLense++;
	        	   if (classification == 3) age2andNoLense++;
	           }
	           else if (thisAge == 3 ) {
	        	   value3 ++; 
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) age3andHardLense++;
	        	   if (classification == 2) age3andSoftLense++;
	        	   if (classification == 3) age3andNoLense++;
	           }
	    }
		
		
		double age1Gain = -age1andHardLense/value1 * (Math.log(age1andHardLense/value1) / Math.log(2));
			   age1Gain += -age1andSoftLense/value1 * (Math.log(age1andSoftLense/value1) / Math.log(2));
			   age1Gain += -age1andNoLense/value1 * (Math.log(age1andNoLense/value1) / Math.log(2));
		double age2Gain = -age2andHardLense/value2 * (Math.log(age2andHardLense/value2) / Math.log(2));
			   age2Gain += -age2andSoftLense/value2 * (Math.log(age2andSoftLense/value2) / Math.log(2));
			   age2Gain += -age2andNoLense/value2 * (Math.log(age2andNoLense/value2) / Math.log(2));
		double age3Gain = -age3andHardLense/value3 * (Math.log(age3andHardLense/value3) / Math.log(2));
			   age3Gain += -age3andSoftLense/value3 * (Math.log(age3andSoftLense/value3) / Math.log(2));
			   age3Gain += -age3andNoLense/value3 * (Math.log(age3andNoLense/value3) / Math.log(2));
		gain = entropy - (value1/instances.size() * age1Gain + value2/instances.size() * age2Gain + value3/instances.size() * age3Gain);
		
		return gain;
		
	}
	
	public double calcGainPerscription(){
		double gain = 0;
		
		int myopeandHardLense = 0;
		int myopeandSoftLense = 0;
		int myopeandNoLense = 0;
		double myopeAppearances = 0;
		
		int hyperandHardLense = 0;
		int hyperandSoftLense = 0;
		int hyperandNoLense = 0;
		double hyperAppearances = 0;
		
		for (int i = 0; i < instances.size(); i++) {
	           int thisPerscription = instances.get(i).getPerscription();
	           if (thisPerscription == 1 ){
	        	   myopeAppearances ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) myopeandHardLense++;
	        	   if (classification == 2) myopeandSoftLense++;
	        	   if (classification == 3) myopeandNoLense++;
	           }
	           else if (thisPerscription == 2 ) {
	        	   hyperAppearances ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) hyperandHardLense++;
	        	   if (classification == 2) hyperandSoftLense++;
	        	   if (classification == 3) hyperandNoLense++;
	           }
	    }
		
		double myopeGain = -myopeandHardLense/myopeAppearances * (Math.log(myopeandHardLense/myopeAppearances) / Math.log(2));
		myopeGain += -myopeandSoftLense/myopeAppearances * (Math.log(myopeandSoftLense/myopeAppearances) / Math.log(2));
		myopeGain += -myopeandNoLense/myopeAppearances * (Math.log(myopeandNoLense/myopeAppearances) / Math.log(2));
		double hyperGain = -hyperandHardLense/hyperAppearances * (Math.log(hyperandHardLense/hyperAppearances) / Math.log(2));
		hyperGain += -hyperandSoftLense/hyperAppearances * (Math.log(hyperandSoftLense/hyperAppearances) / Math.log(2));
		hyperGain += -hyperandNoLense/hyperAppearances * (Math.log(hyperandNoLense/hyperAppearances) / Math.log(2));
		
		gain = entropy - (myopeAppearances/instances.size() * myopeGain + hyperAppearances/instances.size() * hyperGain);
		
		
		return gain;
		
	}

	public double calcGainAstigmatic(){
        double gain = 0;
		
		int myopeandHardLense = 0;
		int myopeandSoftLense = 0;
		int myopeandNoLense = 0;
		double myopeAppearances = 0;
		
		int hyperandHardLense = 0;
		int hyperandSoftLense = 0;
		int hyperandNoLense = 0;
		double hyperAppearances = 0;
		
		for (int i = 0; i < instances.size(); i++) {
	           int thisPerscription = instances.get(i).getAstigmatic();
	           if (thisPerscription == 1 ){
	        	   myopeAppearances ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) myopeandHardLense++;
	        	   if (classification == 2) myopeandSoftLense++;
	        	   if (classification == 3) myopeandNoLense++;
	           }
	           else if (thisPerscription == 2 ) {
	        	   hyperAppearances ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) hyperandHardLense++;
	        	   if (classification == 2) hyperandSoftLense++;
	        	   if (classification == 3) hyperandNoLense++;
	           }
	    }
		double myopeGain = 0;
		
		if (myopeandHardLense != 0) myopeGain = -myopeandHardLense/myopeAppearances * (Math.log(myopeandHardLense/myopeAppearances) / Math.log(2));
		if (myopeandSoftLense != 0)myopeGain += -myopeandSoftLense/myopeAppearances * (Math.log(myopeandSoftLense/myopeAppearances) / Math.log(2));
		if (myopeandNoLense != 0) myopeGain += -myopeandNoLense/myopeAppearances * (Math.log(myopeandNoLense/myopeAppearances) / Math.log(2));
		   System.out.println(myopeGain);
		double hyperGain = 0;
		if (hyperandHardLense != 0)	hyperGain = -hyperandHardLense/hyperAppearances * (Math.log(hyperandHardLense/hyperAppearances) / Math.log(2));
		if (hyperandSoftLense != 0) hyperGain += -hyperandSoftLense/hyperAppearances * (Math.log(hyperandSoftLense/hyperAppearances) / Math.log(2));
		if (hyperandNoLense != 0) hyperGain += -hyperandNoLense/hyperAppearances * (Math.log(hyperandNoLense/hyperAppearances) / Math.log(2));
		   System.out.println(hyperGain);
		
		System.out.println("Persciption stats");
		System.out.println(myopeandHardLense);
		System.out.println(myopeandSoftLense);
		System.out.println(myopeandNoLense);
		System.out.println(myopeAppearances);
		System.out.println();
		System.out.println(hyperandHardLense);
		System.out.println(hyperandSoftLense);
		System.out.println(hyperandNoLense);
		System.out.println(hyperAppearances);
		
		gain = entropy - (myopeAppearances/instances.size() * myopeGain + hyperAppearances/instances.size() * hyperGain);
		
		
		return gain;
	
	}

	public double calcGainTearProdRate(){
        double gain = 0;
		
		int myopeandHardLense = 0;
		int myopeandSoftLense = 0;
		int myopeandNoLense = 0;
		double myopeAppearances = 0;
		
		int hyperandHardLense = 0;
		int hyperandSoftLense = 0;
		int hyperandNoLense = 0;
		double hyperAppearances = 0;
		
		for (int i = 0; i < instances.size(); i++) {
	           int thisPerscription = instances.get(i).getTearProdRate();
	           if (thisPerscription == 1 ){
	        	   myopeAppearances ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) myopeandHardLense++;
	        	   if (classification == 2) myopeandSoftLense++;
	        	   if (classification == 3) myopeandNoLense++;
	           }
	           else if (thisPerscription == 2 ) {
	        	   hyperAppearances ++;
	        	   int classification = instances.get(i).getClassification();
	        	   if (classification == 1) hyperandHardLense++;
	        	   if (classification == 2) hyperandSoftLense++;
	        	   if (classification == 3) hyperandNoLense++;
	           }
	    }
		
		System.out.println("Tear stats");
		double myopeGain = 0;
		
		if (myopeandHardLense != 0) myopeGain = -myopeandHardLense/myopeAppearances * (Math.log(myopeandHardLense/myopeAppearances) / Math.log(2));
		if (myopeGain != 0) myopeGain += -myopeandSoftLense/myopeAppearances * (Math.log(myopeandSoftLense/myopeAppearances) / Math.log(2));
		if (myopeandNoLense != 0) myopeGain += -myopeandNoLense/myopeAppearances * (Math.log(myopeandNoLense/myopeAppearances) / Math.log(2));
		   System.out.println(myopeGain);
		double hyperGain = -hyperandHardLense/hyperAppearances * (Math.log(hyperandHardLense/hyperAppearances) / Math.log(2));
		hyperGain += -hyperandSoftLense/hyperAppearances * (Math.log(hyperandSoftLense/hyperAppearances) / Math.log(2));
		hyperGain += -hyperandNoLense/hyperAppearances * (Math.log(hyperandNoLense/hyperAppearances) / Math.log(2));
		   System.out.println(hyperGain);
		
		
		System.out.println(myopeandHardLense);
		System.out.println(myopeandSoftLense);
		System.out.println(myopeandNoLense);
		System.out.println(myopeAppearances);
		System.out.println();
		System.out.println(hyperandHardLense);
		System.out.println(hyperandSoftLense);
		System.out.println(hyperandNoLense);
		System.out.println(hyperAppearances);
		
		gain = entropy - (myopeAppearances/instances.size() * myopeGain + hyperAppearances/instances.size() * hyperGain);
		
		
		return gain;
	
	}
	
		
	public int returnHighestGain(){
		double highest = findMax(gain, gainPerscrip, gainAstigmatic, gainTearProdRate);
		System.out.println("This is the highest " + highest);
		if (highest == gain) return 1;
		else if (highest == gainPerscrip) return 2;
		else if (highest == gainAstigmatic) return 3;
		else return 4;
		
	}
	
	private double findMax(double... vals) {
		   double max = Double.NEGATIVE_INFINITY;

		   for (double d : vals) {
		      if (d > max) max = d;
		   }

		   return max;
		}
	
	
	
	public int calculateHighestGain(){
		
		
		
		return 0;
	}
}