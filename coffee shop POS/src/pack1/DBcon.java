package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBcon {
	
	public Connection getConnection() {
        try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:coffeeshop.db");
			createTablesIfNotExist(con);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null; 
    }
	
	private void createTablesIfNotExist(Connection con) {
	    String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
	            + "username TEXT NOT NULL UNIQUE, "
	            + "password TEXT NOT NULL)";
	            
	    String createMenuTable = "CREATE TABLE IF NOT EXISTS menu ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
	            + "name TEXT NOT NULL UNIQUE, "
	            + "price REAL NOT NULL, "
	            + "isDrink INTEGER NOT NULL, "
	            + "drink_temp TEXT DEFAULT NULL)";

	    try (Statement st = con.createStatement()) {
	        st.execute(createUsersTable);
	        st.execute(createMenuTable);
	        
	        try (ResultSet rs = st.executeQuery("SELECT count(*) FROM users")) {
	            if (rs.next() && rs.getInt(1) == 0) {
	                st.execute("INSERT INTO users (username, password) VALUES ('admin', '1234')");
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Admin> getAdmins() {
		ArrayList<Admin> users = new ArrayList<Admin>();
		try (Connection con = getConnection()) {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from users");
			
			while (rs.next()) {
				users.add(new Admin(rs.getString("username") , rs.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	 
	public void addAdmin(Admin admin) {
		try (Connection con = getConnection()) {
			String query = "insert into users (`username`, `password`) VALUES (?, ?)";
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, admin.username);
			ps.setString(2, admin.password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeAdmin(String username) {
		try (Connection con = getConnection()) {
			String query = "delete from users where username = ?";
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, username);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Drink> getDrinks() {
		ArrayList<Drink> drinks = new ArrayList<Drink>();
		try (Connection con = getConnection()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return drinks;
	}
	
	public void addDrink(Drink drink) {
		try (Connection con = getConnection()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeDrink(String name) {
		try (Connection con = getConnection()) {
			String query = "delete from menu where name = ?";
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, name);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dessert> getDesserts() {
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		try (Connection con = getConnection()) {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery("select name, price from menu where isdrink <> 1");
			
			while (rs.next()) {
				desserts.add(new Dessert(rs.getString("name"), rs.getDouble("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return desserts;
	}
	
	public void addDessert(Dessert dessert) {
		try (Connection con = getConnection()) {
			String query = "insert into menu (name, price, isDrink) values (?, ?, 0)";
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, dessert.name);
			ps.setDouble(2, dessert.getPrice());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeDessert(String name) {
		try (Connection con = getConnection()) {
			String query = "delete from menu where name = ?";
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, name);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrice(String name, Double newPrice) {
		try (Connection con = getConnection()) {
			String query = "update menu set price = ? where name = ?";
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setDouble(1, newPrice);
			ps.setString(2, name);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}