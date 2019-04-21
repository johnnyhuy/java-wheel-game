package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.PlayerView;

import java.util.concurrent.SubmissionPublisher;

public class PlayerController extends Controller {
    private final PlayerView playerView;
    private SubmissionPublisher<Integer> publisher;
    private GameEngine gameEngine;

    public PlayerController(GameEngine gameEngine, SubmissionPublisher<Integer> publisher) {
        this.gameEngine = gameEngine;
        this.playerView = new PlayerView(gameEngine, this);
        this.publisher = publisher;
    }

    public void create() {
        playerView.create();
    }

    public void store(Player player) {
        gameEngine.addPlayer(player);
        publisher.submit(1);
    }
}
