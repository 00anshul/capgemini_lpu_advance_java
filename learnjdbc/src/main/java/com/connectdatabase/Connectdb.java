package com.connectdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connectdb {
	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/school";
		String un = "postgres";
		String pwd = "root";

		// load driver class

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("class loaded");

			// to establish the connection
			Connection connect = DriverManager.getConnection(url, un, pwd);
			System.out.println("connection done");

			//create statement
			String sql = "insert into student values(108,'Will','will@gamil.com','Male'),(109,'Soue','soue@gamil.com','Male');";
			Statement stm = connect.createStatement();
			
			//execute statement
			stm.execute(sql);
			
			//close connection
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
