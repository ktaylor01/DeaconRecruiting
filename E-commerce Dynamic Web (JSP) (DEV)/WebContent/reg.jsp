<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mypkg.DBConnection"%>
<%@ page import="com.mypkg.CustomerDOA"%>
<%@ page import="com.mypkg.Customer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="JSFile.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body onload ="displayResults() " class="page">
<h1>Registration Form</h1>

<!-- ============================ JAVA CODE  =============================== -->
<%
	int results = 0;

	if (request.getParameter("submit") != null) {
		String first = new String();
		String last = new String();
		String email = new String();
		String userid = new String();
		String pword = new String();

		if (request.getParameter("fname") != null) {
			first = request.getParameter("fname");

		}
		if (request.getParameter("lname") != null) {
			last = request.getParameter("lname");
		}
		if (request.getParameter("email") != null) {
			email = request.getParameter("email");
		}
		if (request.getParameter("uname") != null) {
			userid = request.getParameter("uname");

		}
		if (request.getParameter("pass") != null) {
			pword = request.getParameter("pass");
		}
		Customer c = new Customer();
		//load object duh!
		c.setFirstName(first);
		c.setLastName(last);
		c.setEmail(email);
		c.setUname(userid);
		c.setPword(pword);

		results = CustomerDOA.insertCustomer(c);

	}
%><!-- ==========================END OF JAVA CODE  ============================ -->
<form name="myForm" action="reg.jsp" method="post">
	<center>
		<table id="CustomerInput" border="1" width="30%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="2">Enter Information Here</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="fname" placeholder="First Name"
						value="" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lname" placeholder="Last Name"
						value="" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"
						placeholder="Email Address" value="" /></td>
				</tr>
				<tr>
					<td>User Name:</td>
					<td><input type="text" name="uname" placeholder="User Name"
						value="" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="pass" placeholder="Password"
						value="" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" name="submit" /></td>
					<td><input type="reset" value="Clear" name="clear" /></td>
				</tr>
				<tr>
					<td colspan="2">Already registered!! <a href="index.jsp">Login
							Here</a></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" value=<%=results%> name="hidden" />
	</center>
</form>
</body>
</html>