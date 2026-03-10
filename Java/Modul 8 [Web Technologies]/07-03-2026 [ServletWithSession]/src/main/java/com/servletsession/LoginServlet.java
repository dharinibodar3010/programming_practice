package com.servletsession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Model m = new Model();
		m.setEmail(email);
		m.setPassword(password);
		
		Model m2 = Dao.logindata(m);
		
		if(m2 != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("project", true);
			
			response.sendRedirect("dasboard.html");
		}
		else
		{
			out.print("Login Fail");
		}
	}

}
