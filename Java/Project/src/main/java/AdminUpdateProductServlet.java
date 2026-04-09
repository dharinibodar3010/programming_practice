import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.dao.Dao;
import com.model.ProductModel;

@WebServlet("/AdminUpdateProductServlet")
@MultipartConfig(maxFileSize = 16177216)
public class AdminUpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String n = request.getParameter("p_name");
		String p = request.getParameter("p_price");
		String d = request.getParameter("p_des");
		String c = request.getParameter("category");
		
		int id = Integer.parseInt(idStr);
		
		Part part = request.getPart("p_image");
		InputStream is = null;
		
		if(part != null && part.getSize() > 0) {
			is = part.getInputStream();
		}
		
		ProductModel m = new ProductModel();
		m.setId(id);
		m.setP_name(n);
		m.setP_price(p);
		m.setP_des(d);
		m.setCategory(c);
		
		int status = Dao.updateProduct(m, is);
		
		if(status > 0) {
			request.setAttribute("msg", "Product updated successfully!");
			request.getRequestDispatcher("admin_view_products.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Failed to update product.");
			request.getRequestDispatcher("admin_edit_product.jsp?id=" + id).forward(request, response);
		}
	}

}
