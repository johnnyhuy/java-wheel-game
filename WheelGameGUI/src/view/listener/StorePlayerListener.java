package view.listener;

import controller.PlayerController;
import model.SimplePlayer;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.UUID;

public class StorePlayerListener implements ActionListener {
    private JFrame frame;
    private PlayerController playerController;
    private JTextField playerName;
    private JTextField playerPoints;

    public StorePlayerListener(JFrame frame, PlayerController playerController, JTextField playerName, JTextField playerPoints) {
        this.frame = frame;
        this.playerController = playerController;
        this.playerName = playerName;
        this.playerPoints = playerPoints;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = new SimplePlayer(UUID.randomUUID().toString(), playerName.getText(), Integer.parseInt(playerPoints.getText()));
        playerController.store(player);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
