package view.component.frame;

import javax.swing.*;
import java.awt.*;

public class CreatePlayerFrame extends JFrame {
    public CreatePlayerFrame() {
        setSize(new Dimension(360, 280));
        setMinimumSize(new Dimension(360, 280));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Create Player");
    }
}
