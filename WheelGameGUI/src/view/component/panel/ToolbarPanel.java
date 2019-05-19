package view.component.panel;

import controller.GameController;
import model.BetTypeViewModel;
import model.GameLogger;
import model.PlayerViewModel;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.Updatable;
import view.listener.game.BetListener;
import view.listener.game.SpinListener;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

import static helper.BetHelper.getBetablePlayers;
import static helper.CollectionHelper.isEmpty;

public class ToolbarPanel extends JPanel implements Updatable {
    private final JComboBox<PlayerViewModel> playerCombo;
    private final JLabel noPlayersLabel;
    private final JLabel betAmountLabel;
    private final JTextField betAmount;
    private final JButton betButton;
    private final JComboBox<BetTypeViewModel> betTypeCombo;
    private GameController gameController;
    private GameEngine gameEngine;

    public ToolbarPanel(GameController gameController, GameEngine gameEngine, GameLogger gameLogger, int padding) {
        this.gameController = gameController;
        this.gameEngine = gameEngine;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(padding, padding / 2, padding, padding / 2)));
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        add(westPanel, BorderLayout.WEST);

        JButton spinButton = new JButton("⚡ Spin");
        spinButton.addActionListener(new SpinListener(gameController));
        westPanel.add(spinButton, BorderLayout.WEST);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        add(eastPanel, BorderLayout.EAST);

        noPlayersLabel = new JLabel("No players available 😥");
        noPlayersLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        noPlayersLabel.setVisible(false);
        eastPanel.add(noPlayersLabel);

        betAmountLabel = new JLabel("Bet");
        betAmountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        eastPanel.add(betAmountLabel);

        betAmount = new JTextField();
        betAmount.setPreferredSize(new Dimension(100, 26));
        eastPanel.add(betAmount);

        playerCombo = new JComboBox<>();

        for (Player player : gameEngine.getAllPlayers()) {
            playerCombo.addItem(new PlayerViewModel(player));
        }
        eastPanel.add(playerCombo, BorderLayout.CENTER);

        betTypeCombo = new JComboBox<>();
        eastPanel.add(betTypeCombo, BorderLayout.CENTER);

        for (BetType betType : BetType.values()) {
            betTypeCombo.addItem(new BetTypeViewModel(betType));
        }

        betButton = new JButton("💸 Place Bet");
        betButton.addActionListener(new BetListener(gameController, gameLogger, playerCombo, betTypeCombo, betAmount));
        eastPanel.add(betButton, BorderLayout.WEST);
    }

    public void update() {
        playerCombo.removeAllItems();

        Collection<Player> players = getBetablePlayers(gameEngine);
        for (Player player : players) {
            playerCombo.addItem(new PlayerViewModel(player));
        }

        if (isEmpty(players)) {
            playerCombo.setVisible(false);
            betAmountLabel.setVisible(false);
            betAmount.setVisible(false);
            betButton.setVisible(false);
            betTypeCombo.setVisible(false);
            noPlayersLabel.setVisible(true);
        } else {
            playerCombo.setVisible(true);
            betAmountLabel.setVisible(true);
            betAmount.setVisible(true);
            betButton.setVisible(true);
            betTypeCombo.setVisible(true);
            noPlayersLabel.setVisible(false);
        }

        revalidate();
        repaint();
    }
}
