package controller;

import model.interfaces.GameEngine;
import view.GameView;

import javax.swing.*;
import java.util.concurrent.SubmissionPublisher;

public class GameController extends Controller {
    private GameEngine gameEngine;
    private final GameView gameView;

    public GameController(GameEngine gameEngine, SubmissionPublisher<Integer> publisher, PlayerController playerController) {
        this.gameEngine = gameEngine;
        this.gameView = new GameView(gameEngine, this, playerController);

        publisher.subscribe(gameView);
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameView.start();
            }
        });
    }
}
