package com.ce.email;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmailDbUtil {

	private static EmailDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/CE";// jndi - java naming &
														// direcory interface

	public static EmailDbUtil getInstance() throws Exception {// singleton
																// pattern only
																// have
		if (instance == null) { // one instance of dbUtility in
			instance = new EmailDbUtil(); // memory at a given time.
		}
		return instance;
	}

	private EmailDbUtil() throws Exception {// get a handle to the data source
		dataSource = getDataSource();

	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();

		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		//
		return theDataSource;
	}

	public List<EmailContact> getContacts() throws Exception {
		List<EmailContact> contacts = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();
			String sql = "Select * from contact_table order by l_name;";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("f_name");
				String lastName = myRs.getString("l_name");
				String email = myRs.getString("email");

				// create a new emailContact object
				EmailContact temp = new EmailContact(id, firstName, lastName, email);
				// add object to the list of contacts
				contacts.add(temp);
			}
			return contacts;

		} finally {
			myConn.close();
			myStmt.close();
			myRs.close();

		}
	}

	public void addContact(EmailContact theContact) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();
			String sql = "insert into contact_table(f_name, l_name, email) value(?,?,?)";
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setString(1, theContact.getFirstName());
			myStmt.setString(2, theContact.getLastName());
			myStmt.setString(3, theContact.getEmail());

			myStmt.execute();
		} finally {
			myConn.close();
			myStmt.close();
		}

	}
	public EmailContact getContact(int contactId) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn = dataSource.getConnection();
			String sql = "select * from contact_table where id=?";
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, contactId);
			myRs = myStmt.executeQuery();
			EmailContact theContact = null;
			
			if(myRs.next()){
				int id = myRs.getInt("id");
				String firstName = myRs.getString("f_name");
				String lastName = myRs.getString("l_name");
				String email = myRs.getString("email");
				
				theContact = new EmailContact(id, firstName, lastName, email);
				System.out.println(theContact.getFirstName() + " " + theContact.getLastName() + " " + theContact.getEmail());
				
			}else{
				throw new Exception("Could nor find contact id: " + contactId);
			}
			return theContact;
		}finally{
			myConn.close();
			myStmt.close();
			myRs.close();
		}
	}
	public void updateContact(EmailContact theContact) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "update contact" 
			+ "set f_name=?, l_name=?, email=?"+
			"where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, theContact.getFirstName());
			myStmt.setString(2, theContact.getLastName());
			myStmt.setString(3, theContact.getEmail());
			myStmt.setInt(4, theContact.getId());
			
			
			myStmt.execute();
		}finally{
			myConn.close();
			myStmt.close();
			
			
		}
	}
	

}
