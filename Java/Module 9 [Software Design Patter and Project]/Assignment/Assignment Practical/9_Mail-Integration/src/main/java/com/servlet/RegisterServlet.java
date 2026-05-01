package com.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import com.dao.UserDao;
import com.model.UserModel;
import com.util.SendEmail; 

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // check email
        boolean emailExists = UserDao.checkEmail(email);

        if(emailExists) {
            response.getWriter().println("Email already registered!");
            return;
        }


        // OTP generate
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        
        // Console ma otp print
        System.out.println("OTP for " + email + " is: " + otp);

        // session ma store
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("password", password);

        // web.xml config
        ServletContext context = getServletContext();
        String host = context.getInitParameter("mail.smtp.host");
        String port = context.getInitParameter("mail.smtp.port");
        String user = context.getInitParameter("mail.user");
        String pass = context.getInitParameter("mail.password");

        // email send
        SendEmail.sendMail(email, name, host, port, user, pass, otp);

        response.sendRedirect("otp.jsp");
    }
}