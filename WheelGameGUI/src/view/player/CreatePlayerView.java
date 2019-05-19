package view.player;

import controller.PlayerController;
import view.SubscriptionView;
import view.component.frame.CreatePlayerFrame;
import view.component.panel.LabelPanel;
import view.listener.player.StorePlayerListener;
import view.listener.window.CloseWindowListener;

import javax.swing.*;
import java.awt.*;

public class CreatePlayerView extends SubscriptionView {
    private PlayerController playerController;

    public CreatePlayerView(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void render() {
        CreatePlayerFrame frame = new CreatePlayerFrame();

        JPanel inputForm = new JPanel();
        inputForm.setLayout(new BoxLayout(inputForm, BoxLayout.Y_AXIS));
        inputForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        frame.add(inputForm, BorderLayout.NORTH);

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        inputForm.add(errorLabel);

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
