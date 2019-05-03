package view;

import controller.GameController;
import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.WheelPanel;
import view.interfaces.GameEngineCallback;

import java.util.HashMap;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private GameController gameController;
    private GameView gameView;
    private WheelPanel wheelPanel;
    private HashMap<Slot, Double> wheelMap;

    public GameEngineCallbackGUI(GameController gameController, GameEngine gameEngine, GameView gameView, WheelPanel wheelPanel) {
        this.gameController = gameController;
        this.gameView = gameView;
        this.wheelPanel = wheelPanel;
        this.wheelMap = new HashMap<>();

        final double angleIncrement = (double) 360 / Slot.WHEEL_SIZE;

        // Offset the angle to align the picture
        double angle = (angleIncrement / 2) - (angleIncrement * (double) (Slot.WHEEL_SIZE / 4));

        for (Slot slot : gameEngine.getWheelSlots()) {
            wheelMap.put(slot, angle);
            angle += angleIncrement;
        }
    }

    @Override
	public void nextSlot(Slot slot, GameEngine engine) {
        wheelPanel.setBallAngle(wheelMap.get(slot));
    }

    @Override
	public void result(Slot winningSlot, GameEngine engine) {
        gameController.result();
	}
}
