package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.ApplicationDAO;
import be.peerassistedlearningti.model.Application;

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

}
