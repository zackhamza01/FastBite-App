package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrinkTest {
    Drink d;

    @BeforeEach
    public void runBefore() {
        d = new Drink("Fanta", 5.40, 160, 330);
    }

    @Test
    public void testDescription() {
        assertEquals("Fanta\t(160 Cals, 330 mL)\t$5.40", d.getDescription());
    }
}
