<%@page import="com.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="sm" class="com.usermodel.SignupModel"/>
<jsp:setProperty property="*" name="sm"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    int status = Dao.signupdata(sm);
    if(status > 0) {
        response.sendRedirect("login.jsp");
    } else {
        out.print("Signup failed. Please try again.");
    }
%>

</body>
</html>