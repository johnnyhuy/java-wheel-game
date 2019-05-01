package view;

import controller.GameController;
import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.*;
import view.listener.GameFrameListener;
import view.listener.SpinListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static helper.CollectionHelper.toList;

public class GameView extends SubscriptionView {
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private DefaultTableModel dtm;
    private SummaryPanel summaryPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private GameFrame frame;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
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

        JPanel toolbarButtons = new JPanel();
        toolbarButtons.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        toolbar.add(toolbarButtons, BorderLayout.WEST);

        JButton spinButton = new JButton("Spin");
        spinButton.addActionListener(new SpinListener(gameController));
        toolbarButtons.add(spinButton, BorderLayout.WEST);

        JComboBox<String> playersCombo = new JComboBox<>();
        toolbarButtons.add(playersCombo, BorderLayout.CENTER);

        List<Player> players = toList(gameEngine.getAllPlayers());
        for (Player player : players) {
            playersCombo.addItem(player.getPlayerName());
        }

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
    }

    private void paintSummaryPanel() {
        DefaultTableModel dtm = new TableModel();
        dtm.addColumn("ID");
        dtm.addColumn("Name");
        dtm.addColumn("Points");

        table = new JTable();
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.setModel(dtm);
        table.setRowHeight(40);

        for (Player player : gameEngine.getAllPlayers()) {
            Object[] row = new Object[]{
                player.getPlayerId(),
                player.getPlayerName(),
                player.getPoints()
            };

            dtm.addRow(row);
        }

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(null);
        summaryPanel.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void onNext(Integer item) {
        summaryPanel.removeAll();
        paintSummaryPanel();
        getSubscription().request(1);
    }
}
