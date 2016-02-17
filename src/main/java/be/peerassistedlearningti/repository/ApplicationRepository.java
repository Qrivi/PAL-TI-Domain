package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Application;
import be.peerassistedlearningti.model.ApplicationState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Application specific database operations
 *
 * @see Application
 * @see CrudRepository
 */
@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer>
{
    /**
     * Gets all the applications with the specified state
     *
     * @param state The state of the application
     * @return All the applications with the specified state
     */
    @Query( "SELECT a FROM Application a WHERE a.state = :state" )
    Collection<Application> findAll( ApplicationState state );
}
