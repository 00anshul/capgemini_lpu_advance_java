package com.connectdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
	public static void main(String[] args) {
//		Employee.addData();
//		Employee.getdata();
	}

	public static void addData() {
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

					// create statement
					String sql = "insert into employee values(202,'Will','Java'),(203,'Atom','Python'),(204,'Joy','Web Dev'),(205,'Dan','Music');";
					Statement stm = connect.createStatement();

					// execute statement
					stm.execute(sql);

					// close connection
					connect.close();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
}
	public static void getdata() {
		String url = "jdbc:postgresql://localhost:5432/school";
		String un = "postgres";
		String pwd = "root";
		
		
		try {
			Class.forName("org.postgresql.Driver");
			
			Connection connect = DriverManager.getConnection(url, un, pwd);

			String sql = "select * from employee;";
			
			Statement stm = connect.createStatement();
		
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			
			connect.close();

		} catch (Exception e) {//no need for multiple catch just use parent exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
	}

