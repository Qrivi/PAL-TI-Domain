package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface for Course specific database operations
 *
 * @see Course
 * @see CrudRepository
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>
{
    /**
     * Gets a course with the specified code
     *
     * @param code The code of the course
     * @return The course with the specified code
     */
    @Query( "SELECT c FROM Course c WHERE c.code = :code" )
    Course findByCode( @Param( "code" ) String code );

    /**
     * Gets a course with the specified name
     *
     * @param name The name of the course
     * @return The course with the specified name
     */
    @Query( "SELECT c FROM Course c WHERE c.name = :name" )
    Course findByName( @Param( "name" ) String name );

    /**
     * Gets a course with the specified short name
     *
     * @param shortName The short name of the course
     * @return The course with the specified short name
     */
    @Query( "SELECT c FROM Course c WHERE c.shortName = :shortName" )
    Course findByShortName( @Param( "shortName" ) String shortName );
}
