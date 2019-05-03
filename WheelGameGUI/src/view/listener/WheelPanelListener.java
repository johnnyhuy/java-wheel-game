package view.listener;

import model.interfaces.GameEngine;
import view.component.BallPanel;
import view.component.WheelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WheelPanelListener implements ComponentListener {
    private final ImageIcon icon;
    private final JLabel label;
    private int ballRadius;
    private WheelPanel wheelPanel;
    private BallPanel ballPanel;
    private int iconSize;

    public WheelPanelListener(GameEngine gameEngine, WheelPanel wheelPanel) {
        this.wheelPanel = wheelPanel;
        this.icon = wheelPanel.getIcon();
        this.label = wheelPanel.getWheelLabel();
        this.ballPanel = wheelPanel.getBallPanel();

        label.add(ballPanel);
    }

    // TODO: ball needs to persist location on window resize
    @Override
    public void componentResized(ComponentEvent e) {
        iconSize = wheelPanel.getWheelSize();

        // What a ridiculous number aye?
        ballRadius = (int) Math.round(iconSize * 0.0375);
        ballPanel.setRadius(ballRadius);

        Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
        label.setIcon(newIcon);
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
