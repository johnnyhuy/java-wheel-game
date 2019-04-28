package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.BallPanel;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private BallPanel ballPanel;

    public GameEngineCallbackGUI(BallPanel ballPanel) {
        this.ballPanel = ballPanel;
        int angle = Math.round(360 / Slot.WHEEL_SIZE);
    }

    @Override
	public void nextSlot(Slot slot, GameEngine engine) {
		// TODO Auto-generated method stub
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		// TODO Auto-generated method stub
	}
}
