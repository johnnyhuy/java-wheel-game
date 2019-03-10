package model;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameEngineImpl implements GameEngine {
    private Collection<Player> players = new ArrayList<>();
    private GameEngineCallback gameEngineCallback;

    /**
     * Spin da wheel if you know what I'm sayin'
     *
     * @param initialDelay
     *            the starting delay in ms between updates
     *            (based on how fast the ball is rolling in the slots)
     * @param finalDelay
     *            the final delay in ms between updates when the ball stops rolling
     * @param delayIncrement
     *            how much the ball slows down (i.e. delay gets longer) after each slot
     */
    @Override
    public void spin(int initialDelay, int finalDelay, int delayIncrement) {
        int delay = initialDelay;

        // TODO: make this a toList() extension method
        List<Slot> wheelSlots = new ArrayList<>(getWheelSlots());

        // Select a random slot in the list
        int index = new Random().nextInt(wheelSlots.size());
        Slot selectedSlot;

        do {
            // Get the next slot
            selectedSlot = wheelSlots.get(index);
            this.gameEngineCallback.nextSlot(selectedSlot, this);

            // Increment index and loop back
            index++;
            if (index >= wheelSlots.size()) {
                index = 0;
            }

            delay = delay + delayIncrement;

            try {
                TimeUnit.MILLISECONDS.sleep(delayIncrement);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (delay <= finalDelay);

        selectedSlot = wheelSlots.get(index);

        this.gameEngineCallback.result(selectedSlot, this);
    }

    @Override
    public void calculateResult(Slot winningSlot) {

    }

    /**
     * Add player to game.
     *
     * @param player - to add to game
     */
    @Override
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Get players by ID.
     *
     * @param id - id of player to retrieve (null if not found)
     * @return Player
     */
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
        this.gameEngineCallback = gameEngineCallback;
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        if (this.gameEngineCallback != null) {
            this.gameEngineCallback = null;
            return true;
        }

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

    /**
     * Get the slots in the wheel.
     *
     * @return Collection
     */
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
