package pack1;

public class Menu {
	public Menu(String name, Double price) {
		this.name = name;
		this.price = price;
	}
	public String name;
	private Double price;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
