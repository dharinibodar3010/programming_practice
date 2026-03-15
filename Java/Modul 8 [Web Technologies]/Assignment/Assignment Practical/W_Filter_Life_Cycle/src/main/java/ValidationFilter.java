/*Implement a filter to perform server-side validation of user input.*/

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/LoginServlet")
public class ValidationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(username == null || username.isEmpty() || password == null || password.isEmpty())
		{
			request.setAttribute("error","All fields are required");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
		}
		else
		{
			chain.doFilter(request,response);
		}
	
	}

}