

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		
		HttpSession session = request.getSession();
		session.setAttribute("user", name);;
		
		// URL Rewriting
		String url = response.encodeURL("welcome.jsp");
		response.sendRedirect(url);
		
	}

}
