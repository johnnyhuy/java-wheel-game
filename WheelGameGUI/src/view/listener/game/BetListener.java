package view.listener.game;

import controller.GameController;
import model.BetTypeViewModel;
import model.GameLogger;
import model.PlayerViewModel;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.component.panel.ToolbarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static helper.BetHelper.getBetablePlayers;
import static helper.CollectionHelper.isEmpty;
import static helper.StringHelper.isInteger;

public class BetListener implements ActionListener {
    private GameController gameController;
    private GameEngine gameEngine;
    private GameLogger gameLogger;
    private ToolbarPanel toolbarPanel;
    private JComboBox<PlayerViewModel> playerCombo;
    private JComboBox<BetTypeViewModel> betTypeCombo;
    private JTextField betAmountField;

    public BetListener(GameController gameController, GameEngine gameEngine, GameLogger gameLogger, ToolbarPanel toolbarPanel) {
        this.gameController = gameController;
        this.gameEngine = gameEngine;
        this.gameLogger = gameLogger;
        this.toolbarPanel = toolbarPanel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Player player = toolbarPanel.getPlayerFromCombo();
        BetType betType = toolbarPanel.getBetTypeFromCombo();
        String betAmountText = toolbarPanel.getBetAmountFromField();

        if (!isInteger(betAmountText)) {
            gameLogger.log("Bet must be an integer, please try again", Color.RED);
            return;
        }

        int betAmount = Integer.parseInt(betAmountText);
        if (betAmount <= 0) {
            gameLogger.log(String.format("Player %s must bet amounts greater than 0", player.getPlayerName()), Color.RED);
            return;
        } else if (betAmount > player.getPoints()) {
            gameLogger.log(String.format("Player %s has insufficient points", player.getPlayerName()), Color.RED);
            return;
        }

        gameController.bet(player, betType, betAmount);

        if (isEmpty(getBetablePlayers(gameEngine))) {
            toolbarPanel.setEnabled(false);
            gameController.spin();
        }
    }
}
