package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Booking specific database operations
 *
 * @see Booking
 * @see CrudRepository
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {}
