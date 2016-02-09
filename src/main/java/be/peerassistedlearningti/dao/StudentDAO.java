package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.Student;

/**
 * Interface for Student specific DAO operations
 *
 * @see Student
 * @see CRUDDAO
 */
public interface StudentDAO extends CRUDDAO<Integer, Student>
{

    Student getByEmail( String email );

}
