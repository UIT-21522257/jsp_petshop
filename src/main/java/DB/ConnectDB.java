package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	private static Connection connection;

	public static Connection connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_petshop", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			connection = null;
		}
		return connection;
	}

	public static void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ConnectDB connectDB = new ConnectDB();
		System.out.println(connectDB.connection());
	}
}
