package misc;

import java.sql.*;

public class Connecting {
	static Connection conn;

	public Connection Connect() {
		String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/library_system?serverTimezone=UTC";
		String DB_User = "root";
		String DB_Pass = "";
		
		try {
			Class.forName(JDBC_Driver);
			conn = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
