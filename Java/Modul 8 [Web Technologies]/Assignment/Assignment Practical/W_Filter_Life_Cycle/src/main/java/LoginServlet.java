


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException 
	{
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(username.equals("admin") && password.equals("1234"))
		{
			out.println("<h2>Welcome Admin</h2>");
		}
		else
		{
			out.println("<h2>Invalid Username or Password</h2>");
		}
	
	}
}