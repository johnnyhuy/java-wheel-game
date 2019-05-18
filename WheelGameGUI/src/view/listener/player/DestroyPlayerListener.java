package view.listener.player;

import controller.PlayerController;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DestroyPlayerListener implements ActionListener {
    private JFrame frame;
    private PlayerController playerController;
    private Player player;

    public DestroyPlayerListener(JFrame frame, PlayerController playerController, Player player) {
        this.frame = frame;
        this.playerController = playerController;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerController.destroy(player);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
