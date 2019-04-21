package view;

import controller.GameController;
import controller.PlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.Padding;
import view.component.ViewMenuBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Flow;

public class GameView extends View implements Flow.Subscriber<Integer> {
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private Flow.Subscription subscription;
    private JFrame frame;
    private DefaultTableModel dtm;
    private JPanel summaryPanel;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
    }

    public void start() {
        frame = new JFrame();
        frame.setSize(720, 600);
        frame.setJMenuBar(new ViewMenuBar(this, playerController));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        URL location = getClass().getClassLoader().getResource("resources/images/Basic_roulette_wheel_1024x1024.png");
        final ImageIcon icon = new ImageIcon(Objects.requireNonNull(location));
        JLabel label = new JLabel();

        JPanel wheelPanel = new JPanel();
        Padding padding = new Padding(20);
        wheelPanel.add(label);
        wheelPanel.setBorder(padding);
        wheelPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int iconSize = wheelPanel.getWidth() > wheelPanel.getHeight() ? wheelPanel.getHeight() - padding.getTotalSize() : wheelPanel.getWidth() - padding.getTotalSize();
                Icon newIcon = new ImageIcon(icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_DEFAULT));
                label.setIcon(newIcon);
            }
        });
        frame.add(wheelPanel, BorderLayout.CENTER);

        summaryPanel = new JPanel();
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        summaryPanel.setPreferredSize(new Dimension(240, wheelPanel.getHeight()));
        summaryPanel.setBackground(Color.lightGray);
        frame.add(summaryPanel, BorderLayout.LINE_END);

        paintSummaryPanel();
    }

    private void paintSummaryPanel() {
        JLabel summaryTitle = new JLabel("Sexy wheel game");
        summaryPanel.add(summaryTitle);

        JTable table = new JTable();
        dtm = new DefaultTableModel(0, 0);
        dtm.addColumn("Name");
        dtm.addColumn("Points");
        table.setModel(dtm);

        for (Player player : gameEngine.getAllPlayers()) {
            dtm.addRow(new Object[]{player.getPlayerName(), player.getPoints()});
        }

        summaryPanel.add(table);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        summaryPanel.removeAll();
        paintSummaryPanel();
        summaryPanel.revalidate();
        summaryPanel.repaint();

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onComplete() {
    }
}
