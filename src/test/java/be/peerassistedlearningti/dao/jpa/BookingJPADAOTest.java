package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.*;
import org.junit.Before;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BookingJPADAOTest extends JPADAOTest
{
    private Student s1, s2;
    private Lesson l1;

    private BookingJPADAO bookingJPADAO;

    /**
     * Constructor for BookingJPADAO
     */
    public BookingJPADAOTest()
    {
        super( "PAL" );
    }

    /**
     * Assign the factory to the dao
     */
    @Before
    public void before()
    {
        super.before();
        bookingJPADAO = new BookingJPADAO();
        bookingJPADAO.setEntityManagerFactory( factory );

        StudentJPADAO studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );

        LessonJPADAO lessonJPADAO = new LessonJPADAO();
        lessonJPADAO.setEntityManagerFactory( factory );

        CourseJPADAO courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );

        RoomJPADAO roomJPADAO = new RoomJPADAO();
        roomJPADAO.setEntityManagerFactory( factory );

        TutorJPADAO tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory( factory );

        // Make Student and Lesson objects

        s1 = new Student( "Koen", "passwoord", "koen1992@hotmail.com", UserType.ADMIN );
        s2 = new Student( "Jan", "secret", "jan2016@hotmail.com", UserType.ADMIN );

        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", "TI", 3 );
        Course c2 = new Course( "MBI81x", "Communicatie in het Frans Deel 3", "Frans 3", "TI", 3 );

        Set<Course> courses = new HashSet<Course>();
        courses.add( c1 );
        courses.add( c2 );

        Tutor t = new Tutor( s1, courses );

        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        l1 = new Lesson( new Date(), "Test lesson", "Test description", 60000, c1, 25, t, r1, r2 );
        Lesson l2 = new Lesson( new Date(), "Test lesson", "Test description", 60000, c2, 30, t, r2, r1 );

        // Add students and lessons to the database

        s1 = studentJPADAO.add( s1 );
        s2 = studentJPADAO.add( s2 );

        assertNotNull( s1.getId() );
        assertNotNull( s2.getId() );

        r1 = roomJPADAO.add( r1 );
        r2 = roomJPADAO.add( r2 );

        assertNotNull( r1.getId() );
        assertNotNull( r2.getId() );

        c1 = courseJPADAO.add( c1 );
        c2 = courseJPADAO.add( c2 );

        assertNotNull( c1.getId() );
        assertNotNull( c2.getId() );

        t = tutorJPADAO.add( t );

        assertNotNull( t.getId() );

        l1 = lessonJPADAO.add( l1 );
        l2 = lessonJPADAO.add( l2 );

        assertNotNull( l1.getId() );
        assertNotNull( l2.getId() );
    }

    @Override
    public void testAdd()
    {
        Booking b = new Booking( l1, s1 );

        bookingJPADAO.add( b );

        assertNotNull( b.getId() );
    }

    @Override
    public void testUpdate()
    {
        Booking b1 = new Booking( l1, s1 );

        bookingJPADAO.add( b1 );

        assertNotNull( b1.getId() );

        Booking b2 = bookingJPADAO.update( b1 );

        assertEquals( b1, b2 );
    }

    @Override
    public void testRemove()
    {
        Booking b = new Booking( l1, s1 );

        bookingJPADAO.add( b );
        bookingJPADAO.remove( b );

        assertNull( bookingJPADAO.getById( b.getId() ) );
    }

    @Override
    public void testGetById()
    {
        Booking b1 = new Booking( l1, s1 );

        bookingJPADAO.add( b1 );

        Booking b2 = bookingJPADAO.getById( b1.getId() );

        assertNotNull( b1 );
        assertEquals( b1, b2 );
    }

    @Override
    public void testGetAll()
    {
        Booking b1 = new Booking( l1, s1 );
        Booking b2 = new Booking( l1, s2 );

        bookingJPADAO.add( b1 );
        bookingJPADAO.add( b2 );

        Collection<Booking> list = bookingJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Override
    public void testGetLast()
    {
        Booking b1 = new Booking( l1, s1 );
        Booking b2 = new Booking( l1, s2 );

        bookingJPADAO.add( b1 );
        bookingJPADAO.add( b2 );

        Booking b3 = bookingJPADAO.getLast();

        assertNotNull( b3 );
        assertEquals( b2, b3 );
    }
}
