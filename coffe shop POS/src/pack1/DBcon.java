package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {

	public static Connection getConnection() {
        Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee_shop_pos", "root", "Nn.2411@");
		} catch (SQLException e) {
			System.out.println();
			e.printStackTrace();
		}
        return connection;
    }
	
	Connection con = getConnection();
	
	 
	
}