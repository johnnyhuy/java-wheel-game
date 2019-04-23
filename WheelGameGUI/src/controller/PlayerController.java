package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SubscriptionView;
import view.player.CreatePlayerView;
import view.player.DeletePlayerView;
import view.player.ListPlayerView;

import javax.swing.*;
import java.util.concurrent.SubmissionPublisher;

public class PlayerController extends Controller {
    private SubmissionPublisher<Integer> publisher;
    private GameEngine gameEngine;

    public PlayerController(GameEngine gameEngine, SubmissionPublisher<Integer> publisher) {
        this.gameEngine = gameEngine;
        this.publisher = publisher;
    }

    public void list() {
        SubscriptionView view = new ListPlayerView(gameEngine, this);
        publisher.subscribe(view);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.render();
            }
        });
    }

    public void create() {
        SubscriptionView view = new CreatePlayerView(gameEngine, this);
        publisher.subscribe(view);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.render();
            }
        });
    }

    public void remove() {
        SubscriptionView view = new DeletePlayerView(gameEngine, this);
        publisher.subscribe(view);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.render();
            }
        });
    }

    public void store(Player player) {
        gameEngine.addPlayer(player);
        publisher.submit(1);
    }

    public void destroy(Player player) {
        gameEngine.removePlayer(player);
        publisher.submit(1);
    }
}
