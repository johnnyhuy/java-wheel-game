package tests;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {
    @Test
    void testAddPlayersToGameEngine() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        Player[] players = new Player[] {
            new SimplePlayer("1", "Come In Spinner", 1000),
            new SimplePlayer("2", "The Loser", 750),
            new SimplePlayer("3", "The Dabbler", 500)
        };

        // Act
        for (Player player : players) {
            gameEngine.addPlayer(player);
        }

        // Assert
        assertEquals(3, gameEngine.getAllPlayers().size());
    }
}
