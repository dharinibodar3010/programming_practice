package com.controller;

import java.io.IOException;

import com.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        int status = Dao.deletedata(id);

        if(status>0) 
        {
            response.sendRedirect("index.jsp");
        } else 
        {
            response.getWriter().println("Delete Failed");
        }
    }
}