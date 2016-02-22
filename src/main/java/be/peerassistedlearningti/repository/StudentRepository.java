package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Lesson;
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
     * Gets the past lessons for a given student
     *
     * @param email The email of the student
     * @return The list with the past lessons
     */
    @Query("SELECT l FROM lesson_booking lb \n" +
            "  INNER JOIN student s on (lb.student_id = s.id AND s.email = :email)\n" +
            "  INNER JOIN lesson l on (lb.lesson_id = l.id AND l.date < CURRENT_DATE())")
    Collection<Lesson> getPastLessonsForEmail(@Param("email") String email);

    /**
     * Gets the future lessons for a given student
     *
     * @param email The email of the student
     * @return The list with the future lessons
     */
    @Query("SELECT l FROM lesson_booking lb \n" +
            "  INNER JOIN student s on (lb.student_id = s.id AND s.email = :email)\n" +
            "  INNER JOIN lesson l on (lb.lesson_id = l.id AND l.date > CURRENT_DATE())")
    Collection<Lesson> getFutureLessonsForEmail(@Param("email") String email);
}
