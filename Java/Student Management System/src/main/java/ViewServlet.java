
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Session માટે જરૂરી

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet 
{
    @Override
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

        List<StudentModel> list = Dao.viewdata();

        out.print("<html><head>");
        out.print("<title>Student Management Table</title>");
        
        // CSS style
        out.print("<style>");
        out.print("body { font-family: Arial, sans-serif; background-color: #f4f7f6; padding: 20px; }");
        out.print(".header-box { display: flex; justify-content: space-between; align-items: center; background: white; padding: 15px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin-bottom: 20px; }");
        out.print(".btn-add { background-color: #3b82f6; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px; font-weight: bold; }");
        out.print(".btn-logout { background-color: #ef4444; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px; font-weight: bold; }");
        out.print("table { width: 100%; border-collapse: collapse; background: white; }");
        out.print("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
        out.print("th { background-color: #1e293b; color: white; }");
        out.print("tr:nth-child(even) { background-color: #f9fafb; }");
        out.print("tr:hover { background-color: #f1f5f9; }");
        out.print("</style>");
        out.print("</head><body>");

        out.print("<h2 style='text-align:center; color: #1e293b;'>Student Records</h2>");

        // Logout and Add Data 
        out.print("<div class='header-box'>");
            out.print("<a href='dashboard.html' class='btn-add'>+ Add New Student</a>");
            out.print("<a href='LogoutServlet' class='btn-logout'>Logout</a>");
        out.print("</div>");

        // Table
        out.print("<table>");
        out.print("<tr>");
        out.print("<th>Id</th><th>Name</th><th>Surname</th><th>Stream</th><th>City</th><th>Education</th><th>Percentage</th><th>Tech</th><th>Gender</th><th>Fees</th><th>Edit</th><th>Delete</th>");
        out.print("</tr>");

        // Display data using loop
        for(StudentModel m : list) 
        {
            out.print("<tr>");
            out.print("<td>" + m.getId() + "</td>");
            out.print("<td>" + m.getSname() + "</td>");
            out.print("<td>" + m.getSsurname() + "</td>");
            out.print("<td>" + m.getStream() + "</td>");
            out.print("<td>" + m.getCity() + "</td>");
            out.print("<td>" + m.getEdu() + "</td>");
            out.print("<td>" + m.getPercentage() + "</td>");
            out.print("<td>" + m.getPtech() + "</td>");
            out.print("<td>" + m.getGender() + "</td>");
            out.print("<td>" + m.getFees() + "</td>");
            out.print("<td><a href='EditServlet?id=" + m.getId() + "' style='color:#3b82f6;'>Edit</a></td>");
            out.print("<td><a href='DeleteServlet?id=" + m.getId() + "' style='color:#ef4444;'>Delete</a></td>");
            out.print("</tr>");
        }
        
        out.print("</table>");
        out.print("</body></html>");
    }
}