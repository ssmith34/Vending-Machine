package com.techelevator;

import com.techelevator.models.Gum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GumTest {

    @Test
    public void gum_constructor_should_build_object_with_proper_attributes() {
       Gum gum = new Gum("DoubleBuble", 3.17, "B1", "Pop!");
        String expectedName = "DoubleBuble";
        String actualName = gum.getName();
        assertEquals(expectedName, actualName);

        double expectedPrice = 3.17;
        double actualPrice = gum.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);

        String expectedSlot = "B1";
        String actualSlot = gum.getSlotNumber();
        assertEquals(expectedSlot, actualSlot);

        String expectedMessage = "Pop!";
        String actualMessage = gum.getDispenseMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
}
