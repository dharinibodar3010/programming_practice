
/*Create multiple servlets that fetch shared data from web.xml using ServletContext*/

package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AboutServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

ServletContext context = getServletContext();
String pName = context.getInitParameter("projectName");

response.setContentType("text/html");
PrintWriter out = response.getWriter();

out.println("<html><body>");
out.println("<h2>Project: " + pName + "</h2>");
out.println("<p>This is the About Page.</p>");
out.println("</body></html>");
}
}