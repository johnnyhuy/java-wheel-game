package view.component.panel;

import javax.swing.*;
import java.awt.*;

public class LabelPanel extends JPanel {
    public LabelPanel(String text) {
        setLayout(new BorderLayout());
        JLabel playerNameLabel = new JLabel(text);
        playerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        playerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(playerNameLabel);
    }
}
