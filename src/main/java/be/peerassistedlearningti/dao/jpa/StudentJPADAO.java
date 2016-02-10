package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.dao.StudentDAO;
import be.peerassistedlearningti.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Class for Student specific DAO operations via JPA
 *
 * @see Student
 * @see CRUDJPADAO
 */
public class StudentJPADAO extends CRUDJPADAO<Integer, Student> implements StudentDAO
{
    /**
     * Default constructor for StudentJPADAO
     */
    public StudentJPADAO()
    {
        super( Student.class );
    }

    /**
     * Gets a Student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    public Student getByEmail( String email )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "SELECT s FROM Student s WHERE s.email LIKE :studentEmail", Student.class )
                    .setParameter( "studentEmail", email )
                    .getSingleResult();
        } catch ( NoResultException e )
        {
            return null;
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

}
