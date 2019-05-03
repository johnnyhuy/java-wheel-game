package view.listener;

import controller.GameController;
import model.enumeration.BetType;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BetListener implements ActionListener {
    private GameController gameController;
    private List<Player> players;
    private BetType[] betTypes;
    private JComboBox<String> playerCombo;
    private JComboBox<String> betTypeCombo;
    private JTextField betAmount;

    public BetListener(GameController gameController, List<Player> players, BetType[] betTypes, JComboBox<String> playerCombo, JComboBox<String> betTypeCombo, JTextField betAmount) {
        this.gameController = gameController;
        this.players = players;
        this.betTypes = betTypes;
        this.playerCombo = playerCombo;
        this.betTypeCombo = betTypeCombo;
        this.betAmount = betAmount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int playerIndex = playerCombo.getSelectedIndex();
        Player player = players.get(playerIndex);
        BetType betType = betTypes[betTypeCombo.getSelectedIndex()];
        int bet = Integer.parseInt(betAmount.getText());

        players.remove(playerIndex);

        gameController.bet(player, betType, bet);
    }
}
