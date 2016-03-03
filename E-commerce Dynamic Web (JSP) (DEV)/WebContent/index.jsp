<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.mypkg.DBConnection"%>
<%@ page import="com.mypkg.CustomerDOA"%>
<%@ page import="com.mypkg.Customer"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>

<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="JSFile.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>

	<h1>User login</h1>
	<form method="post" action="index.jsp">
		<center>
			<table border="1" width="30%" cellpadding="3">
				<thead>
					<tr>
						<th colspan="2">Login Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>User Name</td>
						<td><input type="text" name="uname" value="" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="pass" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="submit" /></td>
						<td><input type="reset" value="Reset" /></td>
					</tr>
					<tr>
						<td colspan="2">Yet Not Registered!! <a href="reg.jsp">Register
								Here</a></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	<!-- ============================ JAVA CODE  =============================== -->
	<%
		if (request.getParameter("submit") != null) {
			String userid = new String();
			String password = new String();
			if (request.getParameter("uname") != null) {
				userid = request.getParameter("uname");
			}
			if (request.getParameter("pass") != null) {
				password = request.getParameter("pass");
			}
			//ResultSet results = CustomerDOA.userLogin(userid, password);
			List<Customer> list = new ArrayList<Customer>();
			list = CustomerDOA.userLogin(userid);
			
		
	%>
	<!-- ========================= END OF JAVA CODE  ============================ -->
	<!-- ======= iterate through array comparing password to validate user  ===== -->
	
	
	<table id="contactOutput" align = "center" width="600" border="1">
		
		<tbody>
			<tr>
			<th width="98"><div align="center">WELCOME</div></th>
			</tr>
			<%
			for (Customer e : list) {
				if(e.getPword().equals(password))
			%>
			<tr>
			<td><%=userid%></div></td>
			</tr>
			<%
				}
			%>
			<%
				}
			%>
		</tbody>
	</table>
	
	
	
	
	
	
	
	
	<a href='index.jsp'>try again</a>
</body>
</html>