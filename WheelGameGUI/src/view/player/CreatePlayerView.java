package view.player;

import controller.PlayerController;
import model.interfaces.GameEngine;
import view.SubscriptionView;
import view.component.LabelPanel;
import view.listener.CloseWindowListener;
import view.listener.StorePlayerListener;

import javax.swing.*;
import java.awt.*;

public class CreatePlayerView extends SubscriptionView {
    private GameEngine gameEngine;
    private PlayerController playerController;

    public CreatePlayerView(GameEngine gameEngine, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        // TODO: set minimum frame size
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(360, 250));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Create Player");

        JPanel inputForm = new JPanel();
        inputForm.setLayout(new BoxLayout(inputForm, BoxLayout.Y_AXIS));
        inputForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        frame.add(inputForm, BorderLayout.NORTH);

        LabelPanel playerNameLabel = new LabelPanel("Player Name");
        inputForm.add(playerNameLabel);

        JTextField playerName = new JTextField();
        inputForm.add(playerName);

        LabelPanel playerPointsLabel = new LabelPanel("Player Points");
        playerPointsLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        inputForm.add(playerPointsLabel);

        JTextField playerPoints = new JTextField();
        inputForm.add(playerPoints);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 10));
        frame.add(southPanel, BorderLayout.SOUTH);

        JPanel actionButtons = new JPanel();
        actionButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        southPanel.add(actionButtons, BorderLayout.EAST);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CloseWindowListener(frame));
        actionButtons.add(cancelButton);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new StorePlayerListener(frame, playerController, playerName, playerPoints));
        actionButtons.add(createButton);
    }

    @Override
    public void onNext(Boolean success) {

    }
}
