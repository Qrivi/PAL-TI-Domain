package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Tutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Tutor specific database operations
 *
 * @see Tutor
 * @see CrudRepository
 */
@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer> {}
