package com.servlet;

import java.io.IOException;
import java.util.List;

import com.dao.Dao;
import com.model.CoursesModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Dao dao = new Dao();
        List<CoursesModel> list = dao.getAllCourses();
        
        request.setAttribute("courses", list);
        
        RequestDispatcher rd = request.getRequestDispatcher("courses.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}