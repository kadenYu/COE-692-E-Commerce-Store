package ryerson.ca.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ryerson.ca.helper.Item;
import ryerson.ca.helper.ItemXML;

public class Business {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/LMS?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "student";

    public static ItemXML getServices(String query, String token) throws IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Item WHERE ItemName LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + query + "%");
                try (ResultSet rs = statement.executeQuery()) {
                    List<Item> items = new ArrayList<>();
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
                    ItemXML itemXML = new ItemXML();
                    itemXML.setItems(new ArrayList<>(items)); // Convert List<Item> to ArrayList<Item>
                    return itemXML;
                }
            }
        } catch (SQLException ex) {
            throw new IOException("Error accessing database", ex);
        }
    }

    public static boolean isAuthenticated(String username, String password) {
        // Implement your authentication logic here.
        // For example, checking against a database or an authentication service.
        return username.equals("kaden") && password.equals("yu");
    }

    public static boolean holdItem(int itemId, String token) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/FrontEnd/index/")
                .path("FrontEnd");
                //.path(String.valueOf(itemId))
                //.path("hold");

        Invocation.Builder invocationBuilder = target.request();

        if (token != null) {
            invocationBuilder.header("Authorization", "Bearer " + token);
        }

        Response response = invocationBuilder.post(null);

        return response.getStatus() == 200;
    }
}
