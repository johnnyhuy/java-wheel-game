package controller;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameView;
import view.SubscriptionView;

import javax.swing.*;
import java.util.concurrent.SubmissionPublisher;

public class GameController extends Controller {
    private GameEngine gameEngine;
    private SubmissionPublisher<Integer> publisher;
    private PlayerController playerController;

    public GameController(GameEngine gameEngine, SubmissionPublisher<Integer> publisher, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.publisher = publisher;
        this.playerController = playerController;
    }

    public void start() {
        SubscriptionView view = new GameView(gameEngine, this, playerController);
        publisher.subscribe(view);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.render();
            }
        });
    }

    public void spin() {
        new Thread() {
            @Override
            public void run() {
                gameEngine.spin(1, 500, 25);
            }
        }.start();
    }

    public void bet(Player player, BetType betType, int bet) {
        gameEngine.placeBet(player, bet, betType);
    }
}
