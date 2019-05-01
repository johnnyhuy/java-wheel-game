package view.listener;

import view.component.SummaryPanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GameFrameListener implements ComponentListener {
    private SummaryPanel summaryPanel;

    public GameFrameListener(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        summaryPanel.setSize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
