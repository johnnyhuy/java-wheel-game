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

        JPanel inputForm = new JPanel();
        inputForm.setLayout(new BoxLayout(inputForm, BoxLayout.Y_AXIS));
//        inputForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        frame.add(inputForm, BorderLayout.NORTH);

        JLabel playerNameLabel = new JLabel("Player Name");
//        inputForm.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        inputForm.add(playerNameLabel);

        JTextField playerName = new JTextField();
//        playerName.setBorder(BorderFactory.createCompoundBorder(playerName.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        inputForm.add(playerName);

        JLabel playerPointsLabel = new JLabel("Player Points");
        playerPointsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        inputForm.add(playerPointsLabel);

        JTextField playerPoints = new JTextField();
        playerPoints.setPreferredSize(new Dimension(inputForm.getWidth(), 40));
        playerPoints.setBorder(BorderFactory.createCompoundBorder(playerPoints.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
//        inputForm.add(playerPoints);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
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
    public void onNext(Integer item) {

    }
}
