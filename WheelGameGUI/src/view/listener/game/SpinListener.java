package view.listener.game;

import controller.GameController;
import view.component.panel.ToolbarPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinListener implements ActionListener {
    private GameController gameController;
    private ToolbarPanel toolbar;

    public SpinListener(GameController gameController, ToolbarPanel toolbar) {
        this.gameController = gameController;
        this.toolbar = toolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolbar.setVisible(false);
        gameController.spin();
    }
}
