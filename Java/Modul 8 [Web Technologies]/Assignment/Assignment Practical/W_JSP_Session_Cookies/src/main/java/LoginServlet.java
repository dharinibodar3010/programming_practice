
/*Implement a login system in JSP and servlet that uses cookies and session tracking to
manage user authentication.*/

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hardcoded users (for demo)
    private final String USERNAME = "admin";
    private final String PASSWORD = "1234";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication check
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // 1. Create session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 2. Create a cookie (optional)
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60*60); // 1 hour
            response.addCookie(userCookie);

            // Redirect to welcome page
            response.sendRedirect("welcome.jsp");
        } else {
            // Authentication failed
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}