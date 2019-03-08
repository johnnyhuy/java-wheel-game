package model.enumeration;

import model.interfaces.Player;
import model.interfaces.Slot;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 *
 * @author Caspar Ryan
 */
public enum BetType {
    RED {
        @Override
        public void applyWinLoss(Player player, Slot winSlot) {
            if (winSlot.getColor() == Color.RED) {
                int winningPoints = player.getBet();
                int currentPlayerPoints = player.getPoints();

                player.setPoints(currentPlayerPoints + winningPoints);
            }
        }
    },
    BLACK {
        @Override
        public void applyWinLoss(Player player, Slot winSlot) {
            if (winSlot.getColor() == Color.BLACK) {
                int winningPoints = player.getBet();
                int currentPlayerPoints = player.getPoints();

                player.setPoints(currentPlayerPoints + winningPoints);
            }
        }
    },
    ZEROS {
        @Override
        public void applyWinLoss(Player player, Slot winSlot) {
            int slotSize = Slot.WHEEL_SIZE;

            if (winSlot.getColor() == Color.GREEN0 || winSlot.getColor() == Color.GREEN00) {
                int rewardMultiplier = (slotSize / 2) - 1;
                int winningPoints = player.getBet() * rewardMultiplier;
                int currentPlayerPoints = player.getPoints();

                player.setPoints(currentPlayerPoints + winningPoints);
            }
        }
    };

    /**
     * This method is to be overridden for each bet type<br>
     * see assignment specification for betting odds and how win/loss is applied
     *
     * @param player  - the player to apply the win/loss points balance adjustment
     * @param winSlot - the winning slot the ball landed on
     */
    public abstract void applyWinLoss(Player player, Slot winSlot);
}
