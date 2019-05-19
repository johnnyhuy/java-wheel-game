package view;

import controller.GameController;
import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.panel.WheelPanel;
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
    }

    @Override
	public void nextSlot(Slot slot, GameEngine engine) {
        wheelPanel.setBallAngle(slot);
    }

    @Override
	public void result(Slot winningSlot, GameEngine engine) {
        gameController.result(winningSlot, engine);
	}
}
