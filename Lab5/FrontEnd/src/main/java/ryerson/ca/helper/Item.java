package ryerson.ca.helper;

public class Item {
    private int id;
    private String name;
    private String size;
    private double price;
    private String colour;
    private String availability;
    private String description;
    
    public Item(int id, String name, String size, double price, String colour, String availability, String description) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.colour= colour;
        this.availability= availability;
        this.description = description; 
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public double getPrice() {
        return price;
    }
    
    public void setId(int newId){
     this.id = newId;
    }
    public void setName (String newName){
        this.name = newName;
    }
    
    public String getDescription(){
        return description;
    }
    
    
    public void setDescription (String newDescription){
        this.description = newDescription;
    }
    
    public void setPrice (double newPrice){
        this.price = newPrice;
    }
    
    
    public String getAvailability (){
           return availability ;
                   }
    public void setAvailability (String newAvailability){
        this.availability = newAvailability;
    }
    
    
}

