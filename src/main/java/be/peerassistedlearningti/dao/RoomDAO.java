package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Room;

import java.util.Collection;

/**
 * Interface for Room specific DAO operations
 *
 * @see Room
 * @see CRUDDAO
 */
public interface RoomDAO extends CRUDDAO<Integer, Room>
{

    /**
     * Gets all the rooms from the specified campus
     *
     * @param campus The campus to get the rooms from
     * @return The rooms from the specified campus
     */
    Collection<Room> getFromCampus( Campus campus );

}
