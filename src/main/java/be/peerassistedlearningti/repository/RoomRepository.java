package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Room specific database operations
 *
 * @see Room
 * @see CrudRepository
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>
{
    /**
     * Gets all the rooms from the specified campus
     *
     * @param campus The campus to get the rooms from
     * @return The rooms from the specified campus
     */
    @Query( "SELECT r FROM Room r WHERE r.campus = :campus" )
    Collection<Room> findByCampus( @Param( "campus" ) Campus campus );
}
