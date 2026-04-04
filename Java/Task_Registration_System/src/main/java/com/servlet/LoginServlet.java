package com.servlet;

import java.io.IOException;

import com.dao.Dao;
import com.emailauth.EmailUtility;
import com.model.UsersModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UsersModel m = new UsersModel();
		m.setEmail(email);
		m.setPassword(password);

		UsersModel user = Dao.login(m);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			try {

				String host = getServletContext().getInitParameter("host");
				String port = getServletContext().getInitParameter("port");
				String mailUser = getServletContext().getInitParameter("user");
				String mailPass = getServletContext().getInitParameter("pass");

				String subject = "Security Alert: New Login Detected!";

		        String message = "<div style='font-family: Arial, sans-serif; border: 1px solid #ddd; padding: 20px; border-radius: 10px; max-width: 500px;'>" +
		                             "<h2 style='color: #2c3e50;'>New User Login</h2>" +
		                             "<p>Hello <b>Admin</b>,</p>" +
		                             "<p>A user has successfully logged into the <b>E-Learning System</b>. Here are the details:</p>" +
		                             "<table style='width: 100%; border-collapse: collapse;'>" +
		                             "<tr><td style='padding: 8px; border-bottom: 1px solid #eee;'><b>User Name:</b></td><td style='padding: 8px; border-bottom: 1px solid #eee;'>" + user.getName() + "</td></tr>" +
		                             "<tr><td style='padding: 8px; border-bottom: 1px solid #eee;'><b>Email ID:</b></td><td style='padding: 8px; border-bottom: 1px solid #eee;'>" + user.getEmail() + "</td></tr>" +
		                             "<tr><td style='padding: 8px; border-bottom: 1px solid #eee;'><b>Login Time:</b></td><td style='padding: 8px; border-bottom: 1px solid #eee;'>" + new java.util.Date() + "</td></tr>" +
		                             "<tr><td style='padding: 8px; border-bottom: 1px solid #eee;'><b>Status:</b></td><td style='padding: 8px; border-bottom: 1px solid #eee;'><span style='background: #27ae60; color: white; padding: 2px 8px; border-radius: 5px;'>Success</span></td></tr>" +
		                             "</table>" +
		                             "<p style='margin-top: 20px; color: #7f8c8d; font-size: 12px;'>This is an automated security notification from your system.</p>" +
		                             "</div>";

				EmailUtility.sendEmail(host, port, mailUser, mailPass, mailUser, subject, message);

			} catch (Exception e) {
				e.printStackTrace();
			}

			response.sendRedirect("dashboard.jsp");
		} else {
			request.setAttribute("error", "Invalid Email or Password!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}