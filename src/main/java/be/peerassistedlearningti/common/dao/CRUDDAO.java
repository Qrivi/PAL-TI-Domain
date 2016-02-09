package be.peerassistedlearningti.common.dao;


import be.peerassistedlearningti.common.model.identifiable.Identifiable;

import java.util.Collection;

/**
 * Interface for the basic CRUD operations
 *
 * @param <T> The type used to identify the object
 * @param <O> The object class used for CRUD operations
 */
public interface CRUDDAO <T, O extends Identifiable<T>>
{

    /**
     * Adds an object to the dao
     *
     * @param obj The object to add to the dao
     * @return The object added to the dao (useful when dao adds the id)
     */
    O add( O obj );

    /**
     * Updates an object from the dao
     *
     * @param obj The object to update from the dao
     * @return The object updated from the dao
     */
    O update( O obj );

    /**
     * Removes an object from the dao
     *
     * @param obj The object to be removed from the dao
     */
    void remove( O obj );

    /**
     * Gets all objects (of type 'Identifiable<T>')
     *
     * @return All objects (of type 'Identifiable<T>')
     */
    Collection<O> getAll();

    /**
     * Gets the last added object (of type 'Identifiable<T>')
     *
     * @return The last added object (of type 'Identifiable<T>')
     */
    O getLast();

    /**
     * Gets an object by its identifier (of type 'T')
     *
     * @param id The identifier of the object
     * @return The object with identifier 'id'
     */
    O getById( T id );

}
