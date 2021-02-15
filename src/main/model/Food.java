package model;

import java.text.DecimalFormat;

//Subclass of the abstract class Item. Used for solid foods (ex: apple or hamburger)
//Instantiates the abstract method getDescription for solid foods
public class Food extends Item {
    //No fields as it takes all the fields from the abstract class Item

    //Constructor
    // EFFECTS: Initializes the fields; name, price, and calories
    public Food(String name, double price, int calories) {
        super(name, price, calories);
    }

    // EFFECTS: Overrides the abstract getDescription method
    // Gives a description of the solid food; including its name, calories, and price
    // Price also has been formatted so it is outputted as 2 decimal places instead of the default 1
    @Override
    public String getDescription() {
        DecimalFormat f = new DecimalFormat("##.00");
        return getName() + "\t(" + getCalories() + " Cals)\t" + "$" + f.format(getPrice());
    }

}
