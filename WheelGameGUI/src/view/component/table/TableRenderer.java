package view.component.table;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));

        return this;
    }
}
