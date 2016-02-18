package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Lesson;
import be.peerassistedlearningti.model.Review;
import be.peerassistedlearningti.model.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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
    @Query( "SELECT r FROM Review r WHERE r.lesson.tutor = :tutor" )
    Collection<Review> findByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets reviews filtered by lesson
     *
     * @return A collection containing the reviews of that lesson
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson = :lesson" )
    Collection<Review> findByLesson( @Param( "lesson" ) Lesson lesson );
}