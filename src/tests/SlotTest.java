import model.SlotImpl;
import model.enumeration.Color;
import model.interfaces.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlotTest {
    @Test
    void testSlotToString() {
        // Arrange
        final String expectedSlotString = "Position: 0, Color: Red, Number: 69";
        final Slot slot = new SlotImpl(0, Color.RED, 69);

        // Act
        String slotString = slot.toString();

        // Assert
        assertEquals(expectedSlotString, slotString);
    }
}
