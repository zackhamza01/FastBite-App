package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//This is the Test for the Order class in the model package
public class OrderTest {
    Order order;
    Food f1;
    Food f2;
    Drink d1;
    Drink d2;

    @BeforeEach
    public void runBefore() {
        order = new Order();
        f1 = new Food("Big Mac", 7.50, 530);
        f2 = new Food("French Fries", 3.30, 450);
        d1 = new Drink("Pepsi", 2.50, 580, 370);
        d2 = new Drink("Coca Cola", 3.00, 480, 300);
    }


    @Test
    public void testAddOrder() {
        order.addItemToOrder(f2);
        order.addItemToOrder(d1);
        assertEquals("French Fries", f2.getName());
        assertEquals("Pepsi", d1.getName());
    }

    @Test
    public void testRemoveItemTrue() {
        order.addItemToOrder(f1);
        order.addItemToOrder(f2);
        order.addItemToOrder(d1);
        order.addItemToOrder(d2);
        assertEquals(d1, order.getOrderList().get(2));
        assertTrue(order.removeItemFromOrder(d1));
        assertTrue(order.removeItemFromOrder(f1));
        assertEquals(f2, order.getOrderList().get(0));
        assertEquals(d2, order.getOrderList().get(1));
    }

    @Test
    public void testRemoveItemFalse() {
        order.addItemToOrder(f1);
        order.addItemToOrder(f2);
        assertFalse(order.removeItemFromOrder(d1));
        assertFalse(order.removeItemFromOrder(d2));
    }

    @Test
    public void testTotalAmount() {
        order.addItemToOrder(f1);
        assertEquals(7.5, order.getTotalAmount());
        assertEquals("Total: $7.50", order.getTotalAmountString());
        order.addItemToOrder(f2);
        assertEquals(10.8, order.getTotalAmount());
        assertEquals("Total: $10.80", order.getTotalAmountString());
        order.addItemToOrder(d1);
        assertEquals(13.3, order.getTotalAmount());
        assertEquals("Total: $13.30", order.getTotalAmountString());
        order.addItemToOrder(d2);
        assertEquals(16.3, order.getTotalAmount());
        assertEquals("Total: $16.30", order.getTotalAmountString());
    }
}
