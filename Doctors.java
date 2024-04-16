package hospitalmangementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
	private Connection con;

	public Doctors(Connection con ) {
		this.con=con;
	
		
	}
	

	//method 1
	public void viewDoctors() {
		String q1="SELECT * FROM Doctors";
		try {
			PreparedStatement ps=con.prepareStatement(q1);
			//ResultSet is interface
			ResultSet rs=ps.executeQuery();
			System.out.println("Doctors:-");
			while (rs.next()) {
				int id =rs.getInt("id");
				String name=rs.getString("name");
				
				String specialization=rs.getString("specialization");
				System.out.println(" "+id+" "+name+" "+specialization+" ");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//method 3
	public boolean getDoctorsByid(int id) {
		String q1="SELECT* FROM DOCTORS WHERE ID=?";
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
