package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.*;
import org.junit.Before;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Class used to test LessonJPADAO
 */
public class LessonJPADAOTest extends JPADAOTest
{


    private LessonJPADAO lessonJPADAO;
    private Lesson l1, l2;

    /**
     * Constructor for LessonJPADAOTest
     */
    public LessonJPADAOTest()
    {
        super( "PAL" );
    }

    @Before
    public void before()
    {
        super.before();

        // Set JPADAO

        lessonJPADAO = new LessonJPADAO();
        lessonJPADAO.setEntityManagerFactory( factory );
        CourseJPADAO courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );
        StudentJPADAO studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );
        TutorJPADAO tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory( factory );
        RoomJPADAO roomJPADAO = new RoomJPADAO();
        roomJPADAO.setEntityManagerFactory( factory );

        // Make Course object and add to database

        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        c1 = courseJPADAO.add( c1 );
        assertNotNull( c1 );

        Set<Course> courses = new HashSet<Course>();
        courses.add( c1 );

        // Make Student object and add to database

        Student s1 = new Student( "Koen", "paswoord", "koen1992@hotmail.com", true );
        s1 = studentJPADAO.add( s1 );
        assertNotNull( s1.getId() );

        // Make Tutor object and add to database

        Tutor t1 = new Tutor( s1, courses );
        tutorJPADAO.add( t1 );
        assertNotNull( t1.getId() );

        // Make Room object and add to database

        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );
        r1 = roomJPADAO.add( r1 );
        r2 = roomJPADAO.add( r2 );
        assertNotNull( r1.getId() );
        assertNotNull( r2.getId() );

        // Lesson creation

        l1 = new Lesson( new Date(), 120L, c1, 25, t1, r1, r2 );
        l2 = new Lesson( new Date(), 15L, c1, 5, t1, r1, r2 );
    }

    @Override
    public void testAdd()
    {
        lessonJPADAO.add( l1 );
        assertNotNull( l1.getId() );
    }

    @Override
    public void testUpdate()
    {
        lessonJPADAO.add( l1 );

        assertNotNull( l1.getId() );

        l1.setDuration( 90L );
        Lesson l1Updated = lessonJPADAO.update( l1 );

        assertEquals( l1, l1Updated );
    }

    @Override
    public void testRemove()
    {
        lessonJPADAO.add( l1 );

        assertNotNull( l1.getId() );

        lessonJPADAO.remove( l1 );

        assertNull( lessonJPADAO.getById( l1.getId() ) );
    }

    @Override
    public void testGetById()
    {
        lessonJPADAO.add( l1 );

        assertNotNull( l1.getId() );
        assertNotNull( lessonJPADAO.getById( l1.getId() ) );
    }

    @Override
    public void testGetAll()
    {
        lessonJPADAO.add( l1 );
        lessonJPADAO.add( l2 );

        assertNotNull( l1.getId() );
        assertNotNull( l2.getId() );

        Collection<Lesson> list = lessonJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Override
    public void testGetLast()
    {
        l1 = lessonJPADAO.add( l1 );
        l2 = lessonJPADAO.add( l2 );

        assertNotNull( l1.getId() );
        assertNotNull( l2.getId() );

        Lesson last = lessonJPADAO.getLast();
        assertEquals( l2, last );
    }
}
