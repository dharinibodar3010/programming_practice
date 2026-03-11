import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) 
        {
            resp.sendRedirect("index.html");
            return;
        }

		int id = Integer.parseInt(req.getParameter("id"));

		String sname = req.getParameter("sname");
		String ssurname = req.getParameter("ssurname");
		String stream = req.getParameter("stream");
		String city = req.getParameter("city");
		String edu = req.getParameter("edu");
		String percentage = req.getParameter("percentage");
		String ptech = req.getParameter("ptech");
		String gender = req.getParameter("gender");

		int fee = Integer.parseInt(req.getParameter("fees"));

		StudentModel m = new StudentModel();

		m.setId(id);
		m.setSname(sname);
		m.setSsurname(ssurname);
		m.setStream(stream);
		m.setCity(city);         
		m.setEdu(edu);
		m.setPercentage(percentage);
		m.setPtech(ptech);
		m.setGender(gender);
		m.setFees(fee);

		int status = Dao.updatedata(m);

		if(status > 0)
		{
			resp.sendRedirect("ViewServlet");   
		}
		else
		{
			out.print("<h2>Update Failed</h2>");
		}
	}
}