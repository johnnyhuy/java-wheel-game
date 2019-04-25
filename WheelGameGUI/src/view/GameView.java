package view;

import controller.GameController;
import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.MenuBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.Objects;

public class GameView extends SubscriptionView {
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private JFrame frame;
    private DefaultTableModel dtm;
    private JPanel summaryPanel;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        frame = new JFrame();
        frame.setSize(780, 600);
        frame.setJMenuBar(new MenuBar(this, playerController));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        final String wheelFileLocation = "resources/images/Basic_roulette_wheel_1024x1024.png";
        final URL location = getClass().getClassLoader().getResource(wheelFileLocation);
        final ImageIcon icon = new ImageIcon(Objects.requireNonNull(location));
        JLabel label = new JLabel();

        final int wheelPadding = 40;
        JPanel wheelPanel = new JPanel();
        wheelPanel.add(label);
        wheelPanel.setBorder(BorderFactory.createEmptyBorder(wheelPadding, wheelPadding, wheelPadding, wheelPadding));
        wheelPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int iconSize = wheelPanel.getWidth() > wheelPanel.getHeight() ? wheelPanel.getHeight() - (wheelPadding * 2) : wheelPanel.getWidth() - (wheelPadding * 2);
                Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
                label.setIcon(newIcon);
            }
        });
        frame.add(wheelPanel, BorderLayout.CENTER);

        summaryPanel = new JPanel();
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        summaryPanel.setPreferredSize(new Dimension(240, wheelPanel.getHeight()));
        summaryPanel.setBackground(Color.lightGray);
        frame.add(summaryPanel, BorderLayout.EAST);

        paintSummaryPanel();
    }

    private void paintSummaryPanel() {
        JLabel summaryTitle = new JLabel("Sexy wheel game");
        summaryPanel.add(summaryTitle);

        JTable playersTable = new JTable();
        dtm = new DefaultTableModel(0, 0);
        dtm.addColumn("Name");
        dtm.addColumn("Points");
        playersTable.setModel(dtm);

        for (Player player : gameEngine.getAllPlayers()) {
            Object[] row = new Object[]{
                player.getPlayerName(),
                player.getPoints()
            };

            dtm.addRow(row);
        }

        summaryPanel.add(playersTable);
    }

    @Override
    public void onNext(Integer item) {
        summaryPanel.removeAll();
        paintSummaryPanel();
        summaryPanel.revalidate();
        summaryPanel.repaint();
        getSubscription().request(1);
    }
}
