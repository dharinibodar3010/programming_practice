package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

ServletContext context = getServletContext();
String email = context.getInitParameter("supportEmail");
String pName = context.getInitParameter("projectName");

response.setContentType("text/html");
PrintWriter out = response.getWriter();

out.println("<html><body>");
out.println("<h1>Contact Us - " + pName + "</h1>");
out.println("<p>Email us at: " + email + "</p>");
out.println("</body></html>");
}
}