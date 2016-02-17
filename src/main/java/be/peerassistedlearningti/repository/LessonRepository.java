package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Lesson specific database operations
 *
 * @see Lesson
 * @see CrudRepository
 */
@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {}
