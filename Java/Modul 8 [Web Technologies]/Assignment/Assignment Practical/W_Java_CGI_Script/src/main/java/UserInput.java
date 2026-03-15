
/*lab 9: Write a simple CGI script using Java to accept user input from a form and display it on a
		 webpage.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserInput extends HttpServlet {

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException 
	{
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		
		out.println("<h2>Hello "+name+"</h2>");
	}

}