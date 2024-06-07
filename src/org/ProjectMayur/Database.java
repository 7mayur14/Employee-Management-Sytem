package org.ProjectMayur;

import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class Database {
//	declaring connection and datasource variables
	static Connection conn;
	static SQLiteDataSource ds;
	
//	initialize method to initialize the database with  EmployeeDatas table
	public static void dbInit() {
		ds = new SQLiteDataSource();
		
		try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:EmployeeDB.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            
            System.exit(0);
        }
        try {
        	 conn = ds.getConnection();
        	 
        	 Statement stmt = conn.createStatement();
//        	 sql query to add EmployeeData table
        	 String query = "CREATE TABLE IF NOT EXISTS EmployeeData ( "
        	 				+ "Employee_id TEXT PRIMARY KEY ,"
        	 				+ "Employee_name TEXT,"
        	 				+ "department TEXT,"
        	 				+ "Employee_joinDate TEXT,"
        	 				+ "Employee_gender TEXT,"
        	 				+ "Employee_contact TEXT,"
        	 				+ "Employee_email TEXT,"
        	 				+ "salary TEXT,"
        	 				+ "Employee_address TEXT"
        	 				+ " );";  	 

//        	 executing the query using statement variable
        	 stmt.executeUpdate(query);
        	 conn.close();
        	 
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        ;
    }
	
//	function to add the EmployeeData into the database
	protected static void insertEmployeeData(String id,String name,String department,
									 String joinDate,String gender,String contact,String salary,String email,String address
									) throws SQLException {
		String query = "INSERT INTO EmployeeData("
				+ "Employee_id,"
				+ "Employee_name,"
				+ "department,"
				+ "Employee_joinDate,"
				+ "Employee_gender,"
				+ "Employee_contact,"
				+ "salary,"
				+ "Employee_email,"
				+ "Employee_address) "
					+ "VALUES("
						+"'"+ id +"',"
						+"'"+ name +"',"
						+"'"+ department +"',"
						+"'"+ joinDate +"',"
						+"'"+ gender +"',"
						+"'"+ contact +"',"
						+"'"+ salary +"',"
						+"'"+ email +"',"
						+"'"+ address +"');" ;
		
		conn = ds.getConnection();
		Statement stmt =  conn.createStatement();
		stmt.executeUpdate(query);
		conn.close();
	}

//	Fucntion to update the EmployeeData data using the id 
	protected static void updateEmployeeData(String id,String name,String department,String contact,
			 String joinDate,String gender,String email, String salary,String address
			) throws SQLException {
			String query = "UPDATE EmployeeData "
					+ "SET "
					+ "Employee_name = '"+name + "',"
					+ "department = '"+department + "',"
					+ "Employee_contact = '"+contact+ "',"
					+ "Employee_joinDate = '"+joinDate+ "',"
					+ "Employee_gender = '"+gender + "',"
					+ "salary = '"+salary + "',"
					+ "Employee_email = '"+email + "',"
					+ "Employee_address = '"+address + "'"
					
					+ "WHERE "
					+ "Employee_id = '"+id+"'";
			System.out.println(query);
			conn = ds.getConnection();
			Statement stmt =  conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
			}
	//	function to delete the EmployeeData from the database
	protected static void deleteEmployeeData(String id) throws SQLException {
		String query = "DELETE FROM EmployeeData WHERE Employee_id = '"+id+"';"; 
		conn = ds.getConnection();
		Statement stmt =  conn.createStatement();
		stmt.executeUpdate(query);
		conn.close();
	
	}



	//	function that searches the EmployeeData in the database and updates the values using tabel model
	public static void searchEmployeeData(DefaultTableModel model,String searchTerm,String column) throws SQLException {
		model.setRowCount(0);
		String query = "SELECT * FROM EmployeeData WHERE "+column+" LIKE '%"+searchTerm +"%';";
		conn = ds.getConnection();
		Statement stmt =  conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			String id = rs.getString("Employee_id");
			String name = rs.getString("Employee_name");
			String department = rs.getString("department");
			String joinDate = rs.getString("Employee_joinDate");
			String gender = rs.getString("Employee_gender");
			String contact = rs.getString("Employee_contact");
			String salary = rs.getString("salary");
			String email = rs.getString("Employee_email");
			String address = rs.getString("Employee_address");
			
			
			model.addRow(new Object[]{id,name,department,joinDate,gender,contact,salary,email,address});
			
		}
		
		conn.close();
		rs.close();
		
	}

	// function to fetch the data and add it to the model so that the jtable is updated 
	public static void loadData(DefaultTableModel model) throws SQLException {
		model.setRowCount(0);
		String query = "SELECT * FROM EmployeeData ;";
		conn = ds.getConnection();
		Statement stmt =  conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String id = rs.getString("Employee_id");
			String name = rs.getString("Employee_name");
			String department = rs.getString("department");
			String joinDate = rs.getString("Employee_joinDate");
			String gender = rs.getString("Employee_gender");
			String contact = rs.getString("Employee_contact");
			String salary = rs.getString("salary");
			String email = rs.getString("Employee_email");
			String address = rs.getString("Employee_address");
			
			
			model.addRow(new Object[]{id,name,department,joinDate,gender,contact,salary,email,address});
			
		}
		
		conn.close();
		rs.close();
		
	}
}
	

