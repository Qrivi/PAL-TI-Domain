package be.peerassistedlearningti.common.dao;


import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * Class used to add JPA functionalities to a dao
 */
public abstract class JPADAO
{

    /**
     * The entity manager entityManagerFactory (used to create the entity managers)
     *
     * @see JPADAO#createManager()
     */
    protected EntityManagerFactory entityManagerFactory;

    /**
     * Constructor for JPADAO
     */
    public JPADAO() { }

    /**
     * Creates an entity manager, if the entity manager entityManagerFactory is null or is closed it is initialised
     *
     * @return a new entity manager
     */
    public EntityManager createManager()
    {
        try
        {
            return entityManagerFactory.createEntityManager();
        } catch ( Exception e )
        {
            throw new NullPointerException( "The entity manager factory is not set" );
        }
    }

    /**
     * Sets the EntityManagerFactory used in the transactions
     *
     * @param entityManagerFactory The EntityManagerFactory used in the transactions
     */
    @PersistenceUnit
    public void setEntityManagerFactory( EntityManagerFactory entityManagerFactory )
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Adds the functionality to execute a query on the database
     *
     * @param query      The query to be executed
     * @param type       The class of the return type
     * @param parameters The parameters of the query
     * @param <S>        The return type
     * @return The single result of the query if no results null is returned
     */
    public <S extends JPAEntity> S executeSingleQuery( String query, Class<S> type, Map<String, Object> parameters )
    {
        EntityManager manager = createManager();
        try
        {
            TypedQuery<S> q = manager.createQuery( query, type )
                    .setMaxResults( 1 );

            if ( parameters != null )
            {
                for ( String parameter : parameters.keySet() )
                    q.setParameter( parameter, parameters.get( parameter ) );
            }

            return q.getSingleResult();
        } catch ( NoResultException ex )
        {
            return null;
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

    /**
     * Adds the functionality to execute a query on the database
     *
     * @param query      The query to be executed
     * @param type       The class of the return type
     * @param parameters The parameters of the query
     * @param <S>        The return type
     * @return The result list of the query if no results an empty list is returned
     */
    public <S extends JPAEntity> Collection<S> executeListQuery( String query, Class<S> type, Map<String, Object> parameters )
    {
        EntityManager manager = createManager();
        try
        {
            TypedQuery<S> q = manager.createQuery( query, type );

            if ( parameters != null )
            {
                for ( String parameter : parameters.keySet() )
                    q.setParameter( parameter, parameters.get( parameter ) );
            }

            return q.getResultList();
        } catch ( NoResultException ex )
        {
            return new LinkedList<S>();
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

}
