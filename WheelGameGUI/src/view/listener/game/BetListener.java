package view.listener.game;

import controller.GameController;
import model.BetTypeViewModel;
import model.GameLogger;
import model.PlayerViewModel;
import model.enumeration.BetType;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static helper.StringHelper.isInteger;

public class BetListener implements ActionListener {
    private GameController gameController;
    private GameLogger gameLogger;
    private JComboBox<PlayerViewModel> playerCombo;
    private JComboBox<BetTypeViewModel> betTypeCombo;
    private JTextField betAmount;

    public BetListener(GameController gameController, GameLogger gameLogger, JComboBox<PlayerViewModel> playerCombo, JComboBox<BetTypeViewModel> betTypeCombo, JTextField betAmount) {
        this.gameController = gameController;
        this.gameLogger = gameLogger;
        this.playerCombo = playerCombo;
        this.betTypeCombo = betTypeCombo;
        this.betAmount = betAmount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = playerCombo.getItemAt(playerCombo.getSelectedIndex()).getPlayer();
        BetType betType = betTypeCombo.getItemAt(betTypeCombo.getSelectedIndex()).getBetType();
        String betAmountText = betAmount.getText();

        if (!isInteger(betAmountText)) {
            gameLogger.log("Bet must be an integer, please try again", Color.RED);
            return;
        }

        gameController.bet(player, betType, Integer.parseInt(betAmountText));
    }
}
