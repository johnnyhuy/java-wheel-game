package view.listener.game;

import controller.GameController;
import model.BetTypeViewModel;
import model.PlayerViewModel;
import model.enumeration.BetType;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetListener implements ActionListener {
    private GameController gameController;
    private JComboBox<PlayerViewModel> playerCombo;
    private JComboBox<BetTypeViewModel> betTypeCombo;
    private JTextField betAmount;

    public BetListener(GameController gameController, JComboBox<PlayerViewModel> playerCombo, JComboBox<BetTypeViewModel> betTypeCombo, JTextField betAmount) {
        this.gameController = gameController;
        this.playerCombo = playerCombo;
        this.betTypeCombo = betTypeCombo;
        this.betAmount = betAmount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = playerCombo.getItemAt(playerCombo.getSelectedIndex()).getPlayer();
        BetType betType = betTypeCombo.getItemAt(betTypeCombo.getSelectedIndex()).getBetType();
        int bet = Integer.parseInt(betAmount.getText());
        playerCombo.removeItemAt(playerCombo.getSelectedIndex());

        gameController.bet(player, betType, bet);
    }
}
