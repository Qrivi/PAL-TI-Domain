package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Request specific database operations
 *
 * @see Request
 * @see CrudRepository
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Integer>
{
    /**
     * Gets all the requests from the specified course
     *
     * @param course The course to get the requests from
     * @return A collection containing all the requests from the specified course
     */
    @Query( "SELECT r FROM Request r WHERE :course= r.course" )
    Collection<Request> findAll( @Param( "course" ) Course course );
}
