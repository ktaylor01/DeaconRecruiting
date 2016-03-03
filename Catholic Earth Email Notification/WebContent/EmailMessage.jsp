<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.CE.eNotificatioin.EmailContact"%>
<%@page import="com.CE.eNotificatioin.EmailContactDAO"%>
<%@page import="com.CE.eNotificatioin.SendEmail"%>
<%@page import="com.CE.eNotificatioin.SendEmailDAO"%>
<%@page import="com.CE.eNotificatioin.DBConnection"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="jsFile.js"></script>
<title>Email Message</title>
</head>
<body onload="displayResults()">
	<h2>Create an Email Message</h2>

	<%
		String sender = "kellytaylor644@gmail.com";
		int results = 0;

		if (request.getParameter("submit") != null) {
			String subject = new String();
			String text = new String();

			if (request.getParameter("subject") != null) {
				subject = request.getParameter("subject");
			}
			if (request.getParameter("textbox") != null) {
				text = request.getParameter("textbox");
			}

			results = 1;
			SendEmail email = new SendEmail();
			email.setSubject(subject);
			email.setSender(sender);
			email.setMessage(text);

			results = SendEmailDAO.sendGroupEmail(email);
		}
	%>
	<form name="myform2" action="EmailMessage.jsp" method="post">

		Subject: <br> <input type="text" name="subject"
			placeholder="Subject" value=""></input> <br> Message: <br>
		<textarea name="textbox" cols="50" rows="5">
		
		</textarea>
		<br> <input type="submit" name="submit" value="Send"> <input
			type="reset" name="reset" value="Reset"> <input type="hidden"
			value=<%=results%> name="hidden" />


	</form>
	<br>
	<a href="EmailForm.jsp">Back to Contact registration form</a>
</body>
</html>