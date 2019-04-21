package view;

import view.component.Padding;
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

        JPanel wheelPanel = new JPanel();
        Padding padding = new Padding(20);
        wheelPanel.add(label);
        wheelPanel.setBorder(padding);
        wheelPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int iconSize = wheelPanel.getWidth() > wheelPanel.getHeight() ? wheelPanel.getHeight() - padding.getTotalSize() : wheelPanel.getWidth() - padding.getTotalSize();
                Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
                label.setIcon(newIcon);
            }
        });

        JPanel summaryPanel = new JPanel();
        summaryPanel.setPreferredSize(new Dimension(200, 800));
        summaryPanel.setBackground(Color.darkGray);

        JLabel summaryTitle = new JLabel("Test");
        summaryPanel.add(summaryTitle);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(summaryPanel, BorderLayout.LINE_END);
        add(wheelPanel, BorderLayout.CENTER);
    }
}
