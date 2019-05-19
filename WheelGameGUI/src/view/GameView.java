package view;

import controller.GameController;
import controller.PlayerController;
import model.GameLogger;
import model.interfaces.GameEngine;
import view.component.Updatable;
import view.component.frame.GameFrame;
import view.component.panel.SummaryPanel;
import view.component.panel.ToolbarPanel;
import view.component.panel.WheelPanel;
import view.listener.game.GameFrameListener;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameView extends SubscriptionView {
    private ArrayList<Updatable> updatables;
    private GameEngine gameEngine;
    private GameController gameController;
    private PlayerController playerController;
    private GameLogger gameLogger;

    public GameView(GameEngine gameEngine, GameController gameController, PlayerController playerController, GameLogger gameLogger) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.playerController = playerController;
        this.gameLogger = gameLogger;
        this.updatables = new ArrayList<>();
    }

    @Override
    public void render() {
        GameFrame frame = new GameFrame(this, playerController);
        final int padding = 5;

        ToolbarPanel toolbar = new ToolbarPanel(gameController, gameEngine, padding);
        updatables.add(toolbar);
        frame.add(toolbar, BorderLayout.NORTH);

        WheelPanel wheelPanel = new WheelPanel(gameEngine, padding);
        frame.add(wheelPanel);

        SummaryPanel summaryPanel = new SummaryPanel(gameEngine, gameLogger, frame, wheelPanel);
        updatables.add(summaryPanel);
        frame.add(summaryPanel, BorderLayout.EAST);

        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BorderLayout());
        statusBar.setBackground(Color.LIGHT_GRAY);
        frame.add(statusBar, BorderLayout.SOUTH);

        JPanel eastStatusBar = new JPanel();
        eastStatusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.add(eastStatusBar, BorderLayout.WEST);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JLabel dateLabel = new JLabel(dateFormat.format(new Date()));
        eastStatusBar.add(dateLabel);

        frame.addComponentListener(new GameFrameListener(summaryPanel));

        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameController, wheelPanel));
    }

    @Override
    public void onNext(Boolean success) {
        for (Updatable updatable : updatables) {
            updatable.update();
        }

        getSubscription().request(1);
    }
}
