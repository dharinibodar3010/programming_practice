
/*Use ServletContext to share data across multiple servlets.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SetContextServlet")
public class SetContextServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get ServletContext
        ServletContext context = getServletContext();

        // Set data to share
        context.setAttribute("appName", "MyAwesomeApp");
        context.setAttribute("version", "1.0");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Data has been set in ServletContext!</h2>");
        out.println("<a href='GetContextServlet'>Go to GetContextServlet</a>");
        out.println("</body></html>");
    }
}