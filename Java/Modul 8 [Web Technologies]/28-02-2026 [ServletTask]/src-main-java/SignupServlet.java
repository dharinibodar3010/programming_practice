package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");

        // 1️ Get parameters from signup form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2️ Create UserModel
        UserModel um = new UserModel();
        um.setUsername(username);
        um.setEmail(email);
        um.setPassword(password);

        // 3️ Call DAO signup
        int status = UserDao.signup(um);

        // 4️ Redirect based on status
        if(status > 0)
        {
            // Signup success → redirect to signin page
            response.sendRedirect("signin"); // fix URL
        }
        else
        {
            // Signup failed → show message
            response.getWriter().print("<h3 style='color:red'>Signup failed. Try again!</h3>");
            response.getWriter().print("<a href='index.html'>Back to Signup</a>");
        }
    }
}