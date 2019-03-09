package tests;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    private Player[] players = new Player[] {
        new SimplePlayer("1", "Come In Spinner", 1000),
        new SimplePlayer("2", "The Loser", 750),
        new SimplePlayer("3", "The Dabbler", 500)
    };

    @Test
    void testAddPlayers() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();

        // Act
        for (Player player : this.players) {
            gameEngine.addPlayer(player);
        }

        // Assert
        assertEquals(3, gameEngine.getAllPlayers().size());
    }

    @Test
    void testGetPlayerAndPlayerExists() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        for (Player player : this.players) {
            gameEngine.addPlayer(player);
        }

        String expectedPlayerId = this.players[0].getPlayerId();

        // Act
        Player selectedPlayer = gameEngine.getPlayer(expectedPlayerId);

        // Assert
        assertEquals(expectedPlayerId, selectedPlayer.getPlayerId());
    }

    @Test
    void testGetPlayerAndPlayerDoesNotExist() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        for (Player player : this.players) {
            gameEngine.addPlayer(player);
        }

        String expectedPlayerId = "pizza";

        // Act
        Player selectedPlayer = gameEngine.getPlayer(expectedPlayerId);

        // Assert
        assertNull(selectedPlayer);
    }

    @Test
    void testRemovePlayerAndPlayerExists() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        for (Player player : this.players) {
            gameEngine.addPlayer(player);
        }

        Player expectedPlayer = this.players[1];

        // Act
        boolean playerRemoved = gameEngine.removePlayer(expectedPlayer);

        // Assert
        assertTrue(playerRemoved);
        assertEquals(2, gameEngine.getAllPlayers().size());
    }

    @Test
    void testRemovePlayerAndPlayerDoesNotExist() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        for (Player player : this.players) {
            gameEngine.addPlayer(player);
        }

        Player player = new SimplePlayer("apples", "Test player", 100);

        // Act
        boolean playerRemoved = gameEngine.removePlayer(player);

        // Assert
        assertFalse(playerRemoved);
        assertEquals(3, gameEngine.getAllPlayers().size());
    }
}
