package ryerson.ca.searchitem.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemXML {

    @XmlElement(name = "item")
    private ArrayList<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public ItemXML() {

    }

    public void setItems(ArrayList<Item> bs) {
        items=bs;
    }
}