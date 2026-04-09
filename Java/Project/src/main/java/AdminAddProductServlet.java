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

@WebServlet("/AdminAddProductServlet")
@MultipartConfig(maxFileSize = 16177216) // Max 16MB image size
public class AdminAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n = request.getParameter("p_name");
		String p = request.getParameter("p_price");
		String d = request.getParameter("p_des");
		String c = request.getParameter("category");
		
		Part part = request.getPart("p_image");
		InputStream is = part.getInputStream();
		
		ProductModel m = new ProductModel();
		m.setP_name(n);
		m.setP_price(p);
		m.setP_des(d);
		m.setCategory(c);
		
		int status = Dao.addProduct(m, is);
		
		if(status > 0) {
			request.setAttribute("msg", "Product added successfully!");
		} else {
			request.setAttribute("error", "Failed to add product.");
		}
		
		request.getRequestDispatcher("admin_add_product.jsp").forward(request, response);
	}

}
