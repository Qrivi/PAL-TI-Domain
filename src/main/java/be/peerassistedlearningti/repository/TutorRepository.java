package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Tutor specific database operations
 *
 * @see Tutor
 * @see CrudRepository
 */
@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer>
{
    /**
     * Gets all the tutors from the specified course
     *
     * @param course The course to get the tutors from
     * @return A collection containing all the tutors from the specified course
     */
    @Query( "SELECT t FROM Tutor t WHERE :course MEMBER OF t.courses" )
    Collection<Tutor> findAll( @Param( "course" ) Course course );

    /**
     * Gets the tutor with the specified student
     *
     * @param student The student of the tutor
     * @return The tutor with the specified student
     */
    @Query( "SELECT t FROM Tutor t WHERE t.student = :student" )
    Tutor findByStudent( @Param( "student" ) Student student );
}
