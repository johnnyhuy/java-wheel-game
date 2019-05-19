package view.listener.game;

import model.interfaces.Slot;
import view.component.label.BallLabel;
import view.component.panel.BallPanel;
import view.component.panel.WheelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static helper.ObjectHelper.exists;

public class WheelPanelListener implements ComponentListener {
    private final ImageIcon icon;
    private final BallLabel ballLabel;
    private WheelPanel wheelPanel;
    private BallPanel ballPanel;

    public WheelPanelListener(WheelPanel wheelPanel) {
        this.wheelPanel = wheelPanel;
        this.icon = wheelPanel.getIcon();
        this.ballLabel = wheelPanel.getBallLabel();
        this.ballPanel = wheelPanel.getBallPanel();

        ballLabel.add(ballPanel);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int ballSize = wheelPanel.getBallSize();

        // What a ridiculous number aye?
        int ballRadius = (int) Math.round(ballSize * 0.0375);
        ballPanel.setRadius(ballRadius);

        Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(ballSize, ballSize, Image.SCALE_DEFAULT));
        ballLabel.setIcon(newIcon);

        Slot savedSlot = wheelPanel.getSavedSlot();
        if (exists(savedSlot)) {
            wheelPanel.setBallAngle(savedSlot);
        }
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
