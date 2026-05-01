<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String name = request.getParameter("username");

    if (name == null || name.trim().isEmpty()) {
        out.print("Please enter a valid name.");
    } else {
        out.print("Hello, " + name + "! Welcome to JSP with AJAX.");
    }
%>