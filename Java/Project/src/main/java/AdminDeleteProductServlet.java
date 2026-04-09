
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.dao.Dao;

@WebServlet("/AdminDeleteProductServlet")
public class AdminDeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Dao.deleteProduct(id);
		}
		response.sendRedirect("admin_view_products.jsp");
	}

}
