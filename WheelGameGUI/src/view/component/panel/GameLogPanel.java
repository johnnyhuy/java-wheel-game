package view.component.panel;

import model.GameLogger;

import javax.swing.*;
import java.awt.*;

public class GameLogPanel extends JPanel {
    private GameLogger gameLogger;

    public GameLogPanel(GameLogger gameLogger) {
        this.gameLogger = gameLogger;

        setLayout(new BorderLayout());

        JLabel outputTitle = new JLabel("Game Log");
        outputTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(outputTitle, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        gameLogger.add(textArea);

        JPanel innerScrollPanel = new JPanel();
        innerScrollPanel.setLayout(new BoxLayout(innerScrollPanel, BoxLayout.Y_AXIS));
        innerScrollPanel.add(textArea);

        JScrollPane outputScrollPane = new JScrollPane(innerScrollPanel);
        outputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outputScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outputScrollPane.setBackground(null);
        outputScrollPane.setWheelScrollingEnabled(true);
        add(outputScrollPane, BorderLayout.CENTER);
    }
}
