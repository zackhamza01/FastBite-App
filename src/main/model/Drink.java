package model;

import java.text.DecimalFormat;

public class Drink extends Item {
    private int volume;

    public Drink(String name, double price, int calories, int volume) {
        super(name, price, calories);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String getDescription() {
        DecimalFormat f = new DecimalFormat("##.00");
        return getName() + "\t(" + getCalories() + " Cals, " + getVolume() + " mL)\t" + "$" + f.format(getPrice());
    }
}
