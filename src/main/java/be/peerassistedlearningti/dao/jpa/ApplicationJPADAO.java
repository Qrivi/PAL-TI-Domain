package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.dao.ApplicationDAO;
import be.peerassistedlearningti.model.Application;
import be.peerassistedlearningti.model.ApplicationState;
import be.peerassistedlearningti.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Class for Application specific DAO operations via JPA
 *
 * @see Application
 * @see CRUDJPADAO
 */
public class ApplicationJPADAO extends CRUDJPADAO<Integer, Application> implements ApplicationDAO
{

    /**
     * Default constructor for CRUDJPADAO
     */
    public ApplicationJPADAO()
    {
        super( Application.class );
    }

    /**
     * Gets all the applications with the specified state
     *
     * @param state The state of the applications
     * @return All the applications with the specified state
     */
    public Collection<Application> getAll( ApplicationState state )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "SELECT a FROM Application a WHERE a.state = :state", Application.class )
                    .setParameter( "state", state )
                    .getResultList();
        } catch ( NoResultException e )
        {
            return new LinkedList<Application>();
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

}
