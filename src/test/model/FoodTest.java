package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    Food f;

    @BeforeEach
    public void runBefore() {
        f = new Food("Poutine", 5.80, 740);
    }

    @Test
    public void testDescription() {
        assertEquals("Poutine\t(740 Cals)\t$5.80", f.getDescription());
    }
}
