package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Lesson;
import be.peerassistedlearningti.model.Review;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Interface for Review specific database operations
 *
 * @see Review
 * @see CrudRepository
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>
{
    /**
     * Gets reviews filtered by tutor
     *
     * @return A collection containing the reviews for that tutor
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson.tutor = :tutor ORDER BY r.date DESC" )
    Collection<Review> findByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets reviews filtered by tutor from the offset with the limit as size
     *
     * @param tutor    to be filtered on
     * @param pageable The page to select from
     * @return A collection containing the reviews for that tutor from the offset with the limit as size
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson.tutor = :tutor ORDER BY r.date DESC" )
    List<Review> findByTutor( @Param( "tutor" ) Tutor tutor, Pageable pageable );

    /**
     * Gets reviews filtered by lesson
     *
     * @return A collection containing the reviews of that lesson
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson = :lesson ORDER BY r.date DESC" )
    Collection<Review> findByLesson( @Param( "lesson" ) Lesson lesson );

    /**
     * Gets reviews filtered by student
     *
     * @return A collection containing the reviews of that student
     */
    @Query( "SELECT r FROM Review r WHERE r.student = :student AND r.anonymous = false ORDER BY r.date DESC" )
    Collection<Review> findByStudent( @Param( "student" ) Student student );

    /**
     * Gets the reviews made by the given student from the offset with the limit as size
     *
     * @param student  The given student
     * @param pageable The page to select from
     * @return A collection containing the reviews of that student from the offset with the limit as size
     */
    @Query( "SELECT r FROM Review r WHERE r.student = :student AND r.anonymous = false ORDER BY r.date DESC" )
    List<Review> findByStudent( @Param( "student" ) Student student, Pageable pageable );

    /**
     * Gets reviews filtered by student and lesson
     *
     * @return A collection containing the reviews of that student
     */
    @Query( "SELECT r FROM Review r WHERE r.student = :student AND r.lesson = :lesson" )
    Review findByStudentAndLesson( @Param( "student" ) Student student, @Param( "lesson" ) Lesson lesson );
}
