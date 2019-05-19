package view.component.panel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.Updatable;
import view.component.frame.GameFrame;
import view.component.table.TableModel;
import view.component.table.TableRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static helper.StringHelper.capitalize;

public class SummaryPanel extends JPanel implements Updatable {
    private GameEngine gameEngine;
    private GameFrame frame;
    private WheelPanel wheelPanel;

    public SummaryPanel(GameEngine gameEngine, GameFrame frame, WheelPanel wheelPanel) {
        this.gameEngine = gameEngine;
        this.frame = frame;
        this.wheelPanel = wheelPanel;

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
        setSize();
        setLayout(new BorderLayout());
        paintTable();
    }

    private void paintTable() {
        DefaultTableModel dtm = new TableModel();
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

        JTable table = new JTable();
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.setModel(dtm);
        table.setRowHeight(40);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setBackground(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setSize() {
        setPreferredSize(new Dimension(frame.getWidth() / 3, wheelPanel.getHeight()));
    }

    @Override
    public void update() {
        removeAll();
        paintTable();
        revalidate();
        repaint();
    }
}
