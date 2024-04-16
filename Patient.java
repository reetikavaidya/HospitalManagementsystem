package hospitalmangementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection con;
	private Scanner sc;
	public Patient(Connection con ,Scanner sc) {
		this.con=con;
		this.sc=sc;
		
	}
	

	//method 1
	public void  addPatient() {
		System.out.println("Enter patient name: ");
		String Pname=sc.next();
		System.out.println("Enter patient age: ");
		int age=sc.nextInt();
		System.out.println("Enter patient gender: ");
		String gender=sc.next();
	
	try {
	String q1="Insert into patients(name,age,gender) values(?,?,?);";

	PreparedStatement ps=con.prepareStatement(q1);
	ps.setString(1, Pname);
	ps.setInt(2, age);
	ps.setString(3,gender);
	
	int affectedRows=ps.executeUpdate();
	if (affectedRows>0) {
		System.out.println("Patient added successfully.");
		
	} else {
		System.out.println("Failed to add patient!!");

	}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
	//method 2
	public void viewpatient() {
		String q1="SELECT * FROM PATIENTS";
		try {
			PreparedStatement ps=con.prepareStatement(q1);
			//ResultSet is interface
			ResultSet rs=ps.executeQuery();
			System.out.println("Patients:-");
			while (rs.next()) {
				int id =rs.getInt("id");
				String name=rs.getString("name");
				int age =rs.getInt("age");
				String gender=rs.getString("gender");
				System.out.println(" "+id+" "+name+" "+age+" "+gender+" ");
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//method 3
	public boolean getPatientByid(int id) {
		String q1="SELECT* FROM PATIENTS WHERE ID=?";
		try {
			PreparedStatement ps=con.prepareStatement(q1);
			ps.setInt(1,id);
		
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				return true;
				
			} else {
				return false;

			}
		
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
