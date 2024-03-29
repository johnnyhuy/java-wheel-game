package view.player;

import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;
import view.component.frame.DeletePlayerFrame;
import view.listener.player.DestroyPlayerListener;
import view.listener.window.CloseWindowListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static helper.CollectionHelper.isEmpty;
import static helper.CollectionHelper.toList;

public class DeletePlayerView extends SubscriptionView {
    private final GameEngine gameEngine;
    private PlayerController playerController;

    public DeletePlayerView(GameEngine gameEngine, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        DeletePlayerFrame frame = new DeletePlayerFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        panel.add(northPanel, BorderLayout.NORTH);

        JLabel noPlayersLabel = new JLabel();
        noPlayersLabel.setText("No players available");
        noPlayersLabel.setForeground(Color.RED);
        noPlayersLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        noPlayersLabel.setVisible(false);
        northPanel.add(noPlayersLabel);

        JLabel playerNameLabel = new JLabel("Player");
        playerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        northPanel.add(playerNameLabel);

        JComboBox<String> playersCombo = new JComboBox<>();
        playersCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        northPanel.add(playersCombo);

        List<Player> players = toList(gameEngine.getAllPlayers());
        for (Player player : players) {
            playersCombo.addItem(player.getPlayerName());
        }

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));
        panel.add(southPanel, BorderLayout.SOUTH);

        JPanel actionButtons = new JPanel();
        actionButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        southPanel.add(actionButtons, BorderLayout.EAST);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CloseWindowListener(frame));
        actionButtons.add(cancelButton);

        JButton removeButton = new JButton("Remove");
        actionButtons.add(removeButton);
        removeButton.addActionListener(new DestroyPlayerListener(frame, playerController, players, playersCombo));

        if (isEmpty(players)) {
            removeButton.setVisible(false);
            playerNameLabel.setVisible(false);
            playersCombo.setVisible(false);
            noPlayersLabel.setVisible(true);
        }
    }

    @Override
    public void onNext(Boolean success) {

    }
}
