package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//This is the Test for the FastFood class in the model package
public class FastFoodTest {
    FastFood f;
    Food f1;
    Drink d1;

    @BeforeEach
    public void runBefore() {
        f = new FastFood("McDonald's");
        f1 = new Food("Big Mac", 7.50, 530);
        d1 = new Drink("Pepsi", 2.50, 580, 370);
    }

    @Test
    public void testAddItemToRestaurant() {
        assertEquals("McDonald's", f.getName());
        f.addItemToRestaurant(f1);
        f.addItemToRestaurant(d1);
        assertEquals(f1, f.getFoodList().get(0));
        assertEquals(d1, f.getFoodList().get(1));
    }
}