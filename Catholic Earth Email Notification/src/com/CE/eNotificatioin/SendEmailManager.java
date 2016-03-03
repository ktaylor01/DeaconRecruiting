package com.CE.eNotificatioin;

public class SendEmailManager {

	static String subject = "Welcome :-)";
	static String message = "Welcome to Catholic Earth's automated email notification feature";
	static String sender = "kellytaylor644@gmail.com";
	
	

	public static int prepEmail(String firstName, String lastName, String emailAdd) {

		int results = 0;
		boolean exists = true;

		SendEmail emailObj = new SendEmail();
		emailObj.setSubject(subject);
		emailObj.setMessage(message);
		emailObj.setSender(sender);

		EmailContact con = new EmailContact();
		con.setFirstName(firstName);
		con.setLastName(lastName);
		con.setEmail(emailAdd);

		exists = EmailContactDAO.doesExist(con);
		if (!(exists)) {
			results = EmailContactDAO.addEmailContact(con);
			SendEmailDAO.sendWelcomeMessage(con, emailObj);
		}
		return results;
	}

}
