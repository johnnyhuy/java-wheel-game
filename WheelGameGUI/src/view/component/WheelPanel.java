package view.component;

import model.interfaces.GameEngine;
import view.listener.WheelPanelListener;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class WheelPanel extends JPanel {
    private final ImageIcon icon;
    private final JLabel wheelLabel;
    private final int padding;
    private final BallPanel ballPanel;

    public WheelPanel(GameEngine gameEngine, int padding) {
        this.padding = padding;
        this.ballPanel = new BallPanel();

        final String wheelFileLocation = "resources/images/Basic_roulette_wheel_1024x1024.png";
        final URL location = getClass().getClassLoader().getResource(wheelFileLocation);

        setLayout(new BorderLayout());

        icon = new ImageIcon(Objects.requireNonNull(location));
        wheelLabel = new JLabel();
        wheelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wheelLabel.setLayout(null);

        add(wheelLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        addComponentListener(new WheelPanelListener(gameEngine, this));
    }

    public JLabel getWheelLabel() {
        return this.wheelLabel;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public int getPadding() {
        return this.padding;
    }

    public BallPanel getBallPanel() {
        return this.ballPanel;
    }

    public int getWheelSize() {
        return getWidth() > getHeight() ? getHeight() - (padding * 2) : getWidth() - (padding * 2);
    }
}
