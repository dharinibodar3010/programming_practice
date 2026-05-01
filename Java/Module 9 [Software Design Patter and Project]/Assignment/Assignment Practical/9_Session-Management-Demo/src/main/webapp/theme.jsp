<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="jakarta.servlet.http.Cookie"  %>
    
<%
	String theme = "light";
	
	Cookie cookies[] = request.getCookies();
	
	if(cookies !=null)
	{
		for(Cookie c : cookies)
		{
			if(c.getName().equals("theme"))
			{
				theme = c.getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color:<%= theme.equals("dark")?"white":"black" %>">

	<h2>Theme Page</h2>
	
	<form action="ThemeServlet" method="post">
	
		<select name="theme">
			<option value="light">Light</option>
			<option value="dark">Dark</option>
		</select>
		
		<button type="submit">Save</button>
	
	</form>
	
	<a href="welcome.jsp">Back</a>

</body>
</html>