package com.mycompany.lab2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Helper.Item;

@WebServlet(name = "Item_CRUD", urlPatterns = {"/Item_CRUD"})
public class Item_CRUD extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        Item item = searchItemByName(itemName);
        request.setAttribute("item", item);
        request.getRequestDispatcher("searchedItem.jsp").forward(request, response);
    }

    private Item searchItemByName(String itemName) {
        Item item = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?serverTimezone=UTC", "root", "student");
            System.out.println("Connection established for search");
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Item WHERE ItemName = ?");
            ps.setString(1, itemName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                item = new Item(id, name, description, price);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}

