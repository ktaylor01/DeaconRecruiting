package com.CE.eNotificatioin;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.sun.jndi.cosnaming.IiopUrl.Address;

public class EmailContact {
	int id;
	String name;
	String first;
	String last;
	String email;
	
	public int getId(){
		return id;
	}
	public void intSetId(int id){
		this.id = id;
	}
	
	public String getContactName(){
		return name;
	}
	public void setContactName(String name){
		this.name = name;
	}
	public String getFirstName(){
		return first;
	}
	public void setFirstName(String first){
		this.first = first;
	}
	public String getLastName(){
		return last;
	}
	public void setLastName(String last){
		this.last = last;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	
}
