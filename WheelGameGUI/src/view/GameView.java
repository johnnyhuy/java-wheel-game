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

        WheelPanel wheelPanel = new WheelPanel(40);
        summaryPanel = new SummaryPanel(wheelPanel);

        frame.add(wheelPanel);
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
