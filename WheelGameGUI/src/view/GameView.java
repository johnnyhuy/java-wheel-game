package view;

import controller.GameController;
import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.frame.GameFrame;
import view.component.panel.SummaryPanel;
import view.component.panel.ToolbarPanel;
import view.component.panel.WheelPanel;
import view.component.table.TableModel;
import view.component.table.TableRenderer;
import view.listener.game.GameFrameListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static helper.CollectionHelper.toList;
import static helper.StringHelper.capitalize;

public class GameView extends SubscriptionView {
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private DefaultTableModel dtm;
    private SummaryPanel summaryPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private GameFrame frame;
    private List<Player> players;
    private ToolbarPanel toolbar;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
        this.players = toList(gameEngine.getAllPlayers());
    }

    @Override
    public void render() {
        // TODO: set minimum frame size
        frame = new GameFrame(this, playerController);
        final int padding = 5;

        toolbar = new ToolbarPanel(gameController, gameEngine, padding);
        frame.add(toolbar, BorderLayout.NORTH);

        WheelPanel wheelPanel = new WheelPanel(gameEngine, padding * 10);
        frame.add(wheelPanel);

        summaryPanel = new SummaryPanel(frame, wheelPanel);
        paintSummaryPanel();
        frame.add(summaryPanel, BorderLayout.EAST);

        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BorderLayout());
        statusBar.setBackground(Color.LIGHT_GRAY);
        frame.add(statusBar, BorderLayout.SOUTH);

        JPanel eastStatusBar = new JPanel();
        eastStatusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.add(eastStatusBar, BorderLayout.WEST);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JLabel dateLabel = new JLabel(dateFormat.format(new Date()));
        eastStatusBar.add(dateLabel);

        frame.addComponentListener(new GameFrameListener(summaryPanel));

        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameController, gameEngine, this, wheelPanel));
    }

    private void paintSummaryPanel() {
        DefaultTableModel dtm = new TableModel();
        dtm.addColumn("Name");
        dtm.addColumn("Points");
        dtm.addColumn("Bet");

        for (Player player : players) {
            String playerBet = "";
            if (player.getBetType() != null) {
                playerBet = player.getBet() + " on " + capitalize(player.getBetType().name());
            }

            Object[] row = new Object[]{
                player.getPlayerName(),
                player.getPoints(),
                playerBet
            };

            dtm.addRow(row);
        }

        table = new JTable();
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.setModel(dtm);
        table.setRowHeight(40);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setBackground(null);
        summaryPanel.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void onNext(Boolean success) {
        players = toList(gameEngine.getAllPlayers());

        toolbar.revalidate();
        toolbar.repaint();

        summaryPanel.removeAll();
        paintSummaryPanel();
        summaryPanel.revalidate();
        summaryPanel.repaint();

        getSubscription().request(1);
    }
}
