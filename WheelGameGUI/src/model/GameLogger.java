package model;

import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.Collection;

public class GameLogger {
    private Collection<String> logs = new ArrayList<>();
    private Collection<JTextComponent> textComponents = new ArrayList<>();

    public void add(JTextComponent textComponent) {
        textComponents.add(textComponent);
    }

    public void log(String text) {
        logs.add(text);

        for (JTextComponent textComponent : textComponents) {
            textComponent.setText(getText());
        }
    }

    private String getText() {
        StringBuilder output = new StringBuilder();
        for (String text : logs) {
            output.append(text).append("\n");
        }

        return output.toString();
    }
}
