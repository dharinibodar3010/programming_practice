package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		List<ProductModel>list = ProductDao.ViewProducts();
		out.print("<a href='products.html'>Add data</a>");
		out.print("<table border='1' cellpadding='5px' cellspacing='5px'>");
		out.print("<tr>");
		out.print("<th>Id</th><th>Product Name</th><th>Product Price</th><th>Product Description</th><th>Edit</th><th>Delete</th>");
		out.print("</tr>");
		for(ProductModel m : list)
		{
			out.print("<tr>");
			out.print("<td>"+m.getId()+"</td><td>"+m.getPname()+"</td><td>"+m.getPprice()+"</td><td>"+m.getPdes()+"</td><td><a href='EditServlet?id="+m.getId()+"'>Edit</a></td><td><a href='DeleteServlet?id="+m.getId()+"'>Delete</a></td>");
			out.print("</tr>");
			
			
			//System.out.println(m.getId()+" "+m.getPname());
		}
			out.print("</table>");
	}
}