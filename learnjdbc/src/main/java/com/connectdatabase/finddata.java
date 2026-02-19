package com.connectdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class finddata {
public static void main(String[] args) {
	
	String url = "jdbc:postgresql://localhost:5432/school";
	String un = "postgres";
	String pwd = "root";
	
	
	try {
		Class.forName("org.postgresql.Driver");
		
		Connection connect = DriverManager.getConnection(url, un, pwd);

		String sql = "select * from student;";
		
		Statement stm = connect.createStatement();
	
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}
		
		connect.close();

	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
