/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab3; 


import Helper.UserInfo;
import Persistance.User_CRUD;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("UserName");
        String password = request.getParameter("Password");

        // Check if user exists in the database
        UserInfo user = User_CRUD.read(UserName, Password);

        if (user != null) {
            // Redirect to success page upon successful login
            response.sendRedirect("success.html");
        } else {
            // User does not exist or invalid credentials
            out.println("<html><body>");
            out.println("<script>alert('You do not have an account. Please register.');</script>");
            out.println("<h1>Invalid username or password</h1>");
            out.println("<p>Please check your credentials or <a href='register.html'>register</a> if you don't have an account.</p>");
            out.println("</body></html>");
        }
    }
}
