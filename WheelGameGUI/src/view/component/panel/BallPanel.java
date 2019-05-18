package view.component.panel;

import javax.swing.*;
import java.awt.*;

public class BallPanel extends JPanel {
    private int diameter;
    private int radius;

    public BallPanel() {
        setOpaque(false);
    }

    public int getRadius() {
        return this.radius;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, diameter, diameter);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        this.diameter = radius * 2;
    }

    public int getDiameter() {
        return this.diameter;
    }
}
