import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        StudentModel m = new StudentModel();
        m.setSname(request.getParameter("sname"));
        m.setSsurname(request.getParameter("ssurname"));
        m.setStream(request.getParameter("stream"));
        m.setCity(request.getParameter("city"));
        m.setEdu(request.getParameter("edu")); 
        m.setPercentage(request.getParameter("percentage"));
        m.setPtech(request.getParameter("ptech")); 
        m.setGender(request.getParameter("gender"));
        m.setFees(Integer.parseInt(request.getParameter("fees")));

        int status = Dao.savedata(m);

        if(status > 0)
        {
            response.sendRedirect("ViewServlet"); 
        } 
        else 
        {
            response.getWriter().print("Error in saving data!");
        }
    }
}