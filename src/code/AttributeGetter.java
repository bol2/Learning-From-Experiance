package code;

public class AttributeGetter {
	
	Attribute A;
	
	public AttributeGetter(Attribute A){
		this.A = A;
	}
	
	public int getAttribute(){
		switch (A){
			case AGE:
				return 1;
			case PERSCRIPTION:
				return 2;
			case ASTIGMATIC:
				return 3;
			case TEARPRODRATE:
				return 4;
			
			default:
				System.out.println("Undefined Attribute");
				return 0;	
		}
	}

}
