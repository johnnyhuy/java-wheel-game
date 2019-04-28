package view.component;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    public SummaryPanel(JPanel panel) {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
        setPreferredSize(new Dimension(240, panel.getHeight()));
    }
}
