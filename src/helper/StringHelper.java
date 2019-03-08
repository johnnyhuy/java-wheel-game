package helper;

public class StringHelper {
    public static String capitalize(Object object) {
        String string = object.toString();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
