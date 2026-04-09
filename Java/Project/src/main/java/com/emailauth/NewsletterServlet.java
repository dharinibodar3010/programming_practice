package com.emailauth;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.dao.Dao;
import com.model.NewsletterModel;

@WebServlet("/NewsletterServlet")
public class NewsletterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");

        if(email != null && !email.isEmpty()) {

            NewsletterModel m = new NewsletterModel();
            m.setEmail(email);

            int status = Dao.newsletter(m);

            if(status > 0) {

                try {
                    ServletContext context = getServletContext();

                    String user = context.getInitParameter("user");
                    String pass = context.getInitParameter("pass");

                    // 1. Send Welcome Email to Subscriber
                    String subjectUser = "Newsletter Subscription - Webwing";
                    String messageUser = "<div style='font-family: Arial; border: 2px solid #6a5af9; padding: 20px; border-radius: 10px;'>" +
                            "<h2 style='color: #6a5af9;'>Welcome to Webwing!</h2>" +
                            "<p>Thank you for subscribing to our newsletter. You will receive the latest updates and offers directly in your inbox.</p>" +
                            "</div>";

                    EmailUtility.sendNewsletter(
                        "smtp.gmail.com",
                        "587",
                        user,
                        pass,
                        email,
                        subjectUser,
                        messageUser
                    );

                    // 2. Send Notification to Owner
                    String subjectOwner = "New Newsletter Subscriber!";
                    String messageOwner = "<div style='font-family: Arial; background: #f4f4f4; padding: 20px; border-radius: 10px;'>" +
                            "<h3 style='color: #333;'>Hello Owner,</h3>" +
                            "<p>A new user has subscribed to your newsletter.</p>" +
                            "<p><b>Subscriber Email: </b>" + email + "</p>" +
                            "</div>";

                    EmailUtility.sendEmail(
                        "smtp.gmail.com",
                        "587",
                        user,
                        pass,
                        user, // Owner email (same as sender)
                        subjectOwner,
                        messageOwner
                    );

                } catch(Exception e) {
                    e.printStackTrace();
                }

                out.println("<script>alert('Subscribed & Emails Sent!'); window.location='index.jsp';</script>");
            }

            // DUPLICATE
            else if(status == -1) {
                out.println("<script>alert('Already Subscribed!'); window.location='index.jsp';</script>");
            }

            // ERROR
            else {
                out.println("<script>alert('Database Error!'); window.location='index.jsp';</script>");
            }

        } else {
            out.println("<script>alert('Invalid Email!'); window.location='index.jsp';</script>");
        }
    }
}