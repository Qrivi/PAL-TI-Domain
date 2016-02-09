package be.peerassistedlearningti.common.dao;

import be.peerassistedlearningti.common.model.identifiable.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class used for the basic CRUD operations in a JPA environment
 *
 * @param <T> The type used to identify the object
 * @param <O> The object class used for CRUD operations
 */
public abstract class CRUDJPADAO <T, O extends Identifiable<T>> extends JPADAO implements CRUDDAO<T, O>
{

    /**
     * The type of the class used to find by the identifier
     *
     * @see CRUDJPADAO#getById(Object)
     */
    private final Class<O> typeClass;

    /**
     * Constructor for CRUDJPADAO
     *
     * @param typeClass The type of the class used to find by the identifier
     */
    public CRUDJPADAO( Class<O> typeClass )
    {
        this.typeClass = typeClass;
    }

    /**
     * Adds an object to the entity manager
     *
     * @param obj The object to add to the dao
     * @return The object added to the entity manager
     */
    public O add( O obj )
    {
        EntityManager manager = createManager();
        try
        {
            manager.getTransaction()
                    .begin();
            manager.persist( obj );
            manager.flush();
            manager.getTransaction()
                    .commit();
            return obj;
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

    /**
     * Updates ann object from the entity manager
     *
     * @param obj The object to update from the dao
     * @return The object that was updated
     */
    public O update( O obj )
    {
        EntityManager manager = createManager();
        try
        {
            manager.getTransaction()
                    .begin();
            O o = manager.merge( obj );
            manager.getTransaction()
                    .commit();
            return o;
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

    /**
     * Removes an object from the entity manager
     *
     * @param obj The object to be removed from the dao
     */
    public void remove( O obj )
    {
        EntityManager manager = createManager();
        try
        {
            manager.getTransaction()
                    .begin();
            manager.remove( manager.contains( obj ) ? obj : manager.merge( obj ) );
            manager.getTransaction()
                    .commit();
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

    /**
     * Gets all objects (of type 'Identifiable<T>')
     *
     * @return All objects (of type 'Identifiable<T>')
     */
    public Collection<O> getAll()
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "Select a from " + typeClass.getName() + " a", typeClass )
                    .getResultList();
        } catch ( NoResultException ex )
        {
            return new ArrayList<O>();
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

    /**
     * Gets the last added object (of type 'Identifiable<T>')
     *
     * @return The last added object (of type 'Identifiable<T>')
     */
    public O getLast()
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "Select a from " + typeClass.getName() + " a ORDER BY a.id DESC", typeClass )
                    .setMaxResults( 1 )
                    .getSingleResult();
        } catch ( NoResultException ex )
        {
            return null;
        } catch ( Exception e )
        {
            return null;
        } finally
        {
            manager.close();
        }
    }

    /**
     * Gets an object by its identifier (of type 'T')
     *
     * @param id The identifier of the object
     * @return The object with identifier 'id'
     */
    public O getById( T id )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.find( typeClass, id );
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

}
