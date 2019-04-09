package model;

import model.enumeration.Color;
import model.interfaces.Slot;

import java.util.Objects;

import static helper.StringHelper.capitalize;

public class SlotImpl implements Slot {
    private int position;
    private Color color;
    private int number;

    public SlotImpl(int position, Color color, int number) {
        this.position = position;
        this.color = color;
        this.number = number;
    }

    /**
     * Convert Slot object to string.
     *
     * @return String
     */
    public String toString() {
        final String format = "Position: %d, Color: %s, Number: %d";
        return String.format(format, position, capitalize(color), number);
    }

    /**
     * Overridden method to get the slot position.
     *
     * @return int
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * Overridden method to get the slot number.
     *
     * @return int
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Overridden method to get the slot color.
     *
     * @return Color
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Compare slot with another one.
     *
     * @param slot - another Slot to compare with
     * @return boolean
     */
    @Override
    public boolean equals(Slot slot) {
        return this.color == slot.getColor() && this.number == slot.getNumber();
    }

    /**
     * Generate a hash code out of the position, color and number.
     *
     * @return integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(position, color.hashCode(), number);
    }
}
