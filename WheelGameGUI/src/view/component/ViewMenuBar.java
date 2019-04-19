package view.component;

import view.GameView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ViewMenuBar extends JMenuBar {
    public ViewMenuBar(GameView gameView) {
        JMenu fileMenu = new JMenu("File");

        fileMenu.setMnemonic(KeyEvent.VK_I);
        this.add(fileMenu);

        JMenuItem newItem = new JMenuItem("New", KeyEvent.VK_N);

        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_DOWN_MASK
            | InputEvent.CTRL_DOWN_MASK));

        JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
        JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        JMenuItem closeItem = new JMenuItem("Close", KeyEvent.VK_C);
        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
    }
}
