package view.component;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    public SummaryPanel(JPanel panel) {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(240, panel.getHeight()));
        setBackground(Color.lightGray);
    }
}
