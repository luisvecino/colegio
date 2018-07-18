package org.luis.connection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {

	private String url, user, pwd;
	private static Connect instance;
	
	public Connect() {
		String driver = null;
		try {
		
			Properties p = new Properties();
			p.load(new FileReader("/Users/luis/examen-final/Colegio/alum.properties"));			
			driver = p.getProperty("driver");
			Class.forName(driver);
			url = p.getProperty("url");
			user = p.getProperty("user");
			pwd = p.getProperty("pwd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    
   
	// M�todo p�blic de factor�a est�tico
	public static Connection getConnection() throws SQLException {

		if (instance == null) {
			instance = new Connect();
		}

		try {

			return DriverManager.getConnection(instance.url, instance.user, instance.pwd);

		} catch (SQLException e) {
			throw e;
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}