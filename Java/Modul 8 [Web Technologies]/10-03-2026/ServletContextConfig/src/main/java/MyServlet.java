import java.io.IOException;

/*

ServletConfig config;
	ServletConfig is used for one servlet
	Data shared with only one servlet and than you
	want to fetch it ServletConfig will be used
	
ServletContext context;
	ServletContext is used for all servlet
	Commonlly shared data can be fetched from ServletContext
	
*/

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	String email, driver;
	ServletConfig config;
	ServletContext context;
	
	public void init(ServletConfig confing) throws ServletException
	{
		this.config = confing;
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
