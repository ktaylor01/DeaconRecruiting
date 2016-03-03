<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mypkg.DBConnection"%>
<%@ page import="com.mypkg.CustomerDOA"%>
<%@ page import="com.mypkg.Customer"%>
<%@page import="com.mypkg.Product"%>
<%@page import="com.mypkg.ProductDOA"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="JSFile.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products Page</title>
</head>
<h1>Inserting products</h1>
<body onload="displayResults() " class="page">
	<!-- ============================ JAVA CODE  =============================== -->
	<!-- ============================ JAVA CODE  =============================== -->
	<%
		int results = 0;

		if (request.getParameter("submit") != null) {
			String pName = new String();
			String pd = new String();

			if (request.getParameter("prod") != null) {
				pName = request.getParameter("prod");

			}
			if (request.getParameter("prodD") != null) {
				pd = request.getParameter("prodD");
			}
			Product p = new Product();
			//load object duh!
			p.setProductName(pName);
			p.setProductDescription(pd);

			results = ProductDOA.insertProduct(p);

		}
	%>
	<!-- ============================ END OF JAVA  ============================= -->
	
	<form name="myForm" action="Prod.jsp" method="post">
		<table id="productInput" border="0">
			<tbody>
				<tr>
					<td>Product Name:</td>
					<td><input type="text" name="prod" placeholder="Product Name"
						size="50" /></td>
				</tr>
				<tr>
					<td>Product Description:</td>
					<td><input type="text" name="prodD"
						placeholder="Product Description" size="50" /></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" value=<%=results%> name="hidden" /> <input
			type="submit" value="Submit" name="submit" /> <input type="reset"
			value="Clear" name="clear" />
	</form>
			
	


	<br />
	<br />
	<br />
	<br />
	<!-- ============================ SHOW ALL TABLE  ============================= -->

	<h1>SHOW ALL PRODUCTS</h1>
	<%
		List<Product> prodList = new ArrayList<Product>();
		prodList = ProductDOA.showProducts();
	%>
	<br />
	<br />
	<a href="javascript:showhide('showTable');">View All Products</a>
	<form name="showAll">
	<div id="showTable">
		<table id="Product Output" align="center" width="600" border="1">
			<tbody>
				<tr>
					<th width="98"><div align="center">Product Name</div></th>
					<th width="98"><div align="center">Product Description</div></th>
				</tr>
				<%
					for (Product e : prodList) {
				%>
				<tr>
					<td><%=e.getProductName()%></td>
					<td><%=e.getProductDescription()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		</small></p>
		</div>
		
	</form>
	<!-- ============================ END OF SHOW ALL TABLE  ============================= -->

</body>
</html>