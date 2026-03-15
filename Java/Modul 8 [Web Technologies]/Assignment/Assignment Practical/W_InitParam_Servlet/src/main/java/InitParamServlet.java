

/*Write a servlet to fetch and display initialization parameters from web.xml using
ServletConfig.*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/InitParamServlet")
public class InitParamServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config); // always call super
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletConfig config = getServletConfig();

        // Fetch init parameters
        String username = config.getInitParameter("username");
        String password = config.getInitParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>ServletConfig Initialization Parameters</h2>");
        out.println("Username: " + username + "<br>");
        out.println("Password: " + password);
        out.println("</body></html>");
    }
}