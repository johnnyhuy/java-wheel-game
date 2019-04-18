package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

import static helper.StringHelper.capitalize;

/**
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 *
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
    private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

    public GameEngineCallbackImpl() {
        logger.setLevel(Level.FINE);
        Logger.getGlobal().getParent().getHandlers()[0].setLevel(Level.FINE);
    }

    @Override
    public void nextSlot(Slot slot, GameEngine engine) {
        logger.log(Level.FINE, String.format(
            "Next slot: Position: %d, Color: %s, Number: %d",
            slot.getPosition(),
            capitalize(slot.getColor()),
            slot.getNumber()
        ));
    }

    @Override
    public void result(Slot result, GameEngine engine) {
        logger.log(Level.FINE,
            String.format(
                "RESULT=Position: %d, Color: %s, Number: %d\n",
                result.getPosition(),
                capitalize(result.getColor()),
                result.getNumber()
            )
        );
        logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");

        engine.calculateResult(result);

        StringBuilder results = new StringBuilder();
        for (Player player : engine.getAllPlayers()) {
            results
                .append("\n")
                .append(player.toString());
        }

        logger.log(Level.INFO, results.toString());
    }
}
