import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) 
        {
            resp.sendRedirect("index.html");
            return;
        }

        String id = req.getParameter("id");
        if(id == null) 
        {
            out.print("<h2>ID not found</h2>");
            return;
        }

        int myid = Integer.parseInt(id);
        StudentModel m = Dao.getrecord(myid);

        if(m == null) 
        {
            out.print("<h2>Record not found</h2>");
            return;
        }

        out.print("<html><head><title>Edit Student</title></head><body>");
        
        out.print("<h2>Edit Student Profile</h2>");

        out.print("<form action='UpdateServlet' method='post'>");

        out.print("<input type='hidden' name='id' value='"+m.getId()+"'>");

        out.print("Name: <input type='text' name='sname' value='"+m.getSname()+"'><br><br>");
        
        out.print("Surname: <input type='text' name='ssurname' value='"+m.getSsurname()+"'><br><br>");
        
        out.print("Stream: <input type='text' name='stream' value='"+m.getStream()+"'><br><br>");
        
        out.print("City: <input type='text' name='city' value='"+m.getCity()+"'><br><br>");
        
        out.print("Education Status: <input type='text' name='edu' value='"+m.getEdu()+"'><br><br>");
        
        out.print("Percentage: <input type='text' name='percentage' value='"+m.getPercentage()+"'><br><br>");
        
        out.print("Technology: <input type='text' name='ptech' value='"+m.getPtech()+"'><br><br>");
        
        out.print("Gender: <input type='text' name='gender' value='"+m.getGender()+"'><br><br>");
        
        out.print("Fees: <input type='number' name='fees' value='"+m.getFees()+"'><br><br>");

        
        out.print("<input type='submit' value='Update Student Data'>");
        
        out.print("</form>");
        
        out.print("</body></html>");
    }
}