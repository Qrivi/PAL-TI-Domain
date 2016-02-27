package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
