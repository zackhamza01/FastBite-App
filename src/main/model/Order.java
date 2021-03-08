package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

//This class makes the user an Order form to keep track of the items that the user adds
//and wants to check out. It can add and remove items, along with give the total price of the items
public class Order implements Writable {
    //fields
    private ArrayList<Item> orderlist;

    //Constructor
    // EFFECTS: Initializes the orderlist field to an ArrayList of Items
    public Order() {
        this.orderlist = new ArrayList<Item>();
    }

    //EFFECTS: Getter method for the field orderlist
    public ArrayList<Item> getOrderList() {
        return orderlist;
    }

    // MODIFIES: this
    // EFFECTS: Adds item (could be from Food or Drink class) to the orderlist field
    public void addItemToOrder(Item item) {
        orderlist.add(item);
    }

    // MODIFIES: this
    // EFFECTS: Removes item (could be from Food or Drink class) to the orderlist field
    public boolean removeItemFromOrder(Item item) {
        if (orderlist.contains(item)) {
            orderlist.remove(item);
            return true;
        }
        return false;
    }

    // EFFECTS: Gives the total price of items in the orderlist as a double
    public double getTotalAmount() {
        double price = 0.0;
        for (Item item: orderlist) {
            price += item.getPrice();
        }
        return price;
    }

    // EFFECTS: Gives the total price of items in the orderlist as a String
    // Price has been formatted to 2 decimal places using DecimalFormat
    public String getTotalAmountString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return "Total: $" + f.format(getTotalAmount());
    }

    // EFFECTS: Returns Order as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("orderlist", itemToOrderJson());
        return json;
    }

    // EFFECTS: Returns items in Order as a JSONArray
    private JSONArray itemToOrderJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item: orderlist) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }
}
