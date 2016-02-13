package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.Application;
import be.peerassistedlearningti.model.ApplicationState;

import java.util.Collection;

/**
 * Interface for Application specific DAO operations
 *
 * @see Application
 * @see CRUDDAO
 */
public interface ApplicationDAO extends CRUDDAO<Integer, Application>
{

    /**
     * Gets all the applications with the specified state
     *
     * @param state The state of the applications
     * @return All the applications with the specified state
     */
    Collection<Application> getAll( ApplicationState state );

}
