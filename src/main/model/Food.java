package model;

import java.text.DecimalFormat;

public class Food extends Item {

    public Food(String name, double price, int calories) {
        super(name, price, calories);
    }

    @Override
    public String getDescription() {
        DecimalFormat f = new DecimalFormat("##.00");
        return getName() + "\t(" + getCalories() + " Cals)\t" + "$" + f.format(getPrice());
    }

}
