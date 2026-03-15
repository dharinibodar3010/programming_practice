<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="c" class="com.bean.Cube" />

	<% int i1 = c.cube(5); 

	out.print("Cube of 5 is = "+i1);

%>
</body>
</html>