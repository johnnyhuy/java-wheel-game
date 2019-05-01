package view.player;

import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;
import view.component.TableModel;
import view.component.TableRenderer;
import view.listener.CloseWindowListener;
import view.listener.CreatePlayerListener;
import view.listener.DeletePlayerListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListPlayerView extends SubscriptionView {
    private final GameEngine gameEngine;
    private PlayerController playerController;
    private JScrollPane scrollPane;
    private JTable table;

    public ListPlayerView(GameEngine gameEngine, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        // TODO: set minimum frame size
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(580, 450));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("List Players");

        scrollPane = new JScrollPane();
        paintPlayerTable();
        scrollPane.setPreferredSize(new Dimension(300, frame.getHeight()));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        scrollPane.setBackground(null);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        frame.add(southPanel, BorderLayout.SOUTH);

        JPanel actionButtons = new JPanel();
        southPanel.add(actionButtons, BorderLayout.EAST);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CloseWindowListener(frame));
        actionButtons.add(cancelButton);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new DeletePlayerListener(playerController));
        actionButtons.add(removeButton);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new CreatePlayerListener(playerController));
        actionButtons.add(createButton);
    }

    private void paintPlayerTable() {
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

        scrollPane.setViewportView(table);
    }

    @Override
    public void onNext(Integer item) {
        table.removeAll();
        paintPlayerTable();
        getSubscription().request(1);
    }
}
