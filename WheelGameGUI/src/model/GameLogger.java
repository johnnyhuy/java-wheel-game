package model;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class GameLogger {
    private JTextPane textPane;

    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }

    /**
     * Log something to the text pane.
     *
     * @param text string
     */
    public void log(String text) {
        print(text, Color.BLACK);
    }

    /**
     * Log something to the text pane with a specific color.
     *
     * @param text  string
     * @param color color to set the text
     */
    public void log(String text, Color color) {
        print(text, color);
    }

    /**
     * Print text in the text pane.
     *
     * @param text to print
     * @param color on the text
     */
    private void print(String text, Color color) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attribute = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attribute = styleContext.addAttribute(attribute, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);
        attribute = styleContext.addAttribute(attribute, StyleConstants.FontFamily, "Lucida Console");

        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(attribute, false);
        textPane.replaceSelection(text + "\n");
    }
}
