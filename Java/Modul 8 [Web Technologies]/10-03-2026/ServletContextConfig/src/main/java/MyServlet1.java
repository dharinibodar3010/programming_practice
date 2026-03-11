

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Servlet implementation class MyServlet1
 */ 

@WebServlet("/MyServlet1")
public class MyServlet1 extends HttpServlet 
{   
	private static final long serialVersionUID = 1L;
	
	String email, driver;
	ServletConfig config;
	ServletContext context;
	
	public void init(ServletConfig config) throws ServletException
	{
		this.config = config;
		this.context = config.getServletContext();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		email = config.getInitParameter("email");
		System.out.println(email);
		
		driver = context.getInitParameter("driver");
		System.out.println(driver);
	}

}
