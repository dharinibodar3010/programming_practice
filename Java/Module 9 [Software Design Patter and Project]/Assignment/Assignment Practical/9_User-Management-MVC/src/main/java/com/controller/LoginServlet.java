package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.Dao;
import com.model.UserModel;

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
    	
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserModel um = new UserModel();
		um.setEmail(email);
		um.setPassword(password);
		
		UserModel um2 = Dao.login(um);
		System.out.println(um2);

        if(um2 != null) 
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", um2);
            response.sendRedirect("profile.jsp");
        } 
        else 
        {
            response.getWriter().println("Invalid Login");
        }
    }
}