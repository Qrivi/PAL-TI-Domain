package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Class used to test TutorJPADAOTest
 */
public class TutorJPADAOTest extends JPADAOTest
{

    private Student s1, s2;
    private Course c2;
    private Set<Course> courses;

    private TutorJPADAO tutorJPADAO;

    /**
     * Constructor for TutorJPADAOTest
     */
    public TutorJPADAOTest()
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

        // Set JPADAO

        tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory( factory );

        StudentJPADAO studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );

        CourseJPADAO courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );

        // Make Student and Course objects

        s1 = new Student( "Koen", "passwoord", "koen1992@hotmail.com", true );
        s2 = new Student( "Jan", "secret", "jan2016@hotmail.com", true );

        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        c2 = new Course( "MBI81x", "Communicatie in het Frans Deel 3", "Frans 3" );

        // Add students and courses to the database

        s1 = studentJPADAO.add( s1 );
        s2 = studentJPADAO.add( s2 );

        assertNotNull( s1.getId() );
        assertNotNull( s2.getId() );

        c1 = courseJPADAO.add( c1 );
        c2 = courseJPADAO.add( c2 );

        assertNotNull( c1.getId() );
        assertNotNull( c2.getId() );

        courses = new HashSet<Course>();
        courses.add( c1 );
    }

    @Test
    public void testAdd()
    {
        Tutor t = new Tutor( s1, courses );

        tutorJPADAO.add( t );

        assertNotNull( t.getId() );
    }

    @Test
    public void testUpdate()
    {
        Tutor t1 = new Tutor( s1, courses );

        tutorJPADAO.add( t1 );

        assertNotNull( t1.getId() );

        t1.addCourse(c2);

        Tutor t2 = tutorJPADAO.update( t1 );

        assertEquals( t1, t2 );
    }

    @Test
    public void testRemove()
    {
        Tutor t = new Tutor( s1, courses );

        tutorJPADAO.add( t );

        tutorJPADAO.remove( t );

        assertNull( tutorJPADAO.getById( t.getId() ) );
    }

    @Test
    public void testGetById()
    {
        Tutor t1 = new Tutor( s1, courses );

        tutorJPADAO.add( t1 );

        Tutor t2 = tutorJPADAO.getById( t1.getId() );

        assertNotNull( t1 );
        assertEquals( t1, t2 );
    }

    @Test
    public void testGetAll()
    {
        Tutor t1 = new Tutor( s1, courses );
        Tutor t2 = new Tutor( s2, courses );

        tutorJPADAO.add( t1 );
        tutorJPADAO.add( t2 );

        Collection<Tutor> list = tutorJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Test
    public void testGetLast()
    {
        Tutor t1 = new Tutor( s1, courses );
        Tutor t2 = new Tutor( s2, courses );

        tutorJPADAO.add( t1 );
        tutorJPADAO.add( t2 );

        Tutor t3 = tutorJPADAO.getLast();

        assertNotNull( t3 );
        assertEquals( t2, t3 );
    }

}
