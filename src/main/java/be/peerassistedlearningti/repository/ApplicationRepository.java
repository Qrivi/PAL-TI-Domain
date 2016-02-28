package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Application;
import be.peerassistedlearningti.model.ApplicationState;
import be.peerassistedlearningti.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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
    Collection<Application> findAll( @Param( "state" ) ApplicationState state );

    /**
     * Gets all the applications with the specified student and state
     *
     * @param student The student of the application
     * @param state   The state of the application
     * @return All the applications with the specified state
     */
    @Query( "SELECT a FROM Application a WHERE a.student = :student AND a.state = :state" )
    Collection<Application> findAll( @Param( "student" ) Student student, @Param( "state" ) ApplicationState state );

    /**
     * Gets the last applications with the specified student
     *
     * @param student The student of the application
     * @return The last applications with the specified student
     */
    @Query( "SELECT a FROM Application a WHERE a.student = :student" )
    List<Application> findLastByStudent( @Param( "student" ) Student student, Pageable pageable );
}
