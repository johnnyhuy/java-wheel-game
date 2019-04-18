package helper;

public class StringHelper {
    /**
     * Capitalize string base on an object.
     *
     * @param Object object
     * @return String
     */
    public static String capitalize(Object object) {
        String string = object.toString();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
