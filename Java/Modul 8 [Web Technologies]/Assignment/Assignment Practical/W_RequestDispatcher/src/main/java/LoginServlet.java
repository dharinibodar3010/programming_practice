
/*Create a login form in JSP, send the data to a servlet, and use RequestDispatcher to
forward or include a response based on input validity.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hard-coded validation example
        if("admin".equals(username) && "1234".equals(password)) 
        {
            // Forward to welcome page
            RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
            rd.forward(request, response);
        } 
        else 
        {
            // Include error message and show login page again
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<p style='color:red'>Invalid username or password!</p>");
            
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
        }
    }
}