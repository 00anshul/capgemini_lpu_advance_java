package com.connectdatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class sqlconnect {
public static void main(String[] args) {
	String url = "jdbc:mysql://localhost:3306/university";
	String un = "root";
	String pwd = "anshul";
	
	Scanner scan = new Scanner(System.in);
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("class loaded");

		// to establish the connection
		Connection connect = DriverManager.getConnection(url, un, pwd);
		System.out.println("connection done");
		
		String sql = "insert into Emp values(?,?,?);";
		PreparedStatement pstm = connect.prepareStatement(sql);
		
		System.out.println("Enter empno:");
		int id = scan.nextInt();
		pstm.setInt(1,id);
		
		System.out.println("Enter name:");
		String name = scan.next();
		pstm.setString(2, name);
		
		System.out.println("Enter job:");
		String email = scan.next();
		pstm.setString(3, email);
	
		
		pstm.execute();
		
		connect.close();
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}+
