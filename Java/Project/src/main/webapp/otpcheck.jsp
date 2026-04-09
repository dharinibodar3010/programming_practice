<%@page import="com.dao.Dao"%>
<%@page import="com.model.SignupModel"%>
<%@page import="com.emailauth.EmailUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		String otp = request.getParameter("otp");
		String otp2 = request.getParameter("otp2");
		String otp3 = request.getParameter("otp3");
		String otp4 = request.getParameter("otp4");
		
		int otp11 = Integer.parseInt(otp);
		int otp22 = Integer.parseInt(otp2);
		int otp33 = Integer.parseInt(otp3);
		int otp44 = Integer.parseInt(otp4);
		
		int n1 = (int) session.getAttribute("n1");
		int n2 = (int) session.getAttribute("n2");
		int n3 = (int) session.getAttribute("n3");
		int n4 = (int) session.getAttribute("n4");
		
		 if(otp11==n1)
			{
				if(otp22==n2)
				{
					if(otp33==n3)
					{
						if(otp44==n4)
						{
							

							String fname = (String) session.getAttribute("fname");
							String email = (String)session.getAttribute("email");
							String phone = (String)session.getAttribute("phone");
							String pass = (String)session.getAttribute("pass");
							
							
							SignupModel m = new SignupModel();
							m.setFullname(fname);
							m.setEmail(email);
							m.setPhone(phone);
							m.setPassword(pass);
							
							
							
							int signupstatus = Dao.signup(m);
							
							if(signupstatus > 0) 
						    {
						        
						        session.setAttribute("project", true); 
						        session.setAttribute("email", email); 
						        session.setAttribute("name", fname); 
						        session.setAttribute("num", phone); 

						        // Send beautiful notification email to Admin
						        try {
						            String emailHost = application.getInitParameter("host");
						            String emailPort = application.getInitParameter("port");
						            String emailUser = application.getInitParameter("user");
						            String emailPass = application.getInitParameter("pass");
						            String adminEmail = emailUser; 
						            String subject = "New User Registration! - " + fname;
						            
						            String content = "<div style='font-family: Arial, sans-serif; background-color: #f4f6f9; padding: 30px;'>" +
						                             "<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.1);'>" +
						                             "<div style='background: linear-gradient(135deg, #032154 0%, #1e5799 100%); padding: 30px; text-align: center;'>" +
						                             "<h1 style='color: #f2db18; margin: 0; font-size: 28px;'>New Registration Alert!</h1>" +
						                             "</div>" +
						                             "<div style='padding: 30px;'>" +
						                             "<h2 style='color: #333333; font-size: 22px; margin-top: 0;'>Hello Admin,</h2>" +
						                             "<p style='color: #555555; font-size: 16px; line-height: 1.5;'>Great news! A new user has just signed up on your shopping platform. Here are their details:</p>" +
						                             "<div style='background-color: #f8f9fa; border-left: 6px solid #f2db18; padding: 20px; margin: 25px 0; border-radius: 4px;'>" +
						                             "<p style='margin: 10px 0; font-size: 16px;'><strong style='color: #032154; width: 80px; display: inline-block;'>Name:</strong> <span>" + fname + "</span></p>" +
						                             "<p style='margin: 10px 0; font-size: 16px;'><strong style='color: #032154; width: 80px; display: inline-block;'>Email:</strong> <span>" + email + "</span></p>" +
						                             "<p style='margin: 10px 0; font-size: 16px;'><strong style='color: #032154; width: 80px; display: inline-block;'>Phone:</strong> <span>" + phone + "</span></p>" +
						                             "</div>" +
						                             "</div>" +
						                             "<div style='background-color: #f1f1f1; padding: 20px; text-align: center; color: #888888; font-size: 13px;'>" +
						                             "<p style='margin: 0;'>This is an automated notification from your E-Commerce Project.</p>" +
						                             "</div>" +
						                             "</div>" +
						                             "</div>";

						            EmailUtility.sendEmail(emailHost, emailPort, emailUser, emailPass, adminEmail, subject, content);
						        } catch (Exception e) {
						            e.printStackTrace();
						        }

						        response.sendRedirect("dashbaord.jsp");
						    }
						    else 
						    {
						%>
						    <center>
						        <b><p style="color:red;font-family: sans-serif; font-size: 25px;">Signup Failed!! Please Try Again</p></b>
						    </center>
						<% 	
						    
								
							}
							
							}
							
							
						}
						
					}
				
			}
						
		 %>
	
	


</body>
</html>