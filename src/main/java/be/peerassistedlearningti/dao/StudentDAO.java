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

    /**
     * Gets a Student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    Student getByEmail( String email );

}
