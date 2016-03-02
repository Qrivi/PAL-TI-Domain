package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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
    @Query( "SELECT l FROM Lesson l WHERE l.course = :course ORDER BY l.date DESC" )
    Collection<Lesson> findByCourse( @Param( "course" ) Course course );

    /**
     * Gets lessons from a tutor
     *
     * @return A collection containing the lessons from that tutor
     */
    @Query( "SELECT l FROM Lesson l WHERE l.tutor = :tutor ORDER BY l.date DESC" )
    Collection<Lesson> findByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets lessons from a curriculum
     *
     * @return A collection containing the lessons from that curriculum
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course.curriculum = :curriculum ORDER BY l.date DESC" )
    Collection<Lesson> findUpcomingByCurriculum( @Param( "curriculum" ) Curriculum curriculum );

    /**
     * Gets the upcoming lessons from a course
     *
     * @param course The course to get the upcoming lessons from
     * @return A collection containing the upcoming lessons from that course
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course = :course and l.date > current_timestamp ORDER BY l.date DESC" )
    Collection<Lesson> findUpcomingByCourse( @Param( "course" ) Course course );

    /**
     * Gets the past lessons for a given student
     *
     * @param student The student to get the lessons from
     * @return The list with the past lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date < current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findPastByStudent( @Param( "student" ) Student student );

    /**
     * Gets the past bookings for the student from the offset with the limit as size
     *
     * @param student  The student to get the bookings from
     * @param pageable The page to select from
     * @return The past bookings of the student from the offset with size limit
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date < current_timestamp ORDER BY l.date ASC" )
    List<Lesson> findPastByStudent( @Param( "student" ) Student student, Pageable pageable );

    /**
     * Gets the future lessons for a given student
     *
     * @param student The student to get the lessons from
     * @return The list with the future lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date > current_timestamp ORDER BY l.date DESC" )
    Collection<Lesson> findUpcomingByStudent( @Param( "student" ) Student student );

    /**
     * Gets the past lessons for a given tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return The list with the past lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :tutor = l.tutor AND l.date < current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findPastByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets past lessons from the specified tutor from the offset with the limit as size
     *
     * @param tutor    The tutor to get the lessons from
     * @param pageable The page to select from
     * @return the lessons in the past of that tutor from the offset with the limit as size
     */
    @Query( "SELECT l FROM Lesson l WHERE :tutor = l.tutor AND l.date < current_timestamp ORDER BY l.date ASC" )
    List<Lesson> findPastByTutor( @Param( "tutor" ) Tutor tutor, Pageable pageable );

    /**
     * Gets the future lessons for a given tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return The list with the future lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :tutor = l.tutor AND l.date > current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findUpcomingByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets all the upcoming lessons
     *
     * @return A collection containing the upcoming lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE l.date > current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findUpcoming();

    /**
     * Checks if the specified lesson has the specified student
     *
     * @return The lesson if it has the students else null
     */
    @Query( "SELECT l FROM Lesson l WHERE l = :lesson AND :student MEMBER OF l.bookings" )
    Lesson hasBooking( @Param( "student" ) Student student, @Param( "lesson" ) Lesson lesson );
}
