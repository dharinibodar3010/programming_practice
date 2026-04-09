

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dao.Dao;
import com.model.AdminModel;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		
		AdminModel m = new AdminModel();
		m.setUsername(u);
		m.setPassword(p);
		
		AdminModel admin = Dao.adminLogin(m);
		
		if(admin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("adminSession", admin);
			response.sendRedirect("admin_dashboard.jsp");
		} else {
			request.setAttribute("error", "Invalid admin credentials!");
			request.getRequestDispatcher("admin_login.jsp").forward(request, response);
		}
	}

}
