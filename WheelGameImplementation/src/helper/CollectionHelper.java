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

    /**
     * Check whether the list is empty.
     *
     * @param collection used to check
     * @param <T>        any type
     * @return whether the collection is empty
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection.size() == 0;
    }
}
