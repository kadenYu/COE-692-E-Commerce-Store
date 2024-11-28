package com.mycompany.lab2;

import Persistance.User_CRUD;
import Helper.UserInfo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        
        UserInfo uinfo = User_CRUD.read(UserName, Password);
        
        if (uinfo != null) {
            request.getSession().setAttribute("uname", UserName);
            request.getSession().setAttribute("pword", Password);
            request.getRequestDispatcher("userAcc.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}