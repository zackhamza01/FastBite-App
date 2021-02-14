package model;

import java.util.ArrayList;

public class Order {
    private ArrayList<Item> orderlist;

    public Order() {
        this.orderlist = new ArrayList<Item>();
    }

    public ArrayList<Item> getOrderList() {
        return orderlist;
    }

    public void addItemToOrder(Item item) {
        orderlist.add(item);
    }

    public boolean removeItemFromOrder(Item item) {
        if (orderlist.contains(item)) {
            orderlist.remove(item);
            return true;
        }
        return false;
    }

    public double getTotalAmount() {
        double price = 0.0;
        for (Item item: orderlist) {
            price += item.getPrice();
        }
        return price;
    }
}
