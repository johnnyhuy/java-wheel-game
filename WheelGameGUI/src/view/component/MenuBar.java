package view.component;

import controller.PlayerController;
import view.GameView;
import view.listener.CloseWindowListener;
import view.listener.CreatePlayerListener;
import view.listener.ListPlayerListener;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    public MenuBar(JFrame frame, GameView gameView, PlayerController playerController) {
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        this.add(gameMenu);

        JMenuItem listPlayer = new JMenuItem("Players", KeyEvent.VK_P);
        listPlayer.addActionListener(new ListPlayerListener(playerController));
        gameMenu.add(listPlayer);

        JMenuItem newPlayer = new JMenuItem("New Player", KeyEvent.VK_N);
        newPlayer.addActionListener(new CreatePlayerListener(playerController));
        newPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(newPlayer);

        JMenuItem removePlayer = new JMenuItem("Remove Player", KeyEvent.VK_R);
        removePlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(removePlayer);

        gameMenu.addSeparator();

        JMenuItem closeWindow = new JMenuItem("Exit", KeyEvent.VK_R);
        closeWindow.addActionListener(new CloseWindowListener(frame));
        gameMenu.add(closeWindow);
    }
}
