package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        if (username != null && !username.trim().isEmpty()) {
            // Simulate checking username availability (e.g., from a database)
            // Here, we hardcode some taken usernames for demonstration
            if (username.equalsIgnoreCase("admin") || username.equalsIgnoreCase("test") || username.equalsIgnoreCase("user")) {
                out.print("<span style='color:red'>✖ Username is already taken!</span>");
            } else {
                out.print("<span style='color:green'>✔ Username is available.</span>");
            }
        } else {
            out.print("");
        }
    }
}
