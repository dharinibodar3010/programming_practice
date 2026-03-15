
/*Use ServletContext to share data across multiple servlets.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetContextServlet")
public class GetContextServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get ServletContext
        ServletContext context = getServletContext();

        // Get shared data
        String appName = (String) context.getAttribute("appName");
        String version = (String) context.getAttribute("version");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Data from ServletContext</h2>");
        out.println("App Name: " + appName + "<br>");
        out.println("Version: " + version);
        out.println("</body></html>");
    }
}