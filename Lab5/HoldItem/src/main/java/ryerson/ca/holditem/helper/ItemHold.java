
package ryerson.ca.holditem.helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 *
 * @author student
 */
public class ItemHold {
    private int holdID;
    private int itemID;
    private int userID; 
    private Timestamp holdDate; 

public ItemHold (int holdID, int itemID, int userID, Timestamp holdDate){
    this.holdID = holdID;
    this.itemID = itemID;
    this.userID = userID;
    this.holdDate = holdDate;
}
 public int getHoldID() {
     return holdID;
 }

public int getItemID(){
    return itemID;
}

public int getUserID(){
    return userID;
}
public Timestamp getHoldDate(){
    return holdDate;
}

public void setHoldID (int newHoldID){
    this.holdID = newHoldID;
}

public void setItemID (int newItemID){
    this.itemID = newItemID;
}

public void setUserID (int newUserID){
    this.userID = newUserID;
}

public void setHoldDate (Timestamp newHoldDate){
    this.holdDate = newHoldDate;
}


}
