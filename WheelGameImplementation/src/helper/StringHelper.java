package helper;

public class StringHelper {
    /**
     * Capitalize string base on an object.
     *
     * @param object to capitalize
     * @return capitalized string
     */
    public static String capitalize(Object object) {
        String string = object.toString();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    /**
     * Check whether a string is an integer.
     *
     * @param string to check
     * @return whether the string is an integer
     */
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
