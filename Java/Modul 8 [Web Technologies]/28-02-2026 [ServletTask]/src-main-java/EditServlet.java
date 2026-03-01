package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("id");
		System.out.println(id);
		int myid = Integer.parseInt(id); 
		ProductModel m = ProductDao.getrecord(myid);
		
	        out.print("<form action='update' method='post'>");

	        out.print("<p>");
	        
	        out.print("<input type='hidden' name='id' value='"+m.getId()+"'>");
	        out.print("</p>");
	        
	        out.print("<p>");
	        out.print("Enter Product Name:");
	        out.print("<input type='text' name='pname' value='"+m.getPname()+"'>");
	        out.print("</p>");

	        out.print("<p>");
	        out.print("Enter Product Price:");
	        out.print("<input type='text' name='pprice' value='"+m.getPprice()+"'>");
	        out.print("</p>");

	        out.print("<p>");
	        out.print("Enter Product Description:");
	        out.print("<input type='text' name='pdes' value='"+m.getPdes()+"'>");
	        out.print("</p>");

	        out.print("<p>");
	        out.print("<input type='submit' value='Update'>");
	        out.print("</p>");

	        out.print("</form>");
	}
}
