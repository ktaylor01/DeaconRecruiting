package com.mypkg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Customer c = new Customer();
		c.setFirstName("Josh");
		c.setLastName("Taylor");
		c.setEmail("josh@yahoo.com");
		c.setUname("Joshy");
		c.setPword("Josh1");
		
		int results = CustomerDOA.insertCustomer(c);
		String userid = "John";
		String password = "Carols";
		List<Customer> list = new ArrayList<Customer>();
		
		list = CustomerDOA.userLogin(userid, password);
		
		if(!(list.isEmpty())){
			for(Customer e: list){
				String user = e.getUname();
				System.out.println("user id: " + user);
			}
		}else{
			System.out.println("INVALID ENTRY");
		}
		
		/*while (rec.next()) {
	        for (int i = 1; i <= 7; i++) {
	            if (i > 1) System.out.print(",  ");
	            String columnValue = rec.getString(i);
	            System.out.print(columnValue + " " );
	        }
	        System.out.println("");
	    }*/
		
		
		
	}

}
