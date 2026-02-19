package com.connectdatabase;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class dynamicinput {
public static void main(String[] args) {
	String url = "jdbc:postgresql://localhost:5432/school";
	String un = "postgres";
	String pwd = "root";
	
	Scanner scan = new Scanner(System.in);
	try {
		Class.forName("org.postgresql.Driver");
		System.out.println("class loaded");

		// to establish the connection
		Connection connect = DriverManager.getConnection(url, un, pwd);
		System.out.println("connection done");
		
		String sql = "insert into student values(?,?,?,?);";
		PreparedStatement pstm = connect.prepareStatement(sql);
		
		System.out.println("Enter id:");
		int id = scan.nextInt();
		pstm.setInt(1,id);
		
		System.out.println("Enter name:");
		String name = scan.next();
		pstm.setString(2, name);
		
		System.out.println("Enter email:");
		String email = scan.next();
		pstm.setString(3, email);
		
		System.out.println("Enter gender:");
		String gender = scan.next();
		pstm.setString(4, gender);
		
		pstm.execute();
		
		connect.close();
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
