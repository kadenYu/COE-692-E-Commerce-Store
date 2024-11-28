/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchitem.business;

import ryerson.ca.searchitem.helper.Item;
import ryerson.ca.searchitem.helper.ItemXML;
import ryerson.ca.searchitem.persistence.Item_CRUD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class searchItem {

    public ItemXML getItemsByQuery(String query) {
        Set<Item> items = Item_CRUD.searchItem(query);
        Map<String, Item> allItem = new HashMap<>();

        for (Item item : items) {
            if (allItem.containsKey(item.getName())) {
                // If part with same name exists, add manufacturer to the existing part
                allItem.get(item.getName()).setDescription(item.getDescription());
            } else {
                // Otherwise, add the part to the map
                allItem.put(item.getName(), item);
            }
        }

        ItemXML itemsXML = new ItemXML();
        itemsXML.setItems(new ArrayList<>(allItem.values()));
        return itemsXML;
    }
}
