package be.peerassistedlearningti.service;

import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.common.service.ServiceException;
import be.peerassistedlearningti.dao.CourseDAO;
import be.peerassistedlearningti.dao.RoomDAO;
import be.peerassistedlearningti.dao.StudentDAO;
import be.peerassistedlearningti.dao.TutorDAO;
import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Room;
import be.peerassistedlearningti.model.Student;

import java.util.Collection;

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
    private RoomDAO roomDAO;

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
     * Sets the room dao for the service
     *
     * @param roomDAO The room dao for the service
     */
    public void setRoomDAO( RoomDAO roomDAO )
    {
        this.roomDAO = roomDAO;
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

    /**
     * Adds a room to the database
     *
     * @param room The room to be added to the database
     */
    public void addRoom( Room room )
    {
        try
        {
            roomDAO.add( room );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified room from the database
     *
     * @param room The room to be removed from the database
     */
    public void removeStudent( Room room )
    {
        try
        {
            roomDAO.remove( room );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the room with the specified id
     *
     * @param id The id of the room
     * @return The room with the specified id
     */
    public Room getRoomById( int id )
    {
        try
        {
            return roomDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the rooms with the specified campus
     *
     * @param campus The campus of the room
     * @return The rooms with the specified campus
     */
    public Collection<Room> getRoomsFromCampus( Campus campus )
    {
        try
        {
            return roomDAO.getFromCampus( campus );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }
}
