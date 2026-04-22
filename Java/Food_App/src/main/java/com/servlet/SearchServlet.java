package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.dao.Dao;
import com.model.ProductModel;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (query == null || query.trim().isEmpty()) {
            return;
        }

        try {
            Connection con = Dao.getconnect();
            PreparedStatement ps = con.prepareStatement("SELECT name FROM products WHERE name LIKE ? LIMIT 5");
            ps.setString(1, query + "%");
            ResultSet rs = ps.executeQuery();

            StringBuilder suggestions = new StringBuilder();
            while (rs.next()) {
                String name = rs.getString("name");
                suggestions.append("<div class='suggestion-item' onclick='selectSuggestion(\"").append(name).append("\")'>").append(name).append("</div>");
            }
            out.print(suggestions.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
