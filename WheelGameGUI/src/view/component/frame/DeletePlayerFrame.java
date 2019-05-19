package view.component.frame;

import javax.swing.*;
import java.awt.*;

public class DeletePlayerFrame extends JFrame {
    public DeletePlayerFrame() {
        setSize(new Dimension(360, 200));
        setMinimumSize(new Dimension(360, 200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Delete Player");
    }
}
