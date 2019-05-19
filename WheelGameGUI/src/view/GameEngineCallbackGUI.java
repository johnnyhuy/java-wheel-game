package view;

import controller.GameController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.component.panel.WheelPanel;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private GameController gameController;
    private WheelPanel wheelPanel;

    public GameEngineCallbackGUI(GameController gameController, WheelPanel wheelPanel) {
        this.gameController = gameController;
        this.wheelPanel = wheelPanel;
    }

    @Override
	public void nextSlot(Slot slot, GameEngine engine) {
        wheelPanel.setBallAngle(slot);
    }

    @Override
	public void result(Slot winningSlot, GameEngine engine) {
        gameController.result(winningSlot);

        for (Player player : engine.getAllPlayers()) {
            player.resetBet();
        }
	}
}
