package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.dao.CourseDAO;
import be.peerassistedlearningti.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Class for Course specific DAO operations via JPA
 *
 * @see Course
 * @see CRUDJPADAO
 */
public class CourseJPADAO extends CRUDJPADAO<Integer, Course> implements CourseDAO
{

    /**
     * Default constructor for CourseJPADAO
     */
    public CourseJPADAO()
    {
        super( Course.class );
    }

    public Course getByCode( String code )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "SELECT c FROM Course c WHERE c.code = :code", Course.class )
                    .setParameter( "code", code )
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

    public Course getByName( String name )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "SELECT c FROM Course c WHERE c.name = :name", Course.class )
                    .setParameter( "name", name )
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

    public Course getByShortName( String shortName )
    {
        EntityManager manager = createManager();
        try
        {
            return manager.createQuery( "SELECT c FROM Course c WHERE c.shortName = :shortName", Course.class )
                    .setParameter( "shortName", shortName )
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
