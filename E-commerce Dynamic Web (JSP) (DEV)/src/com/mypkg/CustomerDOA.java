package com.mypkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomerDOA {

	static Connection connection = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static int insertCustomer(Customer customer) {
		int results = 0;
		connection = DBConnection.createConnection();

		try {
			ps = connection.prepareStatement("INSERT INTO Customer"
					+ "(first_name, last_name, email, uname, pword, regdate)" + "VALUES(?,?,?,?,?, CURDATE())");
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getUname());
			ps.setString(5, customer.getPword());

			results = ps.executeUpdate();

			System.out.println("CONNECTION CLOSED!!!!!");
			connection.close();
		} catch (SQLException e) {
			System.out.println("Insert Failed..." + e);
		}
		return results;
	}

	public static List<Customer> login(String uname, String pword) throws SQLException {

		connection = DBConnection.createConnection();
		ps = connection
				.prepareStatement("SELECT * FROM Customer WHERE uname='" + uname + "' and pword='" + pword + "'");
		rs = ps.executeQuery();
		System.out.println("SELECT SUCCESSFUL");
		List<Customer> user = new ArrayList<Customer>();

		Customer c;
		while (rs.next()) {
			c = new Customer();
			c.setUname(rs.getString(5));
			c.setPword(rs.getString(6));
			user.add(c);
		}

		return user;
	}

	public static List<Customer> allCustomers() throws SQLException {

		connection = DBConnection.createConnection();

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Customer ");
		System.out.println("SELECT SUCCESSFUL!!");
		List<Customer> customers = new ArrayList<Customer>();

		Customer c;
		while (rs.next()) {
			c = new Customer();
			c.setId(rs.getInt(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			c.setEmail(rs.getString(4));
			c.setUname(rs.getString(5));
			c.setPword(rs.getString(6));
			c.setRegDate(rs.getDate(7));

			customers.add(c);
			System.out.println("CONNECTION CLOSED!!!!!");
			connection.close();

		}

		return customers;
	}

	public static List<Customer> userLogin(String userid) throws SQLException {
		// textEdit#7
		connection = DBConnection.createConnection();

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Customer WHERE uname= " + userid);
		System.out.println("SELECT SUCCESSFUL!!");
		List<Customer> customers = new ArrayList<Customer>();

		Customer c;
		while (rs.next()) {
			c = new Customer();
			c.setId(rs.getInt(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			c.setEmail(rs.getString(4));
			c.setUname(rs.getString(5));
			c.setPword(rs.getString(6));
			c.setRegDate(rs.getDate(7));

			customers.add(c);
			System.out.println("CONNECTION CLOSED!!!!!");
			connection.close();

		}

		return customers;
	}

}
