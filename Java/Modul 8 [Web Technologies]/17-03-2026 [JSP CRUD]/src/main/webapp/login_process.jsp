<%@page import="com.dao.Dao"%>
<%@page import="com.usermodel.SignupModel"%>
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
    SignupModel user = Dao.checksignin(sm);
    if(user != null) {
        // Session માં યુઝરનું નામ સ્ટોર કરી શકાય
        session.setAttribute("user", user.getUsername());
        response.sendRedirect("index.jsp");
    } else {
        out.print("<script>alert('Invalid Email or Password'); window.location='login.jsp';</script>");
    }
%>

</body>
</html>