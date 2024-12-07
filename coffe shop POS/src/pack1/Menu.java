package pack1;

public class Menu {
	public Menu(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public String name;
	private double price;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
