package src.Domain.Domain1.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionPool {
	private String id;
	private String pw;
	private String url;
	
	protected static Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	ConnectionPool(){
		id = "root";
		pw = "1234";
		url = "jdbc:mysql://localhost:3306/musicdb";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
