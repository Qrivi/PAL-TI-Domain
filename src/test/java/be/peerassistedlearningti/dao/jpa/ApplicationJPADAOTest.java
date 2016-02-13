package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.*;
import org.junit.Before;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Class used to test ApplicationJPADAO
 */
public class ApplicationJPADAOTest extends JPADAOTest
{
    private ApplicationJPADAO applicationJPADAO;
    private Application a1, a2;

    /**
     * Constructor for ApplicationJPADAOTest
     */
    public ApplicationJPADAOTest()
    {
        super( "PAL" );
    }

    @Before
    public void before()
    {
        super.before();

        // Set JPADAO

        applicationJPADAO = new ApplicationJPADAO();
        applicationJPADAO.setEntityManagerFactory( factory );
        CourseJPADAO courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );
        StudentJPADAO studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );

        // Make Course object and add to database

        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        c1 = courseJPADAO.add( c1 );
        assertNotNull( c1 );

        Set<Application> applications = new HashSet<Application>();
        applications.add( a1 );

        // Make Student object and add to database

        Student s1 = new Student( "Koen", "paswoord", "koen1992@hotmail.com", true );
        s1 = studentJPADAO.add( s1 );
        assertNotNull( s1.getId() );

        a1 = new Application(s1, c1, "URL");
        a2 = new Application(s1, c1, "URL", true, new Date(), new Date());
    }

    @Override
    public void testAdd()
    {
        applicationJPADAO.add( a1 );
        assertNotNull( a1.getId() );
    }

    @Override
    public void testUpdate()
    {
        applicationJPADAO.add( a1 );

        assertNotNull( a1.getId() );

        a1.setPending( true );
        Application a1Updated = applicationJPADAO.update( a1 );

        assertEquals( a1, a1Updated );
    }

    @Override
    public void testRemove()
    {
        applicationJPADAO.add( a1 );

        assertNotNull( a1.getId() );

        applicationJPADAO.remove( a1 );

        assertNull( applicationJPADAO.getById( a1.getId() ) );
    }

    @Override
    public void testGetById()
    {
        applicationJPADAO.add( a1 );

        assertNotNull( a1.getId() );
        assertNotNull( applicationJPADAO.getById( a1.getId() ) );
    }

    @Override
    public void testGetAll()
    {
        applicationJPADAO.add( a1 );
        applicationJPADAO.add( a2 );

        assertNotNull( a1.getId() );
        assertNotNull( a2.getId() );

        Collection<Application> list = applicationJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Override
    public void testGetLast()
    {
        a1 = applicationJPADAO.add( a1 );
        a2 = applicationJPADAO.add( a2 );

        assertNotNull( a1.getId() );
        assertNotNull( a2.getId() );

        Application last = applicationJPADAO.getLast();
        assertEquals( a2, last );
    }
}
