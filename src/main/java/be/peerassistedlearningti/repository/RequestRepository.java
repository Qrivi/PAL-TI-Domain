package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Request;
import be.peerassistedlearningti.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

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

    /**
     * Gets all the requests from the specified student
     *
     * @param student The student to get the requests from
     * @return A collection containing all the requests from the specified student
     */
    @Query( "SELECT r FROM Request r WHERE :student MEMBER OF r.upvotes")
    Collection<Request> findAll( @Param("student") Student student);

    /**
     * Gets all the requests filtered by given set of courses
     *
     * @param courses The set of courses to filter the requests on
     * @return A collection containing all the requests filtered by given courses
     */
    @Query("SELECT r FROM Request r WHERE r.course IN :courses")
    Collection<Request> findAllRequests(@Param("courses") Set<Course> courses);
}
