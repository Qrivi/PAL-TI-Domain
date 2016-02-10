package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.dao.RoomDAO;
import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Class for Student specific DAO operations via JPA
 *
 * @see Room
 * @see CRUDJPADAO
 */
public class RoomJPADAO extends CRUDJPADAO<Integer, Room> implements RoomDAO
{

    /**
     * Default constructor for CRUDJPADAO
     */
    public RoomJPADAO()
    {
        super( Room.class );
    }

    /**
     * Gets all the rooms from the specified campus
     *
     * @param campus The campus to get the rooms from
     * @return The rooms from the specified campus
     */
    public Collection<Room> getFromCampus( Campus campus )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "SELECT r FROM Room r WHERE r.campus = :campus", Room.class )
                    .setParameter( "campus", campus )
                    .getResultList();
        } catch ( NoResultException e )
        {
            return new LinkedList<Room>();
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }
}
