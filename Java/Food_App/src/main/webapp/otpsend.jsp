<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.util.EmailUtil" %>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sending OTP - Foodie</title>
</head>
<body>
<%
    String email = request.getParameter("email");
    if (email == null || email.isEmpty()) {
        response.sendRedirect("register.jsp");
        return;
    }

    // Generate 6-digit OTP
    Random rand = new Random();
    int otpValue = 100000 + rand.nextInt(900000);
    String otp = String.valueOf(otpValue);

    // Store in session
    session.setAttribute("otp", otp);
    session.setAttribute("otpTime", System.currentTimeMillis());
    session.setAttribute("regEmail", email);

    // Print to console for testing
    System.out.println("-------------------------");
    System.out.println("OTP FOR " + email + " : " + otp);
    System.out.println("-------------------------");

    final String fEmail = email;
    final String fOtp = otp;
    final String smtpHost = getServletContext().getInitParameter("smtpHost");
    final String smtpPort = getServletContext().getInitParameter("smtpPort");
    final String fromEmail = getServletContext().getInitParameter("user");
    final String emailPassword = getServletContext().getInitParameter("pass");

    // Send OTP email in background thread — redirect happens immediately
    new Thread(() -> {
        String subject = "Your OTP for Foodie Registration";
        String body = "<div style='font-family: Arial; max-width: 500px; border: 1px solid #eee; border-radius: 8px; overflow: hidden;'>" +
            "<div style='background: #FF6B35; color: white; padding: 20px; text-align: center;'>" +
            "<h2>Foodie Account Verification</h2>" +
            "</div>" +
            "<div style='padding: 30px; text-align: center;'>" +
            "<p>Use the following OTP to verify your account:</p>" +
            "<h1 style='color: #FF6B35; letter-spacing: 10px; font-size: 3rem;'>" + fOtp + "</h1>" +
            "<p style='color: #888;'>This OTP is valid for <b>2 minutes</b> only.</p>" +
            "<br><p>Happy Eating!<br><b>The Foodie Team</b></p>" +
            "</div></div>";
        EmailUtil.sendEmail(fEmail, subject, body, fromEmail, emailPassword, smtpHost, smtpPort);
    }).start();

    // Store registration data in session BEFORE redirect
    session.setAttribute("regFullname", request.getParameter("fullname"));
    session.setAttribute("regPhone", request.getParameter("phone"));
    session.setAttribute("regPassword", request.getParameter("password"));

    response.sendRedirect("otpcheck.jsp");
%>
</body>
</html>
