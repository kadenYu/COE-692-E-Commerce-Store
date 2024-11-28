/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holditem.business;


import java.sql.Timestamp;
import ryerson.ca.holditem.helper.ItemHold;
import ryerson.ca.holditem.persistence.ItemHold_CRUD;

/**
 *
 * @author student
 */
public class HoldBusiness {

   



    public ItemHold getItem(int holdID, int itemID, int userID, Timestamp holdDate) {
        ItemHold bs = ItemHold_CRUD.getItemHold( holdID,  itemID,  userID, holdDate);

        return (bs);
    }

public boolean hold(int holdID, String userID) {
       
        return (ItemHold_CRUD.addHold(holdID, userID));
    }

    
}

