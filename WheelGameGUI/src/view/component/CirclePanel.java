package view.component;

import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {
    private int diameter;

    public CirclePanel(int radius) {
        this.diameter = radius * 2;
        setOpaque(false);
    }

    public void setRadius(int radius) {
        this.diameter = radius * 2;
    }

    @Override
    public void setLocation(Point point) {
        setBounds(point.x, point.y, diameter, diameter);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, diameter, diameter);
    }
}
