package com.servletsession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("project") != null)
		{
			String pname = request.getParameter("pname");
			String pprice = request.getParameter("pprice");
			String pdes = request.getParameter("pdes");
			
			ProductModel pm = new ProductModel();
			pm.setPname(pname);
			pm.setPprice(pprice);
			pm.setPdes(pdes);
			
			//int status = Dao.adddata(pm);
		}
		else
		{
			out.print("<p>Please Login First</p>");
		}
	}

}
