
/*Write a servlet program and override all life cycle methods to log messages when each
method is called.
*/

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("Servlet Initialized (init method called)");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Service Method Called");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Servlet Life Cycle Example</h2>");
        out.println("Check server console for lifecycle messages.");
        out.println("</body></html>");
    }

    public void destroy() {
        System.out.println("Servlet Destroyed (destroy method called)");
    }
}