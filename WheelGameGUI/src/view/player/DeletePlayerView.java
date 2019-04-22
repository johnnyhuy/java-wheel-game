package view.player;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class DeletePlayerView extends SubscriptionView {
    private final GameEngine gameEngine;

    public DeletePlayerView(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void render() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(240, 300));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JLabel playerNameLabel = new JLabel("Player");
        playerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        frame.add(playerNameLabel);

        JComboBox<String> playersCombo = new JComboBox<>();
        playersCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        playersCombo.setMaximumSize(new Dimension(560, 30));
        for (Player player : gameEngine.getAllPlayers()) {
            playersCombo.addItem(player.getPlayerName());
        }
        frame.add(playersCombo);

        frame.add(frame);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Integer item) {

    }
}
