package view.component;

import controller.PlayerController;
import view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    public MenuBar(GameView gameView, PlayerController playerController) {
        JMenu gameMenu = new JMenu("Game");

        gameMenu.setMnemonic(KeyEvent.VK_I);
        this.add(gameMenu);

        JMenuItem listPlayer = new JMenuItem("Players", KeyEvent.VK_P);
        listPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerController.list();
            }
        });
        gameMenu.add(listPlayer);

        JMenuItem newPlayer = new JMenuItem("New Player", KeyEvent.VK_N);
        newPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerController.create();
            }
        });
        newPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(newPlayer);

        JMenuItem removePlayer = new JMenuItem("Remove Player", KeyEvent.VK_N);
        removePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        playerController.remove();
                    }
                });
            }
        });
        removePlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(removePlayer);
    }
}
