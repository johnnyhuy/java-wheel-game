package model;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.*;

public class GameEngineImpl implements GameEngine {
    private Collection<Player> players = new ArrayList<>();

    @Override
    public void spin(int initialDelay, int finalDelay, int delayIncrement) {
        Collection<Slot> wheelSlots = getWheelSlots();
        int randomIndex = new Random().nextInt(wheelSlots.size());
    }

    @Override
    public void calculateResult(Slot winningSlot) {

    }

    @Override
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public Player getPlayer(String id) {
        for (Player player : this.players) {
            if (player.getPlayerId().equals(id)) {
                return player;
            }
        }

        return null;
    }

    /**
     * Remove player from game.
     *
     * @param player - to remove from game
     * @return boolean
     */
    @Override
    public boolean removePlayer(Player player) {
        Iterator<Player> iterator = this.players.iterator();

        while (iterator.hasNext()) {
            Player iteratorPlayer = iterator.next();

            if (player.getPlayerId().equals(iteratorPlayer.getPlayerId())) {
                iterator.remove();

                return true;
            }
        }

        return false;
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {

    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        return false;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return this.players;
    }

    @Override
    public boolean placeBet(Player player, int bet, BetType betType) {
        return false;
    }

    @Override
    public Collection<Slot> getWheelSlots() {
        final int[] wheelNumbers = new int[] {
            0, 27, 10, 25, 29, 12, 8, 19, 31, 18, 6, 21, 33, 16,
            4, 23, 35, 14, 2, 0, 28, 9, 26, 30, 11, 7, 20, 32, 17,
            5, 22, 34, 15, 3, 24, 36, 13, 1
        };
        List<Slot> wheelSlots = new ArrayList<>();

        for (int position = 0; position < Slot.WHEEL_SIZE; position++) {
            Color selectedColor;

            if (position == 0) {
                selectedColor = Color.GREEN00;
            } else if (position == 19) {
                selectedColor = Color.GREEN0;
            } else if (position % 2 == 0) {
                selectedColor = Color.BLACK;
            } else {
                selectedColor = Color.RED;
            }

            wheelSlots.add(new SlotImpl(position, selectedColor, wheelNumbers[position]));
        }

        return wheelSlots;
    }
}
