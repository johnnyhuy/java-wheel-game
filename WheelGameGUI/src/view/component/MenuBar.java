package view.component;

import controller.PlayerController;
import view.GameView;
import view.listener.CreatePlayerListener;
import view.listener.DeletePlayerListener;
import view.listener.ListPlayerListener;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    public MenuBar(GameView gameView, PlayerController playerController) {
        JMenu gameMenu = new JMenu("Game");

        gameMenu.setMnemonic(KeyEvent.VK_I);
        this.add(gameMenu);

        JMenuItem listPlayer = new JMenuItem("Players", KeyEvent.VK_P);
        listPlayer.addActionListener(new ListPlayerListener(playerController));
        gameMenu.add(listPlayer);

        JMenuItem newPlayer = new JMenuItem("New Player", KeyEvent.VK_N);
        newPlayer.addActionListener(new CreatePlayerListener(playerController));
        newPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(newPlayer);

        JMenuItem removePlayer = new JMenuItem("Remove Player", KeyEvent.VK_N);
        removePlayer.addActionListener(new DeletePlayerListener(playerController));
        removePlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(removePlayer);
    }
}
