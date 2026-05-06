package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBcon {

	public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee_shop_pos", "root", "Adnanbest");
    }
	
	public ArrayList<Admin> getUsers() throws SQLException {
		ArrayList<Admin> users = new ArrayList<Admin>();
		Statement st = getConnection().createStatement();
		
		ResultSet rs = st.executeQuery("select * from users");
		
		while (rs.next()) {
			users.add(new Admin(rs.getString("username") , rs.getString("password")));
		}
		
		return users;
	}
	 
	public void AddAdmin(Admin admin) throws SQLException {
		String query = "insert into users (`username`, `password`) VALUES (?, ?)";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, admin.username);
		ps.setString(2, admin.password);
		ps.executeUpdate();
	}
	
	public ArrayList<Drink> getDrinks() throws SQLException {
		ArrayList<Drink> drinks = new ArrayList<Drink>();
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("select name, price, drink_temp from menu where isdrink = 1");
		
		while (rs.next()) {
			if (rs.getString("drink_temp").equals("both")) {
				drinks.add(new Drink(rs.getString("name"), rs.getDouble("price"), "H C"));
			} else if (rs.getString("drink_temp").equals("cold")) {
				drinks.add(new Drink(rs.getString("name"), rs.getDouble("price"), "C"));
			} else if (rs.getString("drink_temp").equals("hot")) {
				drinks.add(new Drink(rs.getString("name"), rs.getDouble("price"), "H"));				
			}
		}
		
		return drinks;
	}
	
	public void addDrink(Drink drink) throws SQLException {
		String query = "insert into menu (name, price, isDrink, drink_temp) values (?, ?, 1, ?)";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, drink.name);
		ps.setDouble(2, drink.getPrice());
		if (drink.state == 0) {
			ps.setString(3, "both");
		} else if (drink.state == 1) {
			ps.setString(3, "hot");
		} else {
			ps.setString(3, "cold");
		}
		ps.executeUpdate();
	}
	
	public void removeDrink(String name) throws SQLException {
		String query = "delete from menu where name = ?";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, name);
		
		ps.executeUpdate();
	}
	
	public ArrayList<Dessert> getDesserts() throws SQLException {
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("select name, price from menu where isdrink <> 1");
		
		while (rs.next()) {
			desserts.add(new Dessert(rs.getString("name"), rs.getDouble("price")));
		}
		
		return desserts;
	}
	
	public void addDessert(Dessert dessert) throws SQLException {
		String query = "insert into menu (name, price, isDrink) valuse (?, ?, 0)";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, dessert.name);
		ps.setDouble(2, dessert.getPrice());
		
		ps.executeUpdate();
	}
	
	public void removeDessert(String name) throws SQLException {
		String query = "delete from menu where name = ?";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, name);
		
		ps.executeUpdate();
	}
	
	public static void main(String[] args) throws SQLException {
//		new DBcon().addDrink(new Drink("drink1", 45.6, "H C"));
		System.out.println(new DBcon().getDrinks().getFirst().name);
	}
	
}