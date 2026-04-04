package com.servlet;

import java.io.IOException;

import com.dao.Dao;
import com.model.EnrollmentsModel;
import com.model.UsersModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsersModel user = (UsersModel) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String courseId = request.getParameter("courseId");
        String amount = request.getParameter("amount");

        EnrollmentsModel e = new EnrollmentsModel();
        e.setUser_id(String.valueOf(user.getId()));
        e.setCourse_id(courseId);
        e.setPayment_status("pending"); // 
        int status = Dao.enroll(e);

        if (status > 0) {
            
            response.sendRedirect("payment.jsp?courseId=" + courseId + "&amount=" + amount);
        } else {
            response.getWriter().println("Enrollment Failed! Check Console for SQL Errors.");
        }
    }
}
