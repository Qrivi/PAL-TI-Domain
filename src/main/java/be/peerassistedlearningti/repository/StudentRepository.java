package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Student specific database operations
 *
 * @see Student
 * @see CrudRepository
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>
{
    /**
     * Gets a Student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    @Query( "SELECT s FROM Student s WHERE s.email = :email" )
    Student findByEmail( @Param( "email" ) String email );

    /**
     * Gets a Student with the specified profile identifier
     *
     * @param profileIdentifier The profile identifier of the student
     * @return The student with the specified profile identifier
     */
    @Query( "SELECT s FROM Student s WHERE s.profileIdentifier = :profileIdentifier" )
    Student findByProfileIdentifier( @Param( "profileIdentifier" ) String profileIdentifier );

    /**
     * Gets all the subscribers from the specified course
     *
     * @param course The course to get the subscribers from
     * @return A collection containing all the subscribers from the specified course
     */
    @Query( "SELECT s FROM Student s WHERE :course MEMBER OF s.subscriptions" )
    Collection<Student> findSubscribersByCourse( @Param( "course" ) Course course );
}
