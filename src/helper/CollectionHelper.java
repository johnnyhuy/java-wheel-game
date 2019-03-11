package helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionHelper {
    /**
     * Very small helper extension method to convert collection to list.
     *
     * @param collection to be converted to list
     * @param <T>        element type
     * @return List
     */
    public static <T> List<T> toList(Collection<T> collection) {
        return new ArrayList<>(collection);
    }
}
