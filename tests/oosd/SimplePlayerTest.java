package oosd;

import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplePlayerTest {
    @Test
    void testGetPlayerId() {
        // Arrange
        final String expectedPlayerId = "S999999";
        final Player player = new SimplePlayer(expectedPlayerId, "Dude", 1000);

        // Act
        String playerName = player.getPlayerId();

        // Assert
        assertEquals(expectedPlayerId, playerName);
    }

    @Test
    void testGetPlayerName() {
        // Arrange
        final String expectedPlayerName = "Come In Spinner";
        final Player player = new SimplePlayer("1", expectedPlayerName, 1000);

        // Act
        String playerName = player.getPlayerName();

        // Assert
        assertEquals(expectedPlayerName, playerName);
    }

    @Test
    void testSetPlayerName() {
        // Arrange
        final String expectedPlayerName = "Come In Spinner";
        final Player player = new SimplePlayer("1", "Dude 101", 1000);

        // Act
        player.setPlayerName(expectedPlayerName);
        String playerName = player.getPlayerName();

        // Assert
        assertEquals(expectedPlayerName, playerName);
    }

    @Test
    void testGetPlayerPoints() {
        // Arrange
        final int expectedPlayerPoints = 1000;
        final Player player = new SimplePlayer("1", "Dode", expectedPlayerPoints);

        // Act
        int playerPoints = player.getPoints();

        // Assert
        assertEquals(expectedPlayerPoints, playerPoints);
    }

    @Test
    void testSetPlayerPoints() {
        // Arrange
        final int expectedPlayerPoints = 1000;
        final Player player = new SimplePlayer("1", "Dude 101", 1000);

        // Act
        player.setPoints(expectedPlayerPoints);
        int playerName = player.getPoints();

        // Assert
        assertEquals(expectedPlayerPoints, playerName);
    }

    @Test
    void testSetAndGetPlayerBetType() {
        // Arrange
        final BetType expectedBetType = BetType.BLACK;
        final Player player = new SimplePlayer("1", "Dode", 1000);

        // Act
        player.setBetType(expectedBetType);
        BetType playerBetType = player.getBetType();

        // Assert
        assertEquals(expectedBetType, playerBetType);
    }

    @Test
    void testPlayerToString() {
        // Arrange
        final String expectedPlayerString = "Player: id=1, name=Dode, bet=100, betType=RED, points=1100";
        final Player player = new SimplePlayer("1", "Dode", 1100);
        player.setBetType(BetType.RED);
        player.setBet(100);

        // Act
        String playerString = player.toString();

        // Assert
        assertEquals(expectedPlayerString, playerString);
    }

    @Test
    void testSetAndGetPlayerBet() {
        // Arrange
        final int expectedPlayerBet = 1000;
        final Player player = new SimplePlayer("1", "Dode", 1000);

        // Act
        boolean bet = player.setBet(expectedPlayerBet);
        int playerBet = player.getBet();

        // Assert
        assertTrue(bet);
        assertEquals(expectedPlayerBet, playerBet);
    }

    @Test
    void testPlayerSetBetInsufficientPoints() {
        // Arrange
        final Player player = new SimplePlayer("1", "Dode", 100);

        // Act
        boolean bet = player.setBet(2000);

        // Assert
        assertFalse(bet);
    }
}
