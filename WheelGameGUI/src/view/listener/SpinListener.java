package view.listener;

import controller.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinListener implements ActionListener {
    private GameController gameController;

    public SpinListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.spin();
    }
}
