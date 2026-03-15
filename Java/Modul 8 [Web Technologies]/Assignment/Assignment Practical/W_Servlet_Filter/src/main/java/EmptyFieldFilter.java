
/*Write a filter that checks whether form input fields are empty. If they are, 
  forward back to the input form; otherwise, proceed with the request.*/

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Apply filter to the servlet handling the form submission

@WebFilter("/SubmitFormServlet")
public class EmptyFieldFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Check if any field is empty
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            // Set an error message
            req.setAttribute("errorMessage", "All fields are required!");
            
            // Forward back to the input form
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } else {
            // All fields filled; proceed with the request
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Optional cleanup
    }
}