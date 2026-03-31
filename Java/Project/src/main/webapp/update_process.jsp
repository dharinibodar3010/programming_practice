<%@page import="com.model.SignupModel"%>
<%@page import="com.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    // Form mathi data fetch karvo
    String name = request.getParameter("fullname");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");

    // Model class ma data set karvo
    SignupModel m = new SignupModel();
    m.setFullname(name);
    m.setEmail(email);
    m.setPhone(phone);

    // Database ma update karva mate Dao ni method call karvi
    int status = Dao.updateProfile(m);

    if(status > 0) 
    {
        // Dashboard par navu naam dekhay te mate session update karvu
        session.setAttribute("name", name);
        session.setAttribute("num", phone);
        
        response.sendRedirect("dashbaord.jsp");
    } 
    else 
    {
        out.println("<script>alert('Something went wrong!'); window.location='profile.jsp';</script>");
    }
%>