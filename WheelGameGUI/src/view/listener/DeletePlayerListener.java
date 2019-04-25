package view.listener;

import controller.PlayerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePlayerListener implements ActionListener {
    private PlayerController playerController;

    public DeletePlayerListener(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerController.delete();
    }
}
