package view.component;

import javax.swing.border.EmptyBorder;

public class Padding extends EmptyBorder {
    private int size;

    public Padding(int size) {
        super(size, size, size, size);
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public int getTotalSize() {
        return getSize() * 2;
    }
}
