package com.mypkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductDOA {

	static Connection connection = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static int insertProduct(Product product) {
		int results = 0;
		connection = DBConnection.createConnection();
		
		
		try {
			ps = connection.prepareStatement("INSERT INTO Products" + "(product_name, product_description)" + "VALUES(?,?)" );
			
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductDescription());
			results = ps.executeUpdate();
			
			
			System.out.println("CONNECTION CLOSED!!!!!");
			connection.close();
		} catch (SQLException e) {
			System.out.println("Insert Failed..." + e);
		}
		return results;
	}
	
	public static List<Product> showProducts() throws SQLException{
		connection = DBConnection.createConnection();
		ps = connection.prepareStatement("SELECT * FROM Products");
		rs = ps.executeQuery();
		List <Product> prodList = new ArrayList<Product>();
		
		Product product;
		while(rs.next()){
			product = new Product();
			product.setProductName(rs.getString(2));
			product.setProductDescription(rs.getString(3));
			prodList.add(product);
		}
		
		return prodList;
	}

}
