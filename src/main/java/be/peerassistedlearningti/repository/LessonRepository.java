package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Lesson;
import be.peerassistedlearningti.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Lesson specific database operations
 *
 * @see Lesson
 * @see CrudRepository
 */
@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer>
{
    /**
     * Gets lessons from a course
     *
     * @return A collection containing the lessons from that course
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course = :course" )
    Collection<Lesson> findByCourse( @Param( "course" ) Course course );

    /**
     * Gets the upcoming lessons from a course
     *
     * @param course The course to get the upcoming lessons from
     * @return A collection containing the upcoming lessons from that course
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course = :course and l.date > current_timestamp " )
    Collection<Lesson> findUpcomingByCourse( @Param( "course" ) Course course );

    /**
     * Gets the past lessons for a given student
     *
     * @param student The student to get the lessons from
     * @return The list with the past lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date < current_timestamp " )
    Collection<Lesson> findPastByStudent( @Param( "student" ) Student student );

    /**
     * Gets the future lessons for a given student
     *
     * @param student The student to get the lessons from
     * @return The list with the future lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date > current_timestamp " )
    Collection<Lesson> findUpcomingByStudent( @Param( "student" ) Student student );
}
