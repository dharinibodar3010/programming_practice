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

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserModel um = new UserModel();
        um.setName(name);
        um.setEmail(email);
        um.setPassword(password);

		int status = Dao.savedata(um);
		
		if (status>0) 
		{
			out.print("Inserted");
			response.sendRedirect("login.jsp");
			
			//System.out.println("Inserted");
		}
		else
		{
			out.print("Error in Register");
		}
	}
}