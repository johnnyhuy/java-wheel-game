package view.component.frame;

import controller.PlayerController;
import view.GameView;
import view.component.menu.GameMenuBar;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(GameView gameView, PlayerController playerController) {
        setSize(780, 600);
        setJMenuBar(new GameMenuBar(this, gameView, playerController));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
    }
}
