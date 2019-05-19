package controller;

import model.GameLogger;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.GameView;
import view.SubscriptionView;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.SubmissionPublisher;

import static helper.ColorHelper.getColor;
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
                gameLogger.log("Let the games begin! Remember to gamble responsibly 😀👌", getColor(50, 50, 200));
            }
        });
    }

    public void spin() {
        new Thread() {
            @Override
            public void run() {
                gameLogger.log("Spinning the wheel... No more bets!", Color.RED);
                gameEngine.spin(1, 500, 25);
                publisher.submit(true);
            }
        }.start();
    }

    public void bet(Player player, BetType betType, int bet) {
        gameLogger.log(String.format(
            "%s placed %d on %s",
            player.getPlayerName(),
            bet,
            capitalize(betType.toString())
        ), getColor(132, 115, 30));
        gameEngine.placeBet(player, bet, betType);
        publisher.submit(true);
    }

    public void result(Slot winningSlot) {
        gameLogger.log(String.format(
            "Winning slot is %s %d",
            capitalize(winningSlot.getColor()),
            winningSlot.getNumber()
        ), getColor(0, 200, 0));

        for (Player player : gameEngine.getAllPlayers()) {
            if (player.getBet() == 0) {
                gameLogger.log(String.format(
                    "%s did not bet this round",
                    player.getPlayerName()
                ), getColor(200, 0, 0));

                continue;
            }

            gameLogger.log(String.format(
                "%s bet %d on %s and now has %d points",
                player.getPlayerName(),
                player.getBet(),
                capitalize(winningSlot.getColor()),
                player.getPoints()
            ), getColor(0, 200, 0));
        }

        publisher.submit(true);
    }
}
