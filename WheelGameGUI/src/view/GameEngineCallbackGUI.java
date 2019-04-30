package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.component.WheelPanel;
import view.interfaces.GameEngineCallback;

import java.util.HashMap;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private WheelPanel wheelPanel;
    private final double angle;
    private int startingAngle;
    private HashMap<Slot, Double> wheelMap;

    public GameEngineCallbackGUI(GameEngine gameEngine, WheelPanel wheelPanel) {
        this.wheelPanel = wheelPanel;
        this.angle = (double) 360 / Slot.WHEEL_SIZE;
        this.wheelMap = new HashMap<>();

        double incrementAngle = angle / 2;

        for (Slot slot : gameEngine.getWheelSlots()) {
            wheelMap.put(slot, incrementAngle);
            incrementAngle += angle;
        }

        startingAngle = 0;
    }

    @Override
	public void nextSlot(Slot slot, GameEngine engine) {
        wheelPanel.setBallAngle(wheelMap.get(slot));
    }

    @Override
	public void result(Slot winningSlot, GameEngine engine) {
		// TODO Auto-generated method stub
	}
}
