package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface for Tutor specific database operations
 *
 * @see Tutor
 * @see CrudRepository
 */
@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer> {
    /**
     * Gets tutor with the given student
     *
     * @return the tutor who is that student
     */
    @Query( "SELECT t FROM Tutor t WHERE t.student = :student" )
    Tutor findByStudent(@Param("student") Student student);
}
