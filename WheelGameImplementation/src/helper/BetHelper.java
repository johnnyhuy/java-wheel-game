package helper;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;

import static helper.ObjectHelper.exists;

/**
 * Since we can't modify interfaces, you've forced my hand to player the helper game >:(
 * <p>
 * Using an adapter would potentially fix that, but it would it smell pretty bad.
 */
public class BetHelper {
    /**
     * Check if a player has placed a bet.
     *
     * @param player used to check a bet place
     * @return whether a bet has been placed
     */
    public static boolean hasBet(Player player) {
        return exists(player.getBetType()) || player.getBet() > 0;
    }

    /**
     * Get a list of "bet'able" players.
     *
     * @param gameEngine used to get the list of players
     * @return collection of players that can bet
     */
    public static Collection<Player> getBetablePlayers(GameEngine gameEngine) {
        ArrayList<Player> list = new ArrayList<>();

        for (Player player : gameEngine.getAllPlayers()) {
            if (hasBet(player)) {
                continue;
            }

            list.add(player);
        }

        return list;
    }
}
