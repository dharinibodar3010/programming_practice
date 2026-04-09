<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.removeAttribute("adminSession");
    session.invalidate();
    response.sendRedirect("admin_login.jsp");
%>
