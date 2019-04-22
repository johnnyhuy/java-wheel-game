package view.player;

import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

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
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(360, 150));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        panel.add(northPanel, BorderLayout.NORTH);

        JLabel playerNameLabel = new JLabel("Player");
        playerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        northPanel.add(playerNameLabel);

        JComboBox<String> playersCombo = new JComboBox<>();
        playersCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        playersCombo.setMaximumSize(new Dimension(560, 30));
        northPanel.add(playersCombo);

        List<Player> players = toList(gameEngine.getAllPlayers());
        for (Player player : players) {
            playersCombo.addItem(player.getPlayerName());
        }

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        panel.add(southPanel, BorderLayout.SOUTH);

        JPanel actionButtons = new JPanel();
        southPanel.add(actionButtons, BorderLayout.EAST);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        actionButtons.add(cancelButton);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerController.destroy(players.get(playersCombo.getSelectedIndex()));
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        actionButtons.add(removeButton);
    }

    @Override
    public void onNext(Integer item) {

    }
}
