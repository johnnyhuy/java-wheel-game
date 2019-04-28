package view.listener;

import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.component.BallPanel;
import view.component.WheelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WheelPanelListener implements ComponentListener {
    private final ImageIcon icon;
    private final JLabel label;
    private int circleRadius;
    private GameEngine gameEngine;
    private WheelPanel wheelPanel;
    private BallPanel ballPanel;
    private int padding;
    private int iconSize;

    public WheelPanelListener(GameEngine gameEngine, WheelPanel wheelPanel) {
        this.gameEngine = gameEngine;
        this.wheelPanel = wheelPanel;
        this.icon = wheelPanel.getIcon();
        this.label = wheelPanel.getWheelLabel();
        this.padding = wheelPanel.getPadding();
        this.ballPanel = wheelPanel.getBallPanel();

        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(ballPanel));
        label.add(ballPanel);
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
        iconSize = wheelPanel.getWheelSize();

        // What a ridiculous number aye?
        circleRadius = (int) Math.round(iconSize * 0.0375);
        ballPanel.setRadius(circleRadius);

        Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
        label.setIcon(newIcon);
        ballPanel.setLocation(getCirclePoint(0));
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
