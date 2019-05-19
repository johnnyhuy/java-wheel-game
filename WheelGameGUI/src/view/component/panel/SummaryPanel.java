package view.component.panel;

import model.GameLogger;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.Updatable;
import view.component.frame.GameFrame;
import view.component.table.TableModel;
import view.component.table.TableRenderer;

import javax.swing.*;
import java.awt.*;

import static helper.CollectionHelper.isEmpty;
import static helper.StringHelper.capitalize;

public class SummaryPanel extends JPanel implements Updatable {
    private final JTable table;
    private final GameLogPanel outputPane;
    private final JScrollPane tableScrollPane;
    private GameEngine gameEngine;
    private GameFrame frame;
    private WheelPanel wheelPanel;

    public SummaryPanel(GameEngine gameEngine, GameLogger gameLogger, GameFrame frame, WheelPanel wheelPanel) {
        this.gameEngine = gameEngine;
        this.frame = frame;
        this.wheelPanel = wheelPanel;

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
        setLayout(new BorderLayout());

        table = new JTable();
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.setRowHeight(40);
        table.setModel(populateTable());

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(table.getPreferredSize().width, 175));
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tableScrollPane.setBackground(null);
        tableScrollPane.setWheelScrollingEnabled(true);
        add(tableScrollPane, BorderLayout.NORTH);

        outputPane = new GameLogPanel(gameLogger);
        add(outputPane, BorderLayout.CENTER);
    }

    private TableModel populateTable() {
        TableModel dtm = new TableModel();
        dtm.addColumn("Name");
        dtm.addColumn("Points");
        dtm.addColumn("Bet");

        for (Player player : gameEngine.getAllPlayers()) {
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

        dtm.fireTableDataChanged();

        return dtm;
    }

    public void setSize() {
        setPreferredSize(new Dimension(frame.getWidth() / 3, wheelPanel.getHeight()));
    }

    @Override
    public void update() {
        table.setModel(populateTable());

        if (isEmpty(gameEngine.getAllPlayers())) {
            tableScrollPane.setVisible(false);
        } else {
            tableScrollPane.setVisible(true);
        }

        revalidate();
        repaint();
    }
}
