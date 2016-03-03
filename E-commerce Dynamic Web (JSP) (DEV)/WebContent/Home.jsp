<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="com.mypkg.DBConnection"%>
<%@ page import="com.mypkg.CustomerDOA"%>
<%@ page import="com.mypkg.Customer"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="JSFile.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body onload ="displayResults()" class="page">
<h1>Home Page</h1>
	<br/>
	

<!-- =============================== DIVS  ================================= -->
<br/>
<br/>
	<div class='allDivs'>
	<div id='welcome'>
	Welcome to my Website! Enter your contact information here...lets stay connected!
	</div>
    </div>
<br/>
    
<!-- ============================ END OF DIVS  ============================= -->
    
<!-- ============================ JAVA CODE  =============================== -->
<%

int results = 0;

if(request.getParameter("submit") != null){
	String firstName = new String();
	String lastName = new String();
	
	if(request.getParameter("firstName") != null){
		firstName = request.getParameter("firstName");
		
	}
	if(request.getParameter("lastName") != null){
		lastName = request.getParameter("lastName");
	}	
	Customer c = new Customer();
	//load object duh!
	c.setFirstName(firstName);
	c.setLastName(lastName);
	
	results = CustomerDOA.insertCustomer(c);
	
}%>
<!-- ============================ END OF JAVA  ============================= -->


<!-- ================================ FORM  ================================ -->
	<form name="myForm" action="Home.jsp" method="post">
	
		<table id="CustomerInput" border="0">
			<tbody>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName"
						placeholder="First Name" size="50" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastName" placeholder="Last Name"
						size="50" /></td>
				</tr>
			</tbody>
		</table>
	<input type="hidden" value=<%=results%> name="hidden"/>
	<input type="submit" value="Submit" name="submit" />
	<input type="reset" value="Clear" name="clear" />
	
	
	<input type="button" value="View My Contacts" name="ViewContacts"
    onclick="openPage('ShowAllCustmers.jsp')"/>
    
	</form>
<!--  ============================ END OF FORM  ============================ -->
<br/>
<br/>
<br/>
<br/>
</body>
</html>