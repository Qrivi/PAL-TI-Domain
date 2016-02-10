package be.peerassistedlearningti.service;

import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.common.service.ServiceException;
import be.peerassistedlearningti.dao.CourseDAO;
import be.peerassistedlearningti.dao.StudentDAO;
import be.peerassistedlearningti.dao.TutorDAO;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;

/**
 * Class used to access the backend functionalities
 *
 * @see PALService
 */
public class PALServiceImpl implements PALService
{

    /**
     * Data access objects
     */

    private TutorDAO tutorDAO;
    private CourseDAO courseDAO;
    private StudentDAO studentDAO;

    /**
     * Sets the course dao for the service
     *
     * @param courseDAO The course dao for the service
     */
    public void setCourseDAO( CourseDAO courseDAO )
    {
        this.courseDAO = courseDAO;
    }

    /**
     * Sets the student dao for the service
     *
     * @param studentDAO The student dao for the service
     */
    public void setStudentDAO( StudentDAO studentDAO )
    {
        this.studentDAO = studentDAO;
    }

    /**
     * Sets the tutor dao for the service
     *
     * @param tutorDAO The tutor dao for the service
     */
    public void setTutorDAO( TutorDAO tutorDAO )
    {
        this.tutorDAO = tutorDAO;
    }

    /**
     * Adds a course to the database
     *
     * @param course The course to be added to the database
     */
    public void addCourse( Course course )
    {
        try
        {
            courseDAO.add( course );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified course from the database
     *
     * @param course The course to be removed from the database
     */
    public void removeCourse( Course course )
    {
        try
        {
            courseDAO.remove( course );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the course with the specified id
     *
     * @param id The id of the course
     * @return The course with the specified id
     */
    public Course getCourseById( int id )
    {
        try
        {
            return courseDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Adds a student to the database
     *
     * @param student The student to be added to the database
     */
    public void addStudent( Student student )
    {
        try
        {
            studentDAO.add( student );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified student from the database
     *
     * @param student The student to be removed from the database
     */
    public void removeStudent( Student student )
    {
        try
        {
            studentDAO.add( student );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the student with the specified id
     *
     * @param id The id of the student
     * @return The student with the specified id
     */
    public Student getStudentById( int id )
    {
        try
        {
            return studentDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    public Student getStudentByEmail( String email )
    {
        try
        {
            return studentDAO.getByEmail( email );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }
}
