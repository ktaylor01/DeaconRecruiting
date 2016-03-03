 package com.CE.eNotificatioin;

import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import com.sun.jndi.cosnaming.IiopUrl.Address;

public class SendEmailDAO {
	/*
	 * passed in an email contact object, and a sendEmail object
	 * initialize variables
	 * get system properties
	 * set up mail server
	 * get a default session object
	 * create a default MimeMessage
	 * set from, to, subject etc...
	 * set the actual message ..message.settext
	 * send message..transport.send
	 */

	public static void sendWelcomeMessage(EmailContact contact, SendEmail se) {

		final String username = "kellytaylor644@gmail.com";
		final String password = "Kvoutour.42";

		String fname = contact.getFirstName();
		String lname = contact.getLastName();
		String recipient = contact.getEmail();
		String subject = se.getSubject();
		String txtMessage = se.getMessage();
		String sender = se.getSender();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(txtMessage);

			Transport.send(message);
			System.out.println("Mail sent successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * TO DO Write code to send an email to all email contacts create a new jsp
	 * where by ted can add new email messages figure out how to set up a cron
	 * job
	 */
	public static int sendGroupEmail(SendEmail se) {
		int results = 0;
		final String username = "kellytaylor644@gmail.com";
		final String password = "Kvoutour.42";

		List<EmailContact> emails = new ArrayList<EmailContact>();
		emails = EmailContactDAO.getEmails();

		String subject = se.getSubject();
		String txtMessage = se.getMessage();
		String sender = se.getSender();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			
			for (EmailContact c : emails) {
				String email = c.getEmail();
				message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(email));
			}
			
			message.setSubject(subject);
			message.setText(txtMessage);

			Transport.send(message);
			System.out.println("Group Mail sent successfully");
			results = 1;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
			return results;
	}

}