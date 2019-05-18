package view.component.panel;

import view.component.frame.GameFrame;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    private GameFrame frame;
    private WheelPanel wheelPanel;

    public SummaryPanel(GameFrame frame, WheelPanel wheelPanel) {
        this.frame = frame;
        this.wheelPanel = wheelPanel;

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
        setSize();
        setLayout(new BorderLayout());
    }

    public void setSize() {
        setPreferredSize(new Dimension(frame.getWidth() / 3, wheelPanel.getHeight()));
    }
}
