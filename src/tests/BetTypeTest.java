import model.SimplePlayer;
import model.SlotImpl;
import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.Player;
import model.interfaces.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BetTypeTest {
    @Test
    void testApplyWinOrLossOnPlayerWinningRedBet() {
        // Arrange
        final int bet = 100;
        int playerPoints = 1000;
        final Player player = new SimplePlayer("1", "Come In Spinner", playerPoints);
        final Slot winningSlot = new SlotImpl(0, Color.RED, 69);
        player.setBetType(BetType.RED);
        player.setBet(bet);

        // Act
        player.getBetType().applyWinLoss(player, winningSlot);

        // Assert
        assertEquals(playerPoints + bet, player.getPoints());
    }

    @Test
    void testApplyWinOrLossOnPlayerWinningBlackBet() {
        // Arrange
        final int bet = 100;
        int playerPoints = 1000;
        final Player player = new SimplePlayer("1", "Come In Spinner", playerPoints);
        final Slot winningSlot = new SlotImpl(0, Color.BLACK, 69);
        player.setBetType(BetType.BLACK);
        player.setBet(bet);

        // Act
        player.getBetType().applyWinLoss(player, winningSlot);

        // Assert
        assertEquals(playerPoints + bet, player.getPoints());
    }

    @Test
    void testApplyWinOrLossOnPlayerWinningGreen0Bet() {
        // Arrange
        final int bet = 100;
        final int winningMultiplier = (Slot.WHEEL_SIZE / 2) - 1;
        int playerPoints = 1000;
        final Player player = new SimplePlayer("1", "Come In Spinner", playerPoints);
        final Slot winningSlot = new SlotImpl(0, Color.GREEN0, 69);
        player.setBetType(BetType.ZEROS);
        player.setBet(bet);

        // Act
        player.getBetType().applyWinLoss(player, winningSlot);

        // Assert
        assertEquals(playerPoints + (bet * winningMultiplier), player.getPoints());
    }

    @Test
    void testApplyWinOrLossOnPlayerWinningGreen00Bet() {
        // Arrange
        final int bet = 100;
        final int winningMultiplier = (Slot.WHEEL_SIZE / 2) - 1;
        int playerPoints = 1000;
        final Player player = new SimplePlayer("1", "Come In Spinner", playerPoints);
        final Slot winningSlot = new SlotImpl(0, Color.GREEN00, 69);
        player.setBetType(BetType.ZEROS);
        player.setBet(bet);

        // Act
        player.getBetType().applyWinLoss(player, winningSlot);

        // Assert
        assertEquals(playerPoints + (bet * winningMultiplier), player.getPoints());
    }
}
