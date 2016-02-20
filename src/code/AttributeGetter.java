package code;
import java.util.ArrayList;

public class AttributeGetter {

	Attribute A;

	public AttributeGetter(Attribute A) {
		this.A = A;
	}

	public int getAttribute() {
		switch (A) {
		case AGE:
			return 0;
		case PERSCRIPTION:
			return 1;
		case ASTIGMATIC:
			return 2;
		case TEARPRODRATE:
			return 3;

		default:
			System.out.println("Undefined Attribute");
			return 0;
		}
	}

	public ArrayList<Integer> getAttributeValues() {
		ArrayList<Integer> list = new ArrayList<>();
		switch (A) {
		case AGE:
			list.add(1);
			list.add(2);
			list.add(3);
			return list;
		case PERSCRIPTION:
			list.add(1);
			list.add(2);
			return list;
		case ASTIGMATIC:
			list.add(1);
			list.add(2);
			return list;
		case TEARPRODRATE:
			list.add(1);
			list.add(2);
			return list;

		default:
			System.out.println("Undefined Attribute");
			return list;
		}
	}

}
