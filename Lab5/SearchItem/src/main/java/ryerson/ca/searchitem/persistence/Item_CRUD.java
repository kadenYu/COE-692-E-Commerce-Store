package ryerson.ca.searchitem.persistence;

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
import ryerson.ca.searchitem.helper.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String size = rs.getString("size");
                double price = rs.getDouble("price");
                String colour = rs.getString("colour");
                String availability = rs.getString("availability");
                String description = rs.getString("description");
                item = new Item(id, name, size, price, colour, availability, description);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
    
    public static Set<Item> searchItem(String query) {
        Set<Item> items = new HashSet<Item>();
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?serverTimezone=UTC", "root", "student");
            System.out.println("Connection established for search");
            
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Item WHERE ItemName LIKE ?");
                ps.setString(1, "%" + query + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int itemID = rs.getInt("ItemID");
                    String name = rs.getString("ItemName");
                    String size = rs.getString("Size");
                    double price = rs.getDouble("Price");
                    String colour = rs.getString("Colour");
                    String availability = rs.getString("Availability");        
                    String description = rs.getString("Description");

                    Item item = new Item(itemID,name, size,price,colour, availability, description);
                    items.add(item);
                }

                rs.close();
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }
    
    
}

