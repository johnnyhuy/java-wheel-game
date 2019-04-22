package view.player;

import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.Flow;

public class ListPlayerView extends SubscriptionView {
    private final GameEngine gameEngine;
    private PlayerController playerController;

    public ListPlayerView(GameEngine gameEngine, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.playerController = playerController;
    }

    @Override
    public void render() {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(580, 450));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.addColumn("ID");
        dtm.addColumn("Name");
        dtm.addColumn("Points");
        table.setModel(dtm);

        for (Player player : gameEngine.getAllPlayers()) {
            Object[] row = new Object[]{
                player.getPlayerId(),
                player.getPlayerName(),
                player.getPoints()
            };

            dtm.addRow(row);
        }

        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(300, frame.getHeight()));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        frame.add(southPanel, BorderLayout.SOUTH);

        JPanel actionButtons = new JPanel();
        southPanel.add(actionButtons, BorderLayout.EAST);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        actionButtons.add(cancelButton);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new DeletePlayerView(gameEngine).render();
                    }
                });
            }
        });
        actionButtons.add(removeButton);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new CreatePlayerView(gameEngine, playerController).render();
                    }
                });
            }
        });
        actionButtons.add(createButton);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Integer item) {

    }
}
