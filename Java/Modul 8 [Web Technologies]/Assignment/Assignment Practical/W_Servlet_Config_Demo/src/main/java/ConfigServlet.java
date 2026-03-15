
/*Write a servlet that uses ServletConfig to fetch initialization parameters.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConfigServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletConfig config = getServletConfig();

        String user = config.getInitParameter("username");
        String pass = config.getInitParameter("password");

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>ServletConfig Example</h2>");
        out.println("Username: " + user + "<br>");
        out.println("Password: " + pass);
        out.println("</body>");
        out.println("</html>");
    }
}