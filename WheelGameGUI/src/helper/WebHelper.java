package helper;

import java.awt.*;
import java.net.URI;

public class WebHelper {
    /**
     * Open a link on a web browser.
     * Reference: author FThompson https://stackoverflow.com/a/10967469/7506439
     *
     * @param url to open
     * @return whether its opened
     */
    public static boolean openUrl(String url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;

        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(url));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
