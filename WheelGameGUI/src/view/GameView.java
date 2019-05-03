package view;

import controller.GameController;
import controller.PlayerController;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.*;
import view.listener.BetListener;
import view.listener.GameFrameListener;
import view.listener.SpinListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static helper.CollectionHelper.toList;
import static helper.StringHelper.capitalize;

public class GameView extends SubscriptionView {
    private List<Player> betAvailablePlayers;
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private DefaultTableModel dtm;
    private SummaryPanel summaryPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private GameFrame frame;
    private List<Player> players;
    private JComboBox<String> playerCombo;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
        this.players = toList(gameEngine.getAllPlayers());
        this.betAvailablePlayers = toList(gameEngine.getAllPlayers());
    }

    @Override
    public void render() {
        // TODO: set minimum frame size
        frame = new GameFrame(this, playerController);
        final int padding = 5;

        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BorderLayout());
        toolbar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(padding, padding / 2, padding, padding / 2)));
        frame.add(toolbar, BorderLayout.NORTH);

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        toolbar.add(westPanel, BorderLayout.WEST);

        JButton spinButton = new JButton("Spin");
        spinButton.addActionListener(new SpinListener(gameController));
        westPanel.add(spinButton, BorderLayout.WEST);

        // TODO: hide this panel when all players have placed bets
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        toolbar.add(eastPanel, BorderLayout.EAST);

        JLabel betAmountLabel = new JLabel("Bet");
        betAmountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        eastPanel.add(betAmountLabel);

        JTextField betAmount = new JTextField();
        betAmount.setPreferredSize(new Dimension(100, 26));
        eastPanel.add(betAmount);

        playerCombo = new JComboBox<>();
        paintPlayers();
        eastPanel.add(playerCombo, BorderLayout.CENTER);

        JComboBox<String> betTypeCombo = new JComboBox<>();
        eastPanel.add(betTypeCombo, BorderLayout.CENTER);

        BetType[] betTypes = BetType.values();
        for (BetType betType : betTypes) {
            betTypeCombo.addItem(capitalize(betType.name()));
        }

        JButton betButton = new JButton("Place Bet");
        betButton.addActionListener(new BetListener(gameController, betAvailablePlayers, betTypes, playerCombo, betTypeCombo, betAmount));
        eastPanel.add(betButton, BorderLayout.WEST);

        WheelPanel wheelPanel = new WheelPanel(gameEngine, padding * 10);
        frame.add(wheelPanel);

        summaryPanel = new SummaryPanel(frame, wheelPanel);
        summaryPanel.setLayout(new BorderLayout());
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

        paintSummaryPanel();

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

    private void paintPlayers() {
        if (playerCombo.getItemCount() > 0) {
            playerCombo.removeAllItems();
        }

        for (Player player : betAvailablePlayers) {
            playerCombo.addItem(player.getPlayerName());
        }
    }

    @Override
    public void onNext(Object item) {
        players = toList(gameEngine.getAllPlayers());
        betAvailablePlayers = toList(gameEngine.getAllPlayers());

        paintPlayers();
        playerCombo.revalidate();
        playerCombo.repaint();

        summaryPanel.removeAll();
        paintSummaryPanel();
        summaryPanel.revalidate();
        summaryPanel.repaint();

        getSubscription().request(1);
    }
}
