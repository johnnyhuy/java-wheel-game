package view.component;

import controller.GameController;
import model.BetTypeViewModel;
import model.PlayerViewModel;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.listener.BetListener;
import view.listener.SpinListener;

import javax.swing.*;
import java.awt.*;

public class ToolbarPanel extends JPanel {
    private final JComboBox<PlayerViewModel> playerCombo;
    private GameController gameController;
    private GameEngine gameEngine;
    private int padding;

    public ToolbarPanel(GameController gameController, GameEngine gameEngine, int padding) {
        this.gameController = gameController;
        this.gameEngine = gameEngine;
        this.padding = padding;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(padding, padding / 2, padding, padding / 2)));
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        add(westPanel, BorderLayout.WEST);

        JButton spinButton = new JButton("Spin");
        spinButton.addActionListener(new SpinListener(gameController));
        westPanel.add(spinButton, BorderLayout.WEST);

        // TODO: hide this panel when all players have placed bets
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, padding / 2, 0));
        add(eastPanel, BorderLayout.EAST);

        JLabel betAmountLabel = new JLabel("Bet");
        betAmountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        eastPanel.add(betAmountLabel);

        JTextField betAmount = new JTextField();
        betAmount.setPreferredSize(new Dimension(100, 26));
        eastPanel.add(betAmount);

        playerCombo = new JComboBox<>();

        for (Player player : gameEngine.getAllPlayers()) {
            playerCombo.addItem(new PlayerViewModel(player));
        }
        eastPanel.add(playerCombo, BorderLayout.CENTER);

        JComboBox<BetTypeViewModel> betTypeCombo = new JComboBox<>();
        eastPanel.add(betTypeCombo, BorderLayout.CENTER);

        for (BetType betType : BetType.values()) {
            betTypeCombo.addItem(new BetTypeViewModel(betType));
        }

        JButton betButton = new JButton("Place Bet");
        betButton.addActionListener(new BetListener(gameController, playerCombo, betTypeCombo, betAmount));
        eastPanel.add(betButton, BorderLayout.WEST);
    }
}
