package ryerson.ca.persistence;

import ryerson.ca.helper.UserInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?serverTimezone=UTC", "root", "student");
            System.out.println("Connection established");
        } catch (Exception e) 
            {System.out.println("Error establishing database connection"+ e);}
        return con;
    }

    public static UserInfo read(String username, String password){
        UserInfo uf = null;
        try {
            Connection con = getCon();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM User WHERE UserName = ? AND Password = ?");
                ps.setString(1, username);
                ps.setString(2, password);
                
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String uname = rs.getString("UserName");
                    String pass = rs.getString("Password");
                    String uEmail = rs.getString("Email");
                    if (uname.equals(username) && pass.equals(password)) { uf = new UserInfo(username, password, uEmail); }
                    System.out.println("Success!");
                }
                
                else{ System.out.println("Fail!");}
                rs.close();
                ps.close();
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return uf;
    }
}