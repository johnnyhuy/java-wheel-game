package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.CirclePanel;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private CirclePanel circlePanel;

    public GameEngineCallbackGUI(CirclePanel circlePanel) {
        this.circlePanel = circlePanel;
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
