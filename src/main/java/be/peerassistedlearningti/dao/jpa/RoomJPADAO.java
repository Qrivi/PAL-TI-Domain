package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.RoomDAO;
import be.peerassistedlearningti.model.Room;

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

}
