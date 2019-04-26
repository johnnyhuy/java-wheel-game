package view;

import controller.GameController;
import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.GameFrame;
import view.component.SummaryPanel;
import view.component.WheelPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static helper.CollectionHelper.toList;

public class GameView extends SubscriptionView {
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private DefaultTableModel dtm;
    private JPanel summaryPanel;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        final GameFrame frame = new GameFrame(this, playerController);
        final int padding = 5;

        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BorderLayout());
        toolbar.setBackground(Color.LIGHT_GRAY);
        toolbar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY), BorderFactory.createEmptyBorder(padding, padding / 2, padding, padding / 2)));
        frame.add(toolbar, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.LIGHT_GRAY);
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        toolbar.add(buttons, BorderLayout.WEST);

        JButton spinButton = new JButton("Spin");
        buttons.add(spinButton, BorderLayout.WEST);

        JComboBox<String> playersCombo = new JComboBox<>();
        buttons.add(playersCombo, BorderLayout.CENTER);

        List<Player> players = toList(gameEngine.getAllPlayers());
        for (Player player : players) {
            playersCombo.addItem(player.getPlayerName());
        }

        WheelPanel wheelPanel = new WheelPanel(padding * 8);
        frame.add(wheelPanel);

        summaryPanel = new SummaryPanel(wheelPanel);
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
