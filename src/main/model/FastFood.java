package model;

import java.util.ArrayList;

public class FastFood {
    private ArrayList<Item> itemlist;
    private String name;

    public FastFood(String name) {
        this.itemlist = new ArrayList<Item>();
        this.name = name;
    }

    public ArrayList<Item> getFoodList() {
        return itemlist;
    }

    public String getName() {
        return name;
    }

    public void addItemToRestaurant(Item item) {
        itemlist.add(item);
    }
}
