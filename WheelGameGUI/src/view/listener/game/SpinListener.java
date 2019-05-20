package view.listener.game;

import controller.GameController;
import view.component.panel.ToolbarPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinListener implements ActionListener {
    private GameController gameController;
    private ToolbarPanel toolbar;

    public SpinListener(GameController gameController, ToolbarPanel toolbarPanel) {
        this.gameController = gameController;
        this.toolbar = toolbarPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolbar.setEnabled(false);
        gameController.spin();
    }
}
