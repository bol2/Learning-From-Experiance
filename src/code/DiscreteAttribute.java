package code;

public class DiscreteAttribute {

	public static final int Young = 0;
	public static final int Pre_presbyopic = 1;
	public static final int Presbyopic = 2;
	
	public static final int Myope = 0;
	public static final int Hypermetrope = 1;
	
	public static final int No = 0;
	public static final int Yes = 1;
	
	public static final int Reduced = 0;
	public static final int Normal = 1; 
	
	public static final int HardLenses = 0;
	public static final int SoftLenses = 1;
	public static final int NoLenses = 2;
	
	enum GiveLenses { Hard, Soft, No }
	enum Age { Young, Pre_presbyopic, Presbyopic }
	enum Perscription { Myope, Hypermetrope } 
	enum Astigmatic { No, Yes }
	enum TearProdRate { Reduced, Normal } 
	
	//public DiscreteAttribute(String name, double value) { super(name, value); } 
	//public DiscreteAttribute(String name, String value) { super(name, value); } 
	
	

}
