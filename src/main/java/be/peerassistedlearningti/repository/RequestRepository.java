package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Request specific database operations
 *
 * @see Request
 * @see CrudRepository
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Integer>{
}
