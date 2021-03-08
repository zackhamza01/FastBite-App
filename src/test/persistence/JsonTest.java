package persistence;

import model.Order;
import model.*;
import java.io.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// Test for the JSON Reader and Writer classes
public class JsonTest {
    Order order;

    @BeforeEach
    public void runBefore() {
        order = new Order();
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0notgoodfilename.json");
            writer.open();
            fail("IOException was expected!");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyOrder() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            order = reader.readOrder();
            assertEquals(0, order.getOrderList().size());
            assertEquals(0.0, order.getTotalAmount());
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

    @Test
    public void testWriterGeneralOrder() {
        try {
            order.addItemToOrder(new Food("Grilled Cheese", 5.00, 400));
            order.addItemToOrder(new Drink("Pepsi", 2.00, 200, 300));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOrder.json");
            order = reader.readOrder();
            assertEquals(2, order.getOrderList().size());
            assertEquals("Grilled Cheese", order.getOrderList().get(0).getName());
            assertEquals("Pepsi", order.getOrderList().get(1).getName());
            assertEquals("Total: $7.00", order.getTotalAmountString());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
