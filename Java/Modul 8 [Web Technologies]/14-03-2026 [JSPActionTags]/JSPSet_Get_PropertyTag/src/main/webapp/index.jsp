

<!-- setProperty tag will be used to set data of bean class 
getProperty tag will be used to get data of bean class  -->



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
<jsp:useBean id="u" class="com.bean.User">
</jsp:useBean>		
<jsp:setProperty property="name" name="u" value="Bill"/>
First Name : <jsp:getProperty property="name" name="u"/>
</body>
</html>