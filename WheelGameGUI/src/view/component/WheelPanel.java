package view.component;

import model.interfaces.GameEngine;
import view.listener.WheelPanelListener;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class WheelPanel extends JPanel {
    public WheelPanel(GameEngine gameEngine, int padding) {
        final String wheelFileLocation = "resources/images/Basic_roulette_wheel_1024x1024.png";
        final URL location = getClass().getClassLoader().getResource(wheelFileLocation);

        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(location));
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setLayout(null);

        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        addComponentListener(new WheelPanelListener(gameEngine, this, icon, label, padding));
    }
}
