import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SendOtpServlet")
public class SendOtpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2. Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // 3. Store in session
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("password", password);
        session.setAttribute("otp", otp);

        // 4. Send Email using EmailUtil
        try {
            EmailUtil.sendEmail(email, "OTP Verification",
                "Hello " + name + ",\n\nYour OTP for account verification is: " + otp +
                "\n\nThis OTP is valid for this session only.\n\nDo not share it with anyone.");

            System.out.println("OTP sent to: " + email + " | OTP: " + otp);

            // 5. Redirect to OTP verification page
            response.sendRedirect("otp.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            // On failure, show error and go back
            response.setContentType("text/html");
            response.getWriter().println(
                "<html><body style='font-family:sans-serif;text-align:center;padding:40px;'>" +
                "<h2 style='color:red;'>❌ Email sending failed!</h2>" +
                "<p>" + e.getMessage() + "</p>" +
                "<a href='index.jsp'>← Go Back</a>" +
                "</body></html>"
            );
        }
    }
}