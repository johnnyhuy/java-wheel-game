package view.listener.player;

import controller.PlayerController;
import model.interfaces.Player;
import view.component.frame.DeletePlayerFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

public class DestroyPlayerListener implements ActionListener {
    private JFrame frame;
    private PlayerController playerController;
    private List<Player> players;
    private JComboBox<String> playersCombo;

    public DestroyPlayerListener(DeletePlayerFrame frame, PlayerController playerController, List<Player> players, JComboBox<String> playersCombo) {
        this.frame = frame;
        this.playerController = playerController;
        this.players = players;
        this.playersCombo = playersCombo;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        playerController.destroy(players.get(playersCombo.getSelectedIndex()));
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
