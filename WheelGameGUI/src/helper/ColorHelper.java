package helper;

import java.awt.*;

public class ColorHelper {
    /**
     * Get the color object from RGB values.
     *
     * @param red   color
     * @param green color
     * @param blue  color
     * @return color object
     */
    public static Color getColor(int red, int green, int blue) {
        return Color.decode(String.format("#%02x%02x%02x", red, green, blue));
    }
}
