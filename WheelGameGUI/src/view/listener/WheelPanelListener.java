package view.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WheelPanelListener implements ComponentListener {
    private final ImageIcon icon;
    private final JLabel label;
    private JPanel panel;
    private int padding;

    public WheelPanelListener(JPanel panel, ImageIcon icon, JLabel label, int padding) {
        this.panel = panel;
        this.icon = icon;
        this.label = label;
        this.padding = padding;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int iconSize = panel.getWidth() > panel.getHeight() ? panel.getHeight() - (padding * 2) : panel.getWidth() - (padding * 2);
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
