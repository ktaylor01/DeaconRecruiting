package com.CE.eNotificatioin;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Testing");
		/*
		 * //THIS IS TO TEST String subject = "GROUP EMAIL"; String message =
		 * "CATHOLIC EARTH GROUP EMAIL UPDATE"; String sender =
		 * "kellytaylor644@gmail.com";
		 * 
		 * SendEmail emailObj = new SendEmail(); emailObj.setSubject(subject);
		 * emailObj.setMessage(message); emailObj.setSender(sender);
		 * 
		 * List<EmailContact> emails = new ArrayList<EmailContact>(); emails =
		 * EmailContactDAO.getEmails();
		 * System.out.println("this is my object array"); for(EmailContact c :
		 * emails){ System.out.println(c.getEmail()); } System.out.println();
		 * System.out.println("testing group email");
		 * 
		 * SendEmailDAO.sendGroupEmail(emailObj);
		 */

		// THIS IS TO TEST IF EMAIL CONTACT ALREADY EXISTS AND THE INSERT NEW
		// CONTACTS METHODS

		// SENDER INFO
		String subject = " EMAIL";
		String message = "WELCOME CATHOLIC EARTH  EMAIL UPDATE";
		String sender = "kellytaylor644@gmail.com";

		SendEmail emailObj = new SendEmail();
		emailObj.setSubject(subject);
		emailObj.setMessage(message);
		emailObj.setSender(sender);

		// CONTACT INFO
		String fName = "Teddy";
		String lName = "Voutour";
		String e = "teddy@voutour.com";
		int result = 0;

		// CREATE AND LOAD EMAILCONTACT OBJECT
		EmailContact c = new EmailContact();
		c.setFirstName(fName);
		c.setLastName(lName);
		c.setEmail(e);

		// TEST IF CONTACT EXISTS..IF NO THEN ADD CONTACT TO THE DATABASE AND
		// SEND A WELCOME EMAIL.
		boolean exists = true;
		exists = EmailContactDAO.doesExist(c);
		System.out.println(exists);

		if (exists) {
			System.out.println("already registered");
		} else {
			result = EmailContactDAO.addEmailContact(c);
			SendEmailDAO.sendWelcomeMessage(c, emailObj);
		}

	}

}
