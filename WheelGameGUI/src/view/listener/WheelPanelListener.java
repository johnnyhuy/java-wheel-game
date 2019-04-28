package view.listener;

import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.component.CirclePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WheelPanelListener implements ComponentListener {
    private final ImageIcon icon;
    private final JLabel label;
    private int circleRadius;
    private GameEngine gameEngine;
    private JPanel panel;
    private CirclePanel circlePanel;
    private int padding;
    private int iconSize;

    public WheelPanelListener(GameEngine gameEngine, JPanel panel, ImageIcon icon, JLabel label, int padding) {
        this.gameEngine = gameEngine;
        this.panel = panel;
        this.icon = icon;
        this.label = label;
        this.padding = padding;
        this.circlePanel = new CirclePanel(iconSize);

        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(circlePanel));
        label.add(circlePanel);
    }

    private Point getCirclePoint(float angle) {
        int xOffset = Math.round((label.getWidth() / 2) - circleRadius);
        int yOffset = Math.round((label.getHeight() / 2) - circleRadius);

        double rads = Math.toRadians(angle);
        int radius = (iconSize / 2) - circleRadius;

        int x = Math.round((float) (xOffset + Math.cos(rads) * radius));
        int y = Math.round((float) (yOffset + Math.sin(rads) * radius));

        return new Point(x, y);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        iconSize = panel.getWidth() > panel.getHeight() ? panel.getHeight() - (padding * 2) : panel.getWidth() - (padding * 2);

        // What a ridiculous number aye?
        circleRadius = (int) Math.round(iconSize * 0.0375);
        circlePanel.setRadius(circleRadius);

        Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
        label.setIcon(newIcon);
        circlePanel.setLocation(getCirclePoint(0));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
