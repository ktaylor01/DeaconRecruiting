package com.ce.email;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean 
@SessionScoped
public class EmailContactController {
	private List<EmailContact> contacts;
	private EmailDbUtil emailDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public EmailContactController()throws Exception{
		contacts = new ArrayList<>();
		emailDbUtil = EmailDbUtil.getInstance();
	}
	public List<EmailContact> getContacts(){
		return contacts;
	}
	public void loadContacts(){
		logger.info("loading contacts");
		contacts.clear();
		
		try{
			//get all contacts from the database
			contacts = emailDbUtil.getContacts();
			
			
		}catch(Exception exc){
			logger.log(Level.SEVERE, "Error loading contacts", exc);
			addErrorMessage(exc);
		}
	}
	
	public String addContact(EmailContact theContact){
		logger.info("Adding contact: " + theContact);
		
		try{
			
			//add contact to the database
			emailDbUtil.addContact(theContact);
			
			
		}catch(Exception exc){
			//send this to server logs
			logger.log(Level.SEVERE, "Error adding Contact", exc);
			addErrorMessage(exc);
			return null;

			
		}
		return "list_contacts?faces-redirect=true";
	}
	public String loadContact(int contactId){
		logger.info("loading contact: " + contactId);
		try{
			EmailContact theContact = emailDbUtil.getContact(contactId);
			ExternalContext externalContext = 
					FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("contact", theContact);
			
;			
		}catch(Exception exc){
			logger.log(Level.SEVERE, "Error loading contact id: " + contactId, exc);
			
			addErrorMessage(exc);
			return null;
			
		}
		return "update-contact-form.xhtml";
	}
	
	
	public String updateContact(EmailContact theContact){
		System.out.println("Inside updateContact: " + theContact.getFirstName() +""
				+theContact.getLastName() + " " + theContact.getEmail());
		
		logger.info("updating contact: "+ theContact);
		try{
			emailDbUtil.updateContact(theContact);
			
		}catch(Exception exc){
			logger.log(Level.SEVERE, "Error updating Contact: " + theContact, exc);
			addErrorMessage(exc);
			return null;
			
		}
		return "list_contacts?faces-redirect=true";
	}
	
	private void addErrorMessage(Exception exc){
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	

}
