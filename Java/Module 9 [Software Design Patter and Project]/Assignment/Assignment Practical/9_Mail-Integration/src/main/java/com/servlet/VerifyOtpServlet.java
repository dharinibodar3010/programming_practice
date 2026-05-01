package com.servlet;

import java.io.IOException;

import com.dao.UserDao;
import com.model.UserModel;

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

		int userOtp = Integer.parseInt(request.getParameter("otp"));

		HttpSession session = request.getSession();
		int realOtp = (int) session.getAttribute("otp");

		if (userOtp == realOtp) {

			String name = (String) session.getAttribute("name");
			String email = (String) session.getAttribute("email");
			String password = (String) session.getAttribute("password");

			UserModel um = new UserModel();
			um.setName(name);
			um.setEmail(email);
			um.setPassword(password);

			UserDao.registerUser(um);

			response.getWriter().println("Registration Successful!");
		} else {
			response.getWriter().println("Invalid OTP!");
		}
	}
}