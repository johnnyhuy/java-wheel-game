package model;

import model.enumeration.Color;
import model.interfaces.Slot;

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
        return String.format(format, position, color, number);
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public Color getColor() {
        return this.color;
    }


    @Override
    public boolean equals(Slot slot) {
        return this.color == slot.getColor()
            && this.position == slot.getPosition()
            && this.number == slot.getNumber();
    }
}
