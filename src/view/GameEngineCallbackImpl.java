package view;

import model.interfaces.GameEngine;
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
    }

    @Override
    public void nextSlot(Slot slot, GameEngine engine) {
        final String format = "Next slot: Position: %d, Color: %s, Number: %d";

        logger.log(Level.FINE, String.format(
            format,
            slot.getPosition(),
            capitalize(slot.getColor()),
            slot.getNumber()
        ));
    }

    @Override
    public void result(Slot result, GameEngine engine) {
        final String format = "RESULT=Position: %d, Color: %s, Number: %d";

        logger.log(
            Level.FINE,
            String.format(
                format,
                result.getPosition(),
                capitalize(result.getColor()),
                result.getNumber()
            )
        );

        engine.calculateResult(result);
    }
}
