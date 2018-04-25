package application;

import java.sql.*;

public class Driver {

	public static Connection Connector(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false","root","root");

			return myConn;
			
		}
		catch (Exception e) {
			return null;
			
		}
	}

}
