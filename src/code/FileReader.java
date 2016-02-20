package code;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileReader {
	
	/*public static final String file = "src/LensData.txt";
	
	public static ArrayList<Instance> createInstances() {
		
		BufferedReader reader = null;
		DataInputStream dis = null;
		
		ArrayList<Instance> instances = new ArrayList<Instance>();
		
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			reader = new BufferedReader(new InputStreamReader(fis));;
			
			String line;
			Instance i = null;
			ArrayList<Attribute> attributes;
			while ((line = reader.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line, " ");
				attributes = new ArrayList<Attribute>();
				i = new Instance();
				
				// Add check to see if correct amount of attributes 
				
				String age = st.nextToken(); 
				String perscription = st.nextToken();
				String astigmatic = st.nextToken();
				String tearProdRate = st.nextToken();
				String outcome = st.nextToken();
				
				if (age.equalsIgnoreCase("1")){
					attributes.add(new Attribute("Age", Attribute.young));
				}
			}
		} catch (Exception e){
			
		}
		
		return instances;
		
	}
	
	
	
	

	public FileReader(){
		
	
		
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
	}*/
	

}
