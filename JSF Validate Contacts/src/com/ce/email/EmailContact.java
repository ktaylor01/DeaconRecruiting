package com.ce.email;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class EmailContact {
	private String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	//private Matcher matcher;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	//private List<EmailContact> contacts;

	public EmailContact() {// pre-populate

	}

	
	public EmailContact(String firstName, String lastName, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public EmailContact(int id, String firstName, String lastName, String email) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void validateEmailAddress(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		if (value == null) {
			return;
		}
		pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			FacesMessage message = new FacesMessage("Invalid Email address!");

			throw new ValidatorException(message);
		}

	}

	@Override
	public String toString() {
		return "Email Contact [id =" + id + ", First Name = " + firstName + ", Last Name = " + lastName + ", Email = "
				+ email + "]";
	}

}
