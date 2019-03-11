package tests;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import org.junit.jupiter.api.Test;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

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

    @Test
    void testAddGameEngineCallback() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();
        Exception unexpectedException = null;

        // Act
        try {
            gameEngine.addGameEngineCallback(gameEngineCallback);
        } catch (Exception exception) {
            unexpectedException = exception;
        }

        // Assert
        assertNull(unexpectedException);
    }

    @Test
    void testRemoveExistingGameEngineCallback() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();
        gameEngine.addGameEngineCallback(gameEngineCallback);

        // Act
        boolean hasRemovedGameEngineCallback = gameEngine.removeGameEngineCallback(gameEngineCallback);

        // Assert
        assertTrue(hasRemovedGameEngineCallback);
    }

    @Test
    void testRemoveGameEngineCallbackWhenDoesNotExist() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();

        // Act
        boolean hasRemovedGameEngineCallback = gameEngine.removeGameEngineCallback(gameEngineCallback);

        // Assert
        assertFalse(hasRemovedGameEngineCallback);
    }

    @Test
    void testPlayerPlaceBetIsNegativeBetAmount() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        Player player = new SimplePlayer("apples", "Test player", 100);
        gameEngine.addPlayer(player);

        // Act
        boolean bet = gameEngine.placeBet(player, -3000, BetType.BLACK);

        // Assert
        assertFalse(bet);
        assertEquals(0, player.getBet());
        assertNull(player.getBetType());
    }

    @Test
    void testPlayerPlaceBetIsZero() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        Player player = new SimplePlayer("apples", "Test player", 100);
        gameEngine.addPlayer(player);

        // Act
        boolean bet = gameEngine.placeBet(player, 0, BetType.BLACK);

        // Assert
        assertFalse(bet);
        assertEquals(0, player.getBet());
        assertNull(player.getBetType());
    }

    @Test
    void testPlayerPlaceBetPlayerInsufficientPoints() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        Player player = new SimplePlayer("apples", "Test player", 100);
        gameEngine.addPlayer(player);

        // Act
        boolean bet = gameEngine.placeBet(player, 9000, BetType.BLACK);

        // Assert
        assertFalse(bet);
        assertEquals(0, player.getBet());
        assertNull(player.getBetType());
    }

    @Test
    void testPlayerPlaceBet() {
        // Arrange
        final GameEngine gameEngine = new GameEngineImpl();
        Player player = new SimplePlayer("apples", "Test player", 100);
        gameEngine.addPlayer(player);

        // Act
        boolean bet = gameEngine.placeBet(player, 100, BetType.BLACK);

        // Assert
        assertTrue(bet);
        assertEquals(100, player.getBet());
        assertNotNull(player.getBetType());
    }

    @Test
    void testMakeBetsAndCalculateResult() {
        // Arrange
        final int initialPoints = 1000;
        final GameEngine gameEngine = new GameEngineImpl();
        GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();
        gameEngine.addGameEngineCallback(gameEngineCallback);

        final Player player = new SimplePlayer("1", "Come In Spinner", initialPoints);
        gameEngine.addPlayer(player);
        gameEngine.placeBet(player, 100, BetType.BLACK);
        gameEngine.placeBet(player, 100, BetType.RED);
        gameEngine.placeBet(player, 100, BetType.ZEROS);

        // Act
        gameEngine.spin(1, 100, 5);

        // Assert
        assertNotEquals(initialPoints, player.getPoints());
        // Gamer wins at least once >:)
        assertTrue(player.getPoints() > (initialPoints - 300));
    }
}
