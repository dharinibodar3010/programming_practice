<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.util.EmailUtil"%>
<%@ page import="com.dao.Dao"%>
<%
String message = "";
if ("POST".equalsIgnoreCase(request.getMethod())) {
    String enteredOtp = request.getParameter("enteredOtp");
    String sessionOtp = (String) session.getAttribute("otp");
    Long otpTime = (Long) session.getAttribute("otpTime");
    long currentTime = System.currentTimeMillis();

    if (sessionOtp == null || otpTime == null) {
        message = "No OTP found. Please register again.";
    } else if (currentTime - otpTime > 120000) {
        message = "OTP Expired! Please request a new one.";
        session.removeAttribute("otp");
    } else if (enteredOtp != null && enteredOtp.trim().equals(sessionOtp.trim())) {
        String fullname = (String) session.getAttribute("regFullname");
        String email    = (String) session.getAttribute("regEmail");
        String phone    = (String) session.getAttribute("regPhone");
        String password = (String) session.getAttribute("regPassword");
        try {
            Connection con = Dao.getconnect();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (fullname, email, phone, password) VALUES (?, ?, ?, ?)");
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);
            int status = ps.executeUpdate();
            if (status > 0) {
                final String smtpHost    = getServletContext().getInitParameter("smtpHost");
                final String smtpPort    = getServletContext().getInitParameter("smtpPort");
                final String fromEmail   = getServletContext().getInitParameter("user");
                final String emailPass   = getServletContext().getInitParameter("pass");
                final String fFullname   = fullname;
                final String fEmail      = email;
                final String fPhone      = phone;
                new Thread(new Runnable() {
                    public void run() {
                        String userSubject = "Welcome to Foodie Family!";
                        String userBody = "<div style='font-family:Arial;padding:20px;border:1px solid #eee;border-radius:10px;max-width:600px;'>"
                            + "<h2 style='color:#FF6B35;'>Hello " + fFullname + "!</h2>"
                            + "<p>Thanks for joining <b>Foodie</b>. Your account is now active!</p>"
                            + "<a href='http://localhost:8080/Food_App/login.jsp' style='background:#FF6B35;color:white;padding:12px 25px;text-decoration:none;border-radius:5px;display:inline-block;margin-top:15px;'>Start Ordering Now</a>"
                            + "<br><br><p>Happy Eating!<br><b>The Foodie Team</b></p></div>";
                        EmailUtil.sendEmail(fEmail, userSubject, userBody, fromEmail, emailPass, smtpHost, smtpPort);
                        String adminSubject = "New User Registered: " + fFullname;
                        String adminBody = "<div style='font-family:Arial;background:#f9f9f9;padding:20px;'>"
                            + "<h3>New User Registration Alert</h3>"
                            + "<ul><li><b>Name:</b> " + fFullname + "</li>"
                            + "<li><b>Email:</b> " + fEmail + "</li>"
                            + "<li><b>Phone:</b> " + fPhone + "</li></ul></div>";
                        EmailUtil.sendEmail(fromEmail, adminSubject, adminBody, fromEmail, emailPass, smtpHost, smtpPort);
                    }
                }).start();
                session.removeAttribute("otp");
                response.sendRedirect("login.jsp?msg=success");
                return;
            } else {
                message = "Registration failed. Please try again.";
            }
        } catch (Exception e) {
            message = "Registration failed: " + e.getMessage();
            e.printStackTrace();
        }
    } else {
        message = "Invalid OTP! Please try again.";
    }
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Verify OTP - Foodie</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>
.otp-inputs { display: flex; gap: 12px; justify-content: center; margin: 25px 0; }
.otp-inputs input { width: 50px; height: 60px; text-align: center; font-size: 1.8rem; font-weight: 700; border: 2px solid var(--border-color); border-radius: 10px; background: var(--bg-color); color: var(--text-dark); outline: none; transition: border-color 0.2s; }
.otp-inputs input:focus { border-color: var(--primary); box-shadow: 0 0 0 3px rgba(255,107,53,0.15); }
.otp-inputs input.filled { border-color: var(--primary); background: rgba(255,107,53,0.05); }
</style>
</head>
<body>

<div class="auth-container">
    <div class="auth-form-wrapper" style="width:100%;max-width:500px;margin:0 auto;height:100vh;display:flex;align-items:center;">
        <div class="auth-form glass-panel" style="width:100%;">
            <div style="text-align:center;margin-bottom:2rem;">
                <a href="index.jsp" class="logo" style="justify-content:center;font-size:2rem;">
                    <i class="fa-solid fa-utensils"></i> Foodie
                </a>
            </div>
            <h3>Verify Your Email</h3>
            <p>We've sent a 6-digit OTP to <b><%= session.getAttribute("regEmail") %></b></p>

            <% if (!message.isEmpty()) { %>
            <div style="background:rgba(220,53,69,0.1);color:#dc3545;padding:10px;border-radius:8px;margin-bottom:20px;font-size:0.9rem;text-align:center;border:1px solid rgba(220,53,69,0.2);">
                <i class="fa-solid fa-circle-exclamation" style="margin-right:6px;"></i><%= message %>
            </div>
            <% } %>

            <form method="post" onsubmit="return combineOtp()">
                <input type="hidden" name="enteredOtp" id="enteredOtp">
                <div class="otp-inputs">
                    <input type="text" maxlength="1" class="otp-box" autocomplete="off" inputmode="numeric">
                    <input type="text" maxlength="1" class="otp-box" autocomplete="off" inputmode="numeric">
                    <input type="text" maxlength="1" class="otp-box" autocomplete="off" inputmode="numeric">
                    <input type="text" maxlength="1" class="otp-box" autocomplete="off" inputmode="numeric">
                    <input type="text" maxlength="1" class="otp-box" autocomplete="off" inputmode="numeric">
                    <input type="text" maxlength="1" class="otp-box" autocomplete="off" inputmode="numeric">
                </div>
                <button type="submit" class="btn btn-primary auth-btn">Verify &amp; Signup</button>
            </form>

            <p style="text-align:center;margin-top:20px;">
                Didn't receive code?
                <a href="otpsend.jsp?email=<%= session.getAttribute("regEmail") %>" style="color:var(--primary);font-weight:600;">Resend OTP</a>
            </p>
        </div>
    </div>
</div>

<script>
var boxes = document.querySelectorAll('.otp-box');
for (var i = 0; i < boxes.length; i++) {
    (function(index) {
        boxes[index].addEventListener('input', function() {
            this.value = this.value.replace(/\D/g, '');
            if (this.value.length === 1) {
                this.classList.add('filled');
                if (index < boxes.length - 1) boxes[index + 1].focus();
            }
        });
        boxes[index].addEventListener('keydown', function(e) {
            if (e.key === 'Backspace' && this.value === '' && index > 0) {
                boxes[index - 1].focus();
                boxes[index - 1].classList.remove('filled');
            }
        });
    })(i);
}
function combineOtp() {
    var otp = '';
    for (var i = 0; i < boxes.length; i++) otp += boxes[i].value;
    document.getElementById('enteredOtp').value = otp;
    return otp.length === 6;
}
</script>
</body>
</html>