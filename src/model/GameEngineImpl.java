package model;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static helper.CollectionHelper.toList;

public class GameEngineImpl implements GameEngine {
    private Collection<Player> players = new ArrayList<>();
    private Collection<GameEngineCallback> gameEngineCallbacks = new ArrayList<>();

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

        List<Slot> wheelSlots = toList(getWheelSlots());

        // Select a random slot in the list
        int index = new Random().nextInt(wheelSlots.size());

        do {
            for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
                gameEngineCallback.nextSlot(wheelSlots.get(index), this);
            }

            // Increment index and loop back
            index++;
            if (index >= wheelSlots.size()) {
                index = 0;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            delay = delay + delayIncrement;
        }
        while (delay <= finalDelay);

        for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
            gameEngineCallback.result(wheelSlots.get(index), this);
        }
    }

    /**
     * Calculate game results on the winning slot.
     *
     * @param winningSlot - the winning slot as passed to GameEngineCallback.result(...)
     */
    @Override
    public void calculateResult(Slot winningSlot) {
        for (Player player : this.players) {
            if (player.getBetType() == null) {
                continue;
            }

            player.getBetType().applyWinLoss(player, winningSlot);
        }
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
     * @param selectedPlayer - to remove from game
     * @return boolean
     */
    @Override
    public boolean removePlayer(Player selectedPlayer) {
        Iterator<Player> iterator = this.players.iterator();

        while (iterator.hasNext()) {
            Player player = iterator.next();

            if (selectedPlayer.getPlayerId().equals(player.getPlayerId())) {
                iterator.remove();

                return true;
            }
        }

        return false;
    }

    /**
     * Add the game engine callback implementation to the game.
     *
     * @param gameEngineCallback <pre> a client specific implementation of GameEngineCallback used to perform display updates etc.
     *                                                     <b>NOTE:</b> you will use a different implementation of the GameEngineCallback
     *                                                           for the console (assignment 1) and GUI (assignment 2) versions</pre>
     */
    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        this.gameEngineCallbacks.add(gameEngineCallback);
    }

    /**
     * Remove the game engine callback.
     *
     * @param selectedGameEngineCallback - instance to be removed if no longer needed
     * @return boolean - true if the callback implementation has been removed
     */
    @Override
    public boolean removeGameEngineCallback(GameEngineCallback selectedGameEngineCallback) {
        Iterator<GameEngineCallback> iterator = this.gameEngineCallbacks.iterator();

        while (iterator.hasNext()) {
            GameEngineCallback gameEngineCallback = iterator.next();

            if (gameEngineCallback.equals(selectedGameEngineCallback)) {
                iterator.remove();

                return true;
            }
        }

        return false;
    }

    /**
     * Get all players in the game.
     *
     * @return Collection
     */
    @Override
    public Collection<Player> getAllPlayers() {
        return this.players;
    }

    /**
     * Place a bet on behalf of the player.
     *
     * @param player  - the Player who is placing the bet
     * @param bet     - the bet in points
     * @param betType - the type of bet (red, black or either zero)
     * @return boolean - true if bet has been placed
     */
    @Override
    public boolean placeBet(Player player, int bet, BetType betType) {
        if (bet > 0 && player.setBet(bet)) {
            player.setBetType(betType);
            return true;
        }

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
