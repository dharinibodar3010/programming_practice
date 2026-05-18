<%@page import="com.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    	Student s = (Student)request.getAttribute("student");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
</head>
<body>

	<h1>Edit Student</h1>
	
	<form action="../update">
	
	<!-- id -->
	<input type="hidden" name="id" value="<%=s.getId()%>"><br><br>
	
	Name:
	<input type="text" name="name" value="<%=s.getName()%>"><br><br>
	
	Email:
	<input type="email" name="email" value="<%=s.getEmail()%>"><br><br>
	
	City:
	<input type="text" name="city" value="<%=s.getCity()%>"><br><br>
	
	<input type="submit" value="update">
	
	</form>

</body>
</html>