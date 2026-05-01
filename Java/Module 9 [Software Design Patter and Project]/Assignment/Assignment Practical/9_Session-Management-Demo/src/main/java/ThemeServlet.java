

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ThemeServlet")
public class ThemeServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String theme = request.getParameter("theme");
		
		Cookie c = new Cookie("theme", theme);
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		response.sendRedirect("theme.jsp");
	}

}
