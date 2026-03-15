
/*Lab: Write a simple Java servlet that accepts parameters from a user and displays a response.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");
        String age = request.getParameter("age");

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>User Details</h2>");
        out.println("Name: " + name + "<br>");
        out.println("Age: " + age);
        out.println("</body>");
        out.println("</html>");
    }
}