
/*lab 7:Create a Java servlet that: 
   		o Displays the HTTP request headers. 
   		o Sends an HTTP response with custom headers.*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/HeaderServlet")
public class HeaderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        // Custom Response Headers
        response.setHeader("Custom-Header", "ServletExample");
        response.setHeader("Developer", "Java Servlet");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>HTTP Headers</title></head>");
        out.println("<body>");

        out.println("<h2>HTTP Request Headers</h2>");

        Enumeration<String> headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements())
        {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            out.println("<b>" + headerName + "</b> : " + headerValue + "<br>");
        }

        out.println("<h3>Custom Response Headers Sent</h3>");
        out.println("Custom-Header : ServletExample <br>");
        out.println("Developer : Java Servlet");

        out.println("</body>");
        out.println("</html>");
    }
}