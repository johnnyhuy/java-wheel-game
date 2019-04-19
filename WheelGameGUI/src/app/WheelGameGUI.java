package app;

import view.GameView;

import javax.swing.*;

public class WheelGameGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameView();
            }
        });
    }
}
