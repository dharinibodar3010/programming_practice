
/*1. Create a registration form in JSP.
  2. Send form data to a servlet, process it, and forward the response back to a JSP using
     RequestDispatcher.*/

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Optional: simple validation
        String message;
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            message = "All fields are required!";
        } else {
            message = "Registration successful!";
        }

        // Set attribute to send to JSP
        request.setAttribute("message", message);
        request.setAttribute("name", name);
        request.setAttribute("email", email);

        // Forward to response JSP
        RequestDispatcher rd = request.getRequestDispatcher("register-response.jsp");
        rd.forward(request, response);
    }
}