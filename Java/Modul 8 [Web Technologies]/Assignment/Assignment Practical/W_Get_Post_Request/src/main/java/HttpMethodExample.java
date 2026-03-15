
/*Write a program using HttpServlet to handle HTTP-specific requests like GET and POST.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HttpMethodExample")
public class HttpMethodExample extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>GET Request</h2>");
        out.println("Hello " + name);
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>POST Request</h2>");
        out.println("Welcome " + name);
        out.println("</body>");
        out.println("</html>");
    }
}