<%@page import="com.emailauth.EmailUtility"%>
<%@page import="com.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Processing Contact - WebWing</title>
</head>
<body>
<jsp:useBean id="m" class="com.model.ContactModel"/>
<jsp:setProperty property="*" name="m"/>

<%
		int status = Dao.contactinsert(m);
	
		if(status>0)
		{
			try {
                ServletContext context = getServletContext();
                String host = context.getInitParameter("host");
                String port = context.getInitParameter("port");
                String user = context.getInitParameter("user");
                String pass = context.getInitParameter("pass");

                String ownerSubject = "New Contact Message Received!";
                String ownerMessage = "<div style='font-family: Arial; background: #fff; padding: 20px; border: 1px solid #ddd; border-radius: 8px;'>" +
                        "<h3 style='color: #2c3e50;'>New Inquiry!</h3>" +
                        "<p><b>Full Name:</b> " + m.getFullname() + "</p>" +
                        "<p><b>Email:</b> " + m.getEmail() + "</p>" +
                        "<p><b>Phone:</b> " + m.getPhone() + "</p>" +
                        "<p><b>Feedback:</b> <br>" + m.getFeedback() + "</p>" +
                        "</div>";

                EmailUtility.sendEmail(host, port, user, pass, user, ownerSubject, ownerMessage);
                
            } catch(Exception e) {
                e.printStackTrace();
            }
			
			// Alert and redirect
			out.println("<script>alert('Message Sent Successfully!'); window.location='contact.jsp';</script>");
		}
		else
		{
			out.print("Not Able to Add Content");
		}
%>

</body>
</html>