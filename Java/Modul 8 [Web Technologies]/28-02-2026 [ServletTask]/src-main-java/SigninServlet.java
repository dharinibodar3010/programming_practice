package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 1️ Get parameters from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2️ Call DAO signin
        UserModel user = UserDao.signin(email, password);

        // 3️ Check login success
        if(user != null) 
        {
            // Redirect to products page
            response.sendRedirect("ViewServlet");
        } 
        else
        {
            // Login failed
            out.print("<h3 style='color:red'>Invalid email or password!</h3>");
            out.print("<a href='index.html'>Try Again</a>");
        }
    }

    // Optional: handle GET request to redirect to login page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {
        response.sendRedirect("index.html");
    }
}