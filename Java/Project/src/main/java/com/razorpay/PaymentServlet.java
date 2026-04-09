
package com.razorpay;

import java.io.IOException;

import com.dao.Dao;
import com.model.PaymentModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String paymentId = request.getParameter("razorpay_payment_id");
			String orderId = request.getParameter("razorpay_order_id");
			String signature = request.getParameter("razorpay_signature");
			String cartIds = request.getParameter("cartIds");

			if (paymentId != null && !paymentId.trim().isEmpty() && !paymentId.equals("undefined")) {

				PaymentModel m = new PaymentModel();
				m.setPaymentId(paymentId);
				m.setOrderId((orderId != null && !orderId.trim().isEmpty()) ? orderId : "undefined");
				m.setSignature((signature != null && !signature.trim().isEmpty()) ? signature : "undefined");

				int status = Dao.savePayment(m);
				if (status > 0) {

					if (cartIds != null && !cartIds.isEmpty()) {

						String[] ids = cartIds.split(",");

						for (String id : ids) {

							if (!id.trim().isEmpty()) {
								Dao.deletefromcart(Integer.parseInt(id));
							}
						}
					}
					
					try {
						String userEmailStr = (String) request.getSession().getAttribute("email");
						String host = getServletContext().getInitParameter("host");
						String port = getServletContext().getInitParameter("port");
						String emailUser = getServletContext().getInitParameter("user");
						String emailPass = getServletContext().getInitParameter("pass");
						String adminEmail = emailUser; // Admin receives the notification
						String subject = "New Successful Payment Alert! - " + paymentId;
						
						String content = "<div style='font-family: Arial, sans-serif; background-color: #f4f6f9; padding: 30px;'>" +
						                 "<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.1);'>" +
						                 "<div style='background: linear-gradient(135deg, #28a745 0%, #218838 100%); padding: 30px; text-align: center;'>" +
						                 "<h1 style='color: #ffffff; margin: 0; font-size: 28px;'>Payment Received! 🎉</h1>" +
						                 "</div>" +
						                 "<div style='padding: 30px;'>" +
						                 "<h2 style='color: #333333; font-size: 22px; margin-top: 0;'>Hello Admin,</h2>" +
						                 "<p style='color: #555555; font-size: 16px; line-height: 1.5;'>A new payment was just completed successfully by a user on your e-commerce platform.</p>" +
						                 "<div style='background-color: #f8f9fa; border-left: 6px solid #28a745; padding: 20px; margin: 25px 0; border-radius: 4px;'>" +
						                 "<p style='margin: 10px 0; font-size: 16px;'><strong style='color: #032154; width: 120px; display: inline-block;'>Payment ID:</strong> <span style='font-family: monospace;'>" + paymentId + "</span></p>" +
						                 "<p style='margin: 10px 0; font-size: 16px;'><strong style='color: #032154; width: 120px; display: inline-block;'>User Email:</strong> <span>" + (userEmailStr != null ? userEmailStr : "Guest") + "</span></p>" +
						                 "<p style='margin: 10px 0; font-size: 16px;'><strong style='color: #032154; width: 120px; display: inline-block;'>Cart Items ID:</strong> <span>" + cartIds + "</span></p>" +
						                 "</div>" +
						                 "</div>" +
						                 "<div style='background-color: #f1f1f1; padding: 20px; text-align: center; color: #888888; font-size: 13px;'>" +
						                 "<p style='margin: 0;'>Automated Payment Notification</p>" +
						                 "</div>" +
						                 "</div>" +
						                 "</div>";

						com.emailauth.EmailUtility.sendEmail(host, port, emailUser, emailPass, adminEmail, subject, content);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// Payment is verified
					 response.sendRedirect("index.jsp");

				} else {
					response.getWriter().write("Payment verification failed");
				}
			} else {
			    response.getWriter().write("Payment failed: Invalid Payment ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Payment failed: " + e.getMessage());
		}
	}
}