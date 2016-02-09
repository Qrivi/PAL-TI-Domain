package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.jpa.Student;

/**
 * Interface for Student specific DAO operations
 *
 * @see Student
 * @see CRUDDAO
 */
public interface StudentDAO extends CRUDDAO<Integer, Student> {

}
