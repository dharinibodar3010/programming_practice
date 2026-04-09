
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.dao.Dao;

@WebServlet("/AdminReplyServlet")
public class AdminReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String reply = request.getParameter("reply");
		
		if(idStr != null && reply != null) {
			int id = Integer.parseInt(idStr);
			Dao.updateContactReply(id, reply);
		}
		
		response.sendRedirect("admin_queries.jsp");
	}

}
