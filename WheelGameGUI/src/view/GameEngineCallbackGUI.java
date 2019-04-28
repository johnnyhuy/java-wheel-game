package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.BallPanel;
import view.component.WheelPanel;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private final BallPanel ballPanel;
    private WheelPanel wheelPanel;

    public GameEngineCallbackGUI(WheelPanel wheelPanel) {
        this.wheelPanel = wheelPanel;
        this.ballPanel = wheelPanel.getBallPanel();
        int angle = Math.round(360 / Slot.WHEEL_SIZE);
    }

    @Override
	public void nextSlot(Slot slot, GameEngine engine) {
        wheelPanel.setAngle(0);
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		// TODO Auto-generated method stub
	}
}
