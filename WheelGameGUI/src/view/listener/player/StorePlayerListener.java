package view.listener.player;

import controller.PlayerController;
import model.SimplePlayer;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.UUID;

import static helper.StringHelper.isInteger;

public class StorePlayerListener implements ActionListener {
    private JFrame frame;
    private PlayerController playerController;
    private JTextField playerName;
    private JTextField playerPoints;
    private JLabel errorLabel;

    public StorePlayerListener(JFrame frame, PlayerController playerController, JTextField playerName, JTextField playerPoints, JLabel errorLabel) {
        this.frame = frame;
        this.playerController = playerController;
        this.playerName = playerName;
        this.playerPoints = playerPoints;
        this.errorLabel = errorLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playerName.getText().length() == 0) {
            errorLabel.setText("Player name is empty");
            return;
        } else if (playerPoints.getText().length() == 0) {
            errorLabel.setText("Player points is empty");
            return;
        } else if (!isInteger(playerPoints.getText())) {
            errorLabel.setText("Player points must be an integer");
            return;
        }

        Player player = new SimplePlayer(UUID.randomUUID().toString(), playerName.getText(), Integer.parseInt(playerPoints.getText()));
        playerController.store(player);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
