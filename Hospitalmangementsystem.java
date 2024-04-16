package hospitalmangementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Hospitalmangementsystem {
	public static final String url="jdbc:mysql://localhost/hospital";
	public static final String username="root";
	public static final String password="root";
	public static void main(String[] args) throws SQLException {
		Scanner sc =new Scanner(System.in);
		Connection con=DriverManager.getConnection(url,username,password);
		Patient p=new Patient(con, sc);
		Doctors d=new Doctors(con);
		while(true) {
			System.out.println("Hospital Mangement System ");
			System.out.println("1.Add patient");
			System.out.println("2.View Patient \n 3.View Doctors \n 4.Book Appoinment \n 5.Exit ");
			System.out.println("Enter your choice:");
			int choice=sc.nextInt();
			switch (choice) {
			case 1: {
				//add patient
				p.addPatient();
				break;
			}
			case 2: {
				//view patient
				p.viewpatient();
				System.out.println();
				break;
			}
			case 3: {
				//view doctor
				d.viewDoctors();
				break;
			}
			case 4: {
				//book appointment
				bookappointment(p,d,con,sc);
				System.out.println();
				break;
				
			}
			case 5: {
				return;
			}
			
			default:
			System.out.println("Enter valid choice");
			break;
			}
		}
	}
	//method 
	public static void bookappointment(Patient p,Doctors d,Connection con,Scanner sc) {
		System.out.println("Enter patient id:");
		int patientid=sc.nextInt();
		System.out.println("Enter Doctor id:");
		int doctorid=sc.nextInt();
		System.out.println("Enter appointment date(yyyy-mm-dd:");
		String date=sc.next();
		if(p.getPatientByid(patientid)&& d.getDoctorsByid(doctorid)) {
			if(checkdravailability(doctorid,date,con)) {
				String appquery="Insert into appointments (patient_id,doctor_id,appointment_date)values(?,?,?)";
				try {
				
				PreparedStatement ps=con.prepareStatement(appquery);
				ps.setInt(1, patientid);
				ps.setInt(2, doctorid);
				ps.setString(3, date);
				int rowaffected=ps.executeUpdate();
				if (rowaffected>0) {
					System.out.println("Appointment booked");
					
				} else {
					System.out.println("Failed to book appointment!!!!!");

				}
				}catch (Exception e) {
				e.printStackTrace();
				}
				
			}
			
		}else {
			System.out.println("Either patient or doctor doesn't exist!!!!");
		}
		
		
	}
public static boolean checkdravailability(int doctorid,String date,Connection con) {
	String q1="Select count(*) from appointments where doctor_id=? and appointment_date=?";
	try {
		PreparedStatement ps=con.prepareStatement(q1);
		ps.setInt(1, doctorid);
		ps.setString(2, date);
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			int count =rs.getInt(1);
			if(count==0) {
				return true;
			}else {
				return false;
			}
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
		
	


}
