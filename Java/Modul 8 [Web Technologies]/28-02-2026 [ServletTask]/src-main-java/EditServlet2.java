package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/update")
public class EditServlet2 extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("id");
		int id2 = Integer.parseInt(id);
		String pname = req.getParameter("pname");
		String pprice = req.getParameter("pprice");
		String pdes = req.getParameter("pdes");
		
		ProductModel m = new ProductModel();
		m.setId(id2);
		m.setPname(pname);
		m.setPprice(pprice);
		m.setPdes(pdes);
		
		int status = ProductDao.updateProduct(m);
		
		if(status>0)
		{
			out.print("Updated");
			resp.sendRedirect("ViewServlet");
			//System.out.println("Inserted");
		}
		else
		{
			out.print("Fail");
			
		}
		
	}
}