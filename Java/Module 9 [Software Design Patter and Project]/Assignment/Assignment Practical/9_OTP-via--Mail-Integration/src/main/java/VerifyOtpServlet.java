import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/VerifyOtpServlet")
public class VerifyOtpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get OTP entered by user
        String userOtp = request.getParameter("otp");
        if (userOtp != null) {
            userOtp = userOtp.trim();
        }

        // 2. Get OTP stored in session
        HttpSession session = request.getSession();
        String systemOtp = (String) session.getAttribute("otp");

        System.out.println("User OTP entered : " + userOtp);
        System.out.println("System OTP (session): " + systemOtp);

        // 3. Check if session expired
        if (systemOtp == null) {
            response.setContentType("text/html");
            response.getWriter().println(
                "<html><body style='font-family:sans-serif;text-align:center;padding:40px;'>" +
                "<h2 style='color:orange;'>⚠️ Session Expired!</h2>" +
                "<p>Your OTP session has expired. Please register again.</p>" +
                "<a href='index.jsp'>← Back to Register</a>" +
                "</body></html>"
            );
            return;
        }

        // 4. Compare OTP
        if (userOtp != null && userOtp.equals(systemOtp)) {

            // OTP matched - remove OTP from session (security)
            session.removeAttribute("otp");

            // Redirect to success page (account created)
            response.sendRedirect("success.jsp");

        } else {
            // Wrong OTP - go back to otp.jsp with error
            response.setContentType("text/html");
            response.getWriter().println(
                "<html><body style='font-family:sans-serif;text-align:center;padding:40px;'>" +
                "<h2 style='color:red;'>❌ Invalid OTP!</h2>" +
                "<p>The OTP you entered is incorrect. Please try again.</p>" +
                "<a href='otp.jsp'>← Try Again</a>" +
                "</body></html>"
            );
        }
    }
}