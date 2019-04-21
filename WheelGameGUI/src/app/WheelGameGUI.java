package app;

import controller.GameController;
import controller.PlayerController;

import javax.swing.*;

public class WheelGameGUI {
    public static void main(String[] args) {
        PlayerController playerController = new PlayerController();
        GameController gameController = new GameController(playerController);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameController.start();
            }
        });
    }
}
