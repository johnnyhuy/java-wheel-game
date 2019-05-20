package app;

import controller.GameController;
import controller.PlayerController;
import model.GameEngineImpl;
import model.GameLogger;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;

import javax.swing.*;
import java.util.UUID;
import java.util.concurrent.SubmissionPublisher;

public class WheelGameGUI {
    public static void main(String[] args) {
        final GameEngine gameEngine = setupGame();
        final GameLogger gameLogger = new GameLogger();

        SubmissionPublisher<Boolean> publisher = new SubmissionPublisher<>();
        final PlayerController playerController = new PlayerController(gameEngine, publisher);
        final GameController gameController = new GameController(gameEngine, publisher, gameLogger, playerController);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameController.start();
            }
        });
    }

    /**
     * Setup the game here.
     *
     * @return game engine
     */
    private static GameEngine setupGame() {
        final GameEngine gameEngine = new GameEngineImpl();
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

        Player[] players = new Player[]{
            new SimplePlayer(UUID.randomUUID().toString(), "Come In Spinner", 1000),
            new SimplePlayer(UUID.randomUUID().toString(), "The Loser", 750),
            new SimplePlayer(UUID.randomUUID().toString(), "The Dabbler", 500)
        };

        for (Player player : players) {
            gameEngine.addPlayer(player);
        }

        return gameEngine;
    }
}
