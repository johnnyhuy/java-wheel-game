package view.component.panel;

import model.GameLogger;

import javax.swing.*;
import java.awt.*;

class GameLogPanel extends JPanel {
    GameLogPanel(GameLogger gameLogger) {
        setLayout(new BorderLayout());

        JLabel outputTitle = new JLabel("Game Log");
        outputTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(outputTitle, BorderLayout.NORTH);

        JTextPane textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        textPane.setOpaque(false);
        textPane.setFocusable(false);
        gameLogger.setTextPane(textPane);

        JPanel innerScrollPanel = new JPanel();
        innerScrollPanel.setLayout(new BoxLayout(innerScrollPanel, BoxLayout.Y_AXIS));
        innerScrollPanel.add(textPane);

        JScrollPane outputScrollPane = new JScrollPane(innerScrollPanel);
        outputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outputScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outputScrollPane.setBackground(null);
        outputScrollPane.setWheelScrollingEnabled(true);
        add(outputScrollPane, BorderLayout.CENTER);
    }
}
