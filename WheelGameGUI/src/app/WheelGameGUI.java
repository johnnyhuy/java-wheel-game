package app;

import controller.GameController;
import controller.PlayerController;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.util.concurrent.SubmissionPublisher;

public class WheelGameGUI {
    public static void main(String[] args) {
        final GameEngine gameEngine = new GameEngineImpl();
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        final PlayerController playerController = new PlayerController(gameEngine, publisher);
        final GameController gameController = new GameController(gameEngine, publisher, playerController);

        Player[] players = new Player[]{
            new SimplePlayer("1", "Come In Spinner", 1000),
            new SimplePlayer("2", "The Loser", 750),
            new SimplePlayer("3", "The Dabbler", 500)
        };

        for (Player player : players) {
            gameEngine.addPlayer(player);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameController.start();
            }
        });
    }
}
