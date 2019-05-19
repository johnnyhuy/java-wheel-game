package view.listener.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static helper.WebHelper.openUrl;

public class HelpMeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        openUrl("https://gamblershelp.com.au/");
    }
}
