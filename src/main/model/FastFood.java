package model;

import java.util.ArrayList;

//This class is used to create a fast food restaurant (ex: McDonald's or DQ)
//and holds a name along with a list of items (Food and/or Drink) in it
public class FastFood {
    //fields
    private ArrayList<Item> itemlist;
    private String name;

    //Constructor
    // EFFECTS: Initializes the name field and itemlist to an ArrayList of Items
    public FastFood(String name) {
        this.itemlist = new ArrayList<Item>();
        this.name = name;
    }

    // EFFECTS: Getter method for itemlist field
    public ArrayList<Item> getFoodList() {
        return itemlist;
    }

    // EFFECTS: Getter method for name field
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: Adds item (Food or Drink) to the itemlist field
    public void addItemToRestaurant(Item item) {
        itemlist.add(item);
    }
}
