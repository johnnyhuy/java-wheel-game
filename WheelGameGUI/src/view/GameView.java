package view;

import view.component.ViewMenuBar;

import javax.swing.*;
import java.awt.*;

public class GameView extends View {
    private final JTextArea text;
    private final JScrollPane scroll;

    public GameView() {
        setBounds(250, 250, 640, 480);
        setJMenuBar(new ViewMenuBar(this));

        text = new JTextArea(20, 50);
        text.setFont(new Font("Courier New", Font.PLAIN, 12));
        scroll = new JScrollPane(text);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
