package pack1;

public class Menu {
	public Menu(String name, double price) {
		this.name = name;
		this.price = price;
	}
	String name;
	double price;
	
	protected static int drinkSize(String size) {
		switch (size) {
		case "S": 
			return 350;
		case "M":
			return 475;
		case "L":
			return 600;
		}
		return -1;
	}
}
