package view;

import view.component.ViewMenuBar;

import javax.swing.*;

public class GameView extends View {
    public GameView() {
        setSize(640, 480);
        setJMenuBar(new ViewMenuBar(this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
