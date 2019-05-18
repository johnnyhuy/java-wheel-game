package view.component.menu;

import controller.PlayerController;
import view.GameView;
import view.listener.player.CreatePlayerListener;
import view.listener.player.DeletePlayerListener;
import view.listener.player.ListPlayerListener;
import view.listener.window.CloseWindowListener;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GameMenuBar extends JMenuBar {
    public GameMenuBar(JFrame frame, GameView gameView, PlayerController playerController) {
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        add(gameMenu);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_G);
        add(helpMenu);

        JMenuItem listPlayer = new JMenuItem("Players", KeyEvent.VK_P);
        listPlayer.addActionListener(new ListPlayerListener(playerController));
        gameMenu.add(listPlayer);

        JMenuItem newPlayer = new JMenuItem("New Player", KeyEvent.VK_N);
        newPlayer.addActionListener(new CreatePlayerListener(playerController));
        newPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(newPlayer);

        JMenuItem removePlayer = new JMenuItem("Remove Player", KeyEvent.VK_R);
        removePlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        removePlayer.addActionListener(new DeletePlayerListener(playerController));
        gameMenu.add(removePlayer);

        gameMenu.addSeparator();

        JMenuItem closeWindow = new JMenuItem("Exit", KeyEvent.VK_R);
        closeWindow.addActionListener(new CloseWindowListener(frame));
        gameMenu.add(closeWindow);
    }
}
