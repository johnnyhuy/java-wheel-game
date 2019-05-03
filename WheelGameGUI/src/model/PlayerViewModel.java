package model;

import model.interfaces.Player;

public class PlayerViewModel {
    private Player player;

    public PlayerViewModel(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String toString() {
        return this.player.getPlayerName();
    }
}
