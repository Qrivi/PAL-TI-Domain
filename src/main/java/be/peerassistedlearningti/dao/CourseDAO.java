package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.Course;

/**
 * Interface for Student specific DAO operations
 *
 * @see Course
 * @see CRUDDAO
 */
public interface CourseDAO extends CRUDDAO<Integer, Course>
{

    /**
     * Gets a course with the specified code
     *
     * @param code The code of the course
     * @return The course with the specified code
     */
    Course getByCode( String code );

    /**
     * Gets a course with the specified name
     *
     * @param name The name of the course
     * @return The course with the specified name
     */
    Course getByName( String name );

    /**
     * Gets a course with the specified short name
     *
     * @param shortName The short name of the course
     * @return The course with the specified short name
     */
    Course getByShortName( String shortName );

}