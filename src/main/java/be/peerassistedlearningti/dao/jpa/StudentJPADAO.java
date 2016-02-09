package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.dao.StudentDAO;
import be.peerassistedlearningti.model.Student;

import javax.persistence.EntityManager;

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
     * Gets a Student by its email
     *
     * @param email The email of the student
     * @return The student with email 'email'
     */
    public Student getByEmail( String email )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createNamedQuery( "SELECT a FROM" + Student.class.getName() + " a WHERE a.email LIKE :studentEmail", Student.class )
                    .setParameter( "studentEmail", email )
                    .setMaxResults( 1 )
                    .getSingleResult();
        } catch ( Exception e )
        {
            throw new DAOException( e );
        } finally
        {
            manager.close();
        }
    }

}
