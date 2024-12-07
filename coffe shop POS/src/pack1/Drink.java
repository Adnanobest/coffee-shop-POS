package pack1;

public class Drink extends Menu {

	int state;
	public Drink(String name, double price, String HorC) {

		super(name, price);
		if (HorC.length()==3) {
			state=3;
		}else if(HorC.charAt(0)=='H'){
			state=1;
		}else {
			state=2;
		}
	
	}




}
