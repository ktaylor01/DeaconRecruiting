package com.CE.eNotificatioin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailContactDAO {
	static Connection connection = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static int addEmailContact(EmailContact contact) {
		int result = 0;
		connection = DBConnection.createConnection();

		try {
			ps = connection.prepareStatement("INSERT INTO contact_table" + "(f_name, l_name, email)" + "VALUES(?,?,?)");

			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getEmail());

			result = ps.executeUpdate();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Insert Successful");

		return result;
	}

	public static boolean doesExist(EmailContact contact) {
		List<EmailContact> contacts = new ArrayList<EmailContact>();
		EmailContact c;
		boolean exists = true;

		connection = DBConnection.createConnection();
		try {
			ps = connection.prepareStatement("SELECT * FROM contact_table Where f_name='" + contact.getFirstName()
					+ "' AND l_name='" + contact.getLastName() + "' AND email='" + contact.getEmail() + "'");
			rs = ps.executeQuery();

			while (rs.next()) {
				c = new EmailContact();
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setEmail(rs.getString(4));
				contacts.add(c);

			}
			if (contacts.isEmpty()) {
				exists = false;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exists;
	}

	public static List<EmailContact> getEmails() {
		List<EmailContact> list = new ArrayList<EmailContact>();
		EmailContact c;
		connection = DBConnection.createConnection();

		try {
			ps = connection.prepareStatement("SELECT * FROM contact_table");
			rs = ps.executeQuery();

			while (rs.next()) {
				c = new EmailContact();
				c.setEmail(rs.getString(4));
				list.add(c);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	
}
