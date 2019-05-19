package view.component.panel;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.label.BallLabel;
import view.listener.game.WheelPanelListener;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class WheelPanel extends JPanel {
    private final ImageIcon icon;
    private final BallLabel ballLabel;
    private final int padding;
    private final BallPanel ballPanel;
    private HashMap<Slot, Double> wheelMap = new HashMap<>();
    private Slot savedSlot;

    public WheelPanel(GameEngine gameEngine, int padding) {
        this.padding = padding * 10;
        this.ballPanel = new BallPanel();

        final String wheelFileLocation = "resources/images/Basic_roulette_wheel_1024x1024.png";
        final URL location = getClass().getClassLoader().getResource(wheelFileLocation);

        setLayout(new BorderLayout());

        icon = new ImageIcon(Objects.requireNonNull(location));
        ballLabel = new BallLabel();

        add(ballLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        addComponentListener(new WheelPanelListener(this));

        final double angleIncrement = (double) 360 / Slot.WHEEL_SIZE;

        // Offset the angle to align the picture
        double angle = (angleIncrement / 2) - (angleIncrement * (double) (Slot.WHEEL_SIZE / 4));

        for (Slot slot : gameEngine.getWheelSlots()) {
            wheelMap.put(slot, angle);
            angle += angleIncrement;
        }
    }

    public BallLabel getBallLabel() {
        return this.ballLabel;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public BallPanel getBallPanel() {
        return this.ballPanel;
    }

    public int getBallSize() {
        return getWidth() > getHeight() ? getHeight() - (padding * 2) : getWidth() - (padding * 2);
    }

    public void setBallAngle(Slot slot) {
        Double angle = wheelMap.get(slot);
        JLabel wheelLabel = getBallLabel();
        int ballRadius = ballPanel.getRadius();
        int ballDiameter = ballPanel.getDiameter();
        int ballSize = getBallSize();

        int xOffset = Math.round((wheelLabel.getWidth() / 2) - ballRadius);
        int yOffset = Math.round((wheelLabel.getHeight() / 2) - ballRadius);

        double rads = Math.toRadians(angle);
        int radius = (ballSize / 2) - ballRadius;

        int x = Math.round((float) (xOffset + Math.cos(rads) * radius));
        int y = Math.round((float) (yOffset + Math.sin(rads) * radius));

        ballPanel.setBounds(x, y, ballDiameter, ballDiameter);
    }

    public Slot getSavedSlot() {
        return this.savedSlot;
    }

    public void saveSlot(Slot slot) {
        this.savedSlot = slot;
    }

    public void resetSavedSlot() {
        this.savedSlot = null;
    }
}
