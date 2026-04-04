package com.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.dao.Dao;
import com.emailauth.EmailUtility;
import com.model.PaymentsModel;
import com.model.UsersModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsersModel user = (UsersModel) session.getAttribute("user");

        try {
            String paymentId = request.getParameter("razorpay_payment_id");
            String orderId = request.getParameter("razorpay_order_id");
            String amount = request.getParameter("amount");
            String enrollIdStr = request.getParameter("courseId"); 

            if (paymentId != null && !paymentId.isEmpty()) {
                
                
                PaymentsModel p = new PaymentsModel();
                p.setEnrollment_id(enrollIdStr); 
                p.setAmount(amount);
                
                Dao dao = new Dao();
                dao.makePayment(p);

               
                String userIdStr = String.valueOf(user.getId());
                Dao.updateStatusByUserId(userIdStr, enrollIdStr, "Paid");

                
                String host = getServletContext().getInitParameter("host");
                String port = getServletContext().getInitParameter("port");
                String mailUser = getServletContext().getInitParameter("user");
                String mailPass = getServletContext().getInitParameter("pass");

                String subject = "Payment Confirmed";
                String content = "Hi " + user.getName() + ", your payment of ₹" + amount + " was successful. ID: " + paymentId;

                EmailUtility.sendEmail(host, port, mailUser, mailPass, user.getEmail(), subject, content);

                
                response.sendRedirect("success.jsp?id=" + paymentId);
                
            } else {
                response.getWriter().write("Payment verification failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if (!response.isCommitted()) {
                response.getWriter().write("Payment failed: " + e.getMessage());
            }
        }
    }

    private String generateSignature(String orderId, String paymentId) throws NoSuchAlgorithmException {
        String key = "5fQw1gR0zy7150XHto7C4iND"; // your screte key
        String data = orderId + "|" + paymentId;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((key + "|" + data).getBytes());
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }
}