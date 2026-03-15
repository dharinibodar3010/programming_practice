/*

forward tag will be used to forward data to other jsp page   

*/
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:forward page="hi.jsp">
	<jsp:param value="I_Like_Java_Programming" name="like"/>
</jsp:forward>
</body>
</html>