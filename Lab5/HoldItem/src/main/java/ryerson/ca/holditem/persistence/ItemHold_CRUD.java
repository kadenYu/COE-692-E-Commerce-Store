/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holditem.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import ryerson.ca.holditem.helper.ItemHold;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 *
 * @author student
 */
public class ItemHold_CRUD {
    private static Connection getCon(){
    Connection con=null;
     try{
         
         Class.forName("com.mysql.cj.jdbc.Driver");
        //con=DriverManager.getConnection("jdbc:mysql://db:3306/hold_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
       
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC", "root", "student");
        System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
    }
    
    
    
    public static boolean addHold(int holdid, String username){
      
        try{
            Connection con= getCon();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate date = LocalDate.now();
            String q = "insert into Item_Holds "
                    + "(HoldID, ItemID, UserID, HoldDate) values "
                    + "('"+holdid+"', "
                    +"'"+username+"', "
                    +"'"+date.format(formatter)+"');";
            Statement stmt = con.createStatement();   
            System.out.println(q);
            stmt.execute(q);
			con.close();
                        return true;

		}catch(Exception e){System.out.println(e);
                return false;
                }
 
        
    }
    public static ItemHold getItemHold(int holdID, int itemID, int userID, Timestamp holdDate){
        ItemHold item=null;
        try{
            Connection con= getCon();
            
            String q = "select * from Item_Holds "
                    + " WHERE "
                    
                    + "HoldID = '"+holdID+"';";
            System.out.println(q);

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){

				
				
                                String date=rs.getDate("startdate").toString();
                                String username=rs.getString("UserID").toString();
                                
                                 item = new ItemHold(holdID , itemID, userID, holdDate);
                                
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
    
        return item;
        
    }

    public static boolean addHold(int holdID, String UserID,  String holddate) {
      return( addHold(holdID, UserID));
    }
}

