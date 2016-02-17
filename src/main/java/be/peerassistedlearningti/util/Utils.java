package be.peerassistedlearningti.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class used for utility purposes
 */
public class Utils
{
    /**
     * Converts an Iterable to a Collection
     *
     * @param iter The Iterable to convert
     * @param <E>  The type of objects that the Iterable contains
     * @return A new collection with the objects of the Iterable
     */
    public static <E> Collection<E> makeCollection( Iterable<E> iter )
    {
        Collection<E> list = new ArrayList<E>();
        for ( E item : iter )
        {
            list.add( item );
        }
        return list;
    }
}
