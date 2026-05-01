<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="result.jsp" method="post">
	
	<input type="hidden" name="name" value="<%= request.getParameter("name") %>" />
	
	Email: <input type="text" name="email"/>
	
	<button type="submit">Submit</button>
	
	</form>

</body>
</html>