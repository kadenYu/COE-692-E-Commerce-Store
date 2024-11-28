package Persistance;

import Helper.UserInfo; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Corrected from ResultSets to ResultSet

/**
 *
 * @author student
 */
public class User_CRUD {
    
    private static Connection getCon(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection established");
        } catch(Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static UserInfo read(String UserName, String Password){
        UserInfo bean = null;
        try{
            Connection con = getCon();
            
            String q = "SELECT * FROM USER WHERE UserName LIKE ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, UserName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String Uid = rs.getString("UserID");
                String email = rs.getString("Email");
                String pass = rs.getString("Password");
                
                if (pass.equals(Password)){
                    bean = new UserInfo(UserName, Uid, email, pass);
                }
            }
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return bean; // Corrected placement of the return statement
    }
}
