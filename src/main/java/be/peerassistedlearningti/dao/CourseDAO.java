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

    Course getByCode( String code );
    Course getByName( String name );
    Course getByShortName( String shortName );

}