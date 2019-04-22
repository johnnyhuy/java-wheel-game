package view.player;

import controller.PlayerController;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.UUID;
import java.util.concurrent.Flow;

public class CreatePlayerView extends SubscriptionView {
    private GameEngine gameEngine;
    private PlayerController playerController;

    public CreatePlayerView(GameEngine gameEngine, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(240, 300));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JPanel inputForm = new JPanel();
        inputForm.setSize(300, 250);
        inputForm.setLayout(new GridBagLayout());
        frame.add(inputForm, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        JLabel playerNameLabel = new JLabel("Player Name");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        inputForm.add(playerNameLabel, gbc);

        JTextField playerName = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        playerName.setBorder(BorderFactory.createCompoundBorder(playerName.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        inputForm.add(playerName, gbc);

        JLabel playerPointsLabel = new JLabel("Player Points");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        inputForm.add(playerPointsLabel, gbc);

        JTextField playerPoints = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 10);
        playerPoints.setPreferredSize(new Dimension(inputForm.getWidth(), 40));
        playerPoints.setBorder(BorderFactory.createCompoundBorder(playerPoints.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        inputForm.add(playerPoints, gbc);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        frame.add(southPanel, BorderLayout.SOUTH);

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

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = new SimplePlayer(UUID.randomUUID().toString(), playerName.getText(), Integer.parseInt(playerPoints.getText()));
                playerController.store(player);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        actionButtons.add(createButton);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Integer item) {

    }
}