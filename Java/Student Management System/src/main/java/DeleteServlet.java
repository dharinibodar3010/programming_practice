
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) 
        {
            resp.sendRedirect("index.html");
            return;
        }
        
		try
		{
			String id = req.getParameter("id");

			if(id != null)
			{
				int myid = Integer.parseInt(id);
				Dao.deletedata(myid);
			}

			resp.sendRedirect("ViewServlet");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}