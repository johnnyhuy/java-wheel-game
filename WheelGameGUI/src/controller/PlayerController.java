package controller;

import view.PlayerView;

public class PlayerController extends Controller {
    private final PlayerView playerView;

    public PlayerController() {
        this.playerView = new PlayerView();
    }

    public void create() {
        playerView.create();
    }
}
