package com.servlet;

import java.io.IOException;

import com.dao.Dao;
import com.model.UsersModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersModel u = new UsersModel();
        u.setName(request.getParameter("name"));
        u.setEmail(request.getParameter("email"));
        u.setPassword(request.getParameter("password"));

        Dao dao = new Dao();
        int status = dao.register(u);
        if(status > 0) response.sendRedirect("index.jsp");
        else response.getWriter().println("Registration Failed!");
    }
}