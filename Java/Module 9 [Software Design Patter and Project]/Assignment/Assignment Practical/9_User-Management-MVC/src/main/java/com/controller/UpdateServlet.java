package com.controller;

import java.io.IOException;

import com.dao.Dao;
import com.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        UserModel um = new UserModel();
        um.setId(id);
        um.setName(name);
        um.setEmail(email);

        int status = Dao.updatedata(um);

        if(status > 0) 
        {
            // ✅ IMPORTANT: session update karo
            HttpSession session = req.getSession();
            session.setAttribute("user", um);

            res.sendRedirect("profile.jsp");
        } 
        else 
        {
            res.getWriter().println("Update Failed");
        }
    }
}