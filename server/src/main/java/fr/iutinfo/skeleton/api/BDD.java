package fr.iutinfo.skeleton.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {
	private static Connection conn;
	private String url = "jdbc:sqlite:./base_donne.db";
	private BDD() {
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance(){
		if(conn == null)
			new BDD();
		return conn;   
	}   
}
