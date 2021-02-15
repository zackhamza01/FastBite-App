package model;

//Abstract class used to create the Food and Drink classes. The instantiated methods
//are the getter methods for its fields. The one abstract method Item has is getDescription
//which makes Food and Drink instantiate their own instantiations for the method.
public abstract class Item {
    //Fields
    private String name;
    private double price;
    private int calories;

    //Constructor
    // EFFECTS: Initializes the fields; name, price, and calories. Cannot have an object
    //instantiated though because Item is abstract
    public Item(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    // EFFECTS: returns the field name
    public String getName() {
        return name;
    }

    // EFFECTS: returns the field price
    public double getPrice() {
        int temp = (int)(price * 100.0);
        return ((double)temp) / 100.0;
    }

    // EFFECTS: returns the field calories
    public int getCalories() {
        return calories;
    }

    // EFFECTS: Abstract method that gives a description of the Item
    public abstract String getDescription();

}
