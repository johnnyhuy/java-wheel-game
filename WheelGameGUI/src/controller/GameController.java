package controller;

import model.GameLogger;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.GameView;
import view.SubscriptionView;

import javax.swing.*;
import java.util.concurrent.SubmissionPublisher;

import static helper.StringHelper.capitalize;

public class GameController extends Controller {
    private GameEngine gameEngine;
    private SubmissionPublisher<Boolean> publisher;
    private GameLogger gameLogger;
    private PlayerController playerController;

    public GameController(GameEngine gameEngine, SubmissionPublisher<Boolean> publisher, GameLogger gameLogger, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.publisher = publisher;
        this.gameLogger = gameLogger;
        this.playerController = playerController;
    }

    public void start() {
        SubscriptionView view = new GameView(gameEngine, this, playerController, gameLogger);
        publisher.subscribe(view);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.render();
                gameLogger.log("Let the games begin! Remember to gamble responsibly.");
            }
        });
    }

    public void spin() {
        new Thread() {
            @Override
            public void run() {
                gameEngine.spin(1, 500, 25);
                gameLogger.log("Spinning the wheel... No more bets!");
                publisher.submit(true);
            }
        }.start();
    }

    public void bet(Player player, BetType betType, int bet) {
        gameEngine.placeBet(player, bet, betType);

        gameLogger.log(String.format(
            "Player %s has placed a bet of %d on %s",
            player.getPlayerName(),
            bet,
            capitalize(betType.toString())
        ));

        publisher.submit(true);
    }

    public void result(Slot winningSlot, GameEngine engine) {
        gameLogger.log(String.format(
            "Winning slot is %s %d",
            capitalize(winningSlot.getColor()),
            winningSlot.getNumber()
        ));

        publisher.submit(true);
    }
}
