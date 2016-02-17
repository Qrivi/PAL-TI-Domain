package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Lesson;
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
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
    /**
     * Gets reviews filtered by tutor
     *
     * @return A collection containing the reviews for that tutor
     */
    @Query( "SELECT l FROM Lesson l WHERE l.lesson.course = :course" )
    Collection<Lesson> findByCourse(@Param( "course" ) Course course );

    @Query( "SELECT l FROM Lesson l WHERE l.lesson.course = :course and l.lesson.date > current_time " )
    Collection<Lesson> findUpcomingByCourse(@Param( "course" ) Course course );
}
