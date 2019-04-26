package view.component;

import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {
    private int diameter;

    public CirclePanel(int diameter) {
        this.diameter = diameter;
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawOval(0, 0, diameter, diameter);
        g.setColor(Color.LIGHT_GRAY);
    }
}
