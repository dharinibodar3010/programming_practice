package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/save")
public class SaveServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String pname = req.getParameter("pname");
		String pprice = req.getParameter("pprice");
		String pdes = req.getParameter("pdes");
		
		ProductModel m = new ProductModel();
		m.setPname(pname);
		m.setPprice(pprice);
		m.setPdes(pdes);
		
		int status = ProductDao.addProduct(m);
		
		if (status>0) 
		{
			out.print("Inserted");
			resp.sendRedirect("ViewServlet");
			
			//System.out.println("Inserted");
		}
		else
		{
			out.print("Fail");
		}
	}

}