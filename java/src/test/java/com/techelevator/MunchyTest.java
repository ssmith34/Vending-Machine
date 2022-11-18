package com.techelevator;

import com.techelevator.models.Candy;
import com.techelevator.models.Munchy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MunchyTest {

    @Test
    public void munchy_constructor_should_build_object_with_proper_attributes() {
        Munchy munchy = new Munchy("Cheetoes", 0.75, "C2", "Ew!");
        String expectedName = "Cheetoes";
        String actualName = munchy.getName();
        assertEquals(expectedName, actualName);

        double expectedPrice = 0.75;
        double actualPrice = munchy.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);

        String expectedSlot = "C2";
        String actualSlot = munchy.getSlotNumber();
        assertEquals(expectedSlot, actualSlot);

        String expectedMessage = "Ew!";
        String actualMessage = munchy.getDispenseMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
