package controller;

import model.interfaces.GameEngine;
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
}
