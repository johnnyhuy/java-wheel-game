package view.listener.player;

import controller.PlayerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPlayerListener implements ActionListener {
    private PlayerController playerController;

    public ListPlayerListener(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerController.list();
    }
}
