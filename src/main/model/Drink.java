package model;

import java.text.DecimalFormat;

//Another subclass of the abstract class Item. Used for drinks (liquids like water, coca cola, etc.)
//Instantiaes the abstract method getDescription for drinks
public class Drink extends Item {
    //fields
    private int volume;

    //Constructor
    // EFFECTS: Initializes the fields; name, price, calories, and volume (in mL)
    public Drink(String name, double price, int calories, int volume) {
        super(name, price, calories);
        this.volume = volume;
    }

    // EFFECTS: Returns the field volume (in mL)
    public int getVolume() {
        return volume;
    }

    // EFFECTS: Overrides the abstract getDescription method
    // Gives a description of the drink; including its name, calories, volume, and price
    // Price has been formatted to 2 decimal places using DecimalFormat
    @Override
    public String getDescription() {
        DecimalFormat f = new DecimalFormat("##.00");
        return getName() + "\t(" + getCalories() + " Cals, " + getVolume() + " mL)\t" + "$" + f.format(getPrice());
    }
}
