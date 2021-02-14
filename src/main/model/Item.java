package model;

public abstract class Item {
    //Fields
    private String name;
    private double price;
    private int calories;

    //Constructor
    public Item(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        int temp = (int)(price * 100.0);
        return ((double)temp) / 100.0;
    }

    public int getCalories() {
        return calories;
    }

    protected abstract String getDescription();

}
