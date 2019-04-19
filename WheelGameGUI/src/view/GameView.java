package view;

import view.component.ViewMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.Objects;

public class GameView extends View {
    public GameView() {
        setSize(640, 480);
        setJMenuBar(new ViewMenuBar(this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        URL location = getClass().getClassLoader().getResource("resources/images/Basic_roulette_wheel_1024x1024.png");
        final ImageIcon icon = new ImageIcon(Objects.requireNonNull(location));
        JLabel label = new JLabel();

        BorderLayout layout = new BorderLayout();

        JPanel wheelPanel = new JPanel();
        wheelPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(wheelPanel.getWidth(), wheelPanel.getHeight(), Image.SCALE_DEFAULT));
                label.setIcon(newIcon);
            }
        });

        JPanel summaryPanel = new JPanel();
        summaryPanel.setBackground(Color.darkGray);

        wheelPanel.setLayout(layout);
        wheelPanel.add(label, BorderLayout.CENTER);
//        wheelPanel.add(summaryPanel, BorderLayout.LINE_END);

        add(wheelPanel);
    }
}
