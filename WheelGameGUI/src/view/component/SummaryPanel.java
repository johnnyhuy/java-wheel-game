package view.component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SummaryPanel extends JPanel {
    public SummaryPanel(JPanel panel) {
        setBorder(new TitledBorder("Game Summary"));
        setPreferredSize(new Dimension(240, panel.getHeight()));
    }
}
