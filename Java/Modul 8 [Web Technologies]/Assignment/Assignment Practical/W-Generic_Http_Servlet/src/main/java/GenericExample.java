/*Create a Java servlet program using both GenericServlet and HttpServlet and
compare their implementation.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/GenericExample")
public class GenericExample extends GenericServlet {

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>GenericServlet Example</h2>");
        out.println("Hello " + name);
        out.println("</body>");
        out.println("</html>");
    }
}