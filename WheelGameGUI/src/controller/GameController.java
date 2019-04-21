package controller;

import view.GameView;

public class GameController extends Controller {
    private final GameView gameView;

    public GameController(PlayerController playerController) {
        this.gameView = new GameView(this, playerController);
    }

    public void start() {
        gameView.start();
    }
}
