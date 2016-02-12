package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.Course;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Class used to test CourseJPADAO
 */
public class CourseJPADAOTest extends JPADAOTest
{

    private static CourseJPADAO courseJPADAO;

    /**
     * Constructor for CourseJPADAOTest
     */
    public CourseJPADAOTest()
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
        courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );
    }

    @Override
    public void testAdd()
    {
        Course c = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c );

        assertNotNull( c.getId() );
    }

    @Override
    public void testUpdate()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c1 );

        c1.setName( ".NET Programmeren in Visual Studio" );

        courseJPADAO.update( c1 );

        Course c2 = courseJPADAO.getById( c1.getId() );

        assertEquals( c2.getName(), ".NET Programmeren in Visual Studio" );
    }

    @Override
    public void testRemove()
    {
        Course c = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c );

        courseJPADAO.remove( c );

        c = courseJPADAO.getById( c.getId() );

        assertNull( c );
    }

    @Override
    public void testGetById()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getById( c1.getId() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

    @Override
    public void testGetAll()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        Course c2 = new Course( "MBI62a", "3D Computer Graphics", "3D" );

        courseJPADAO.add( c1 );
        courseJPADAO.add( c2 );

        Collection<Course> list = courseJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Override
    public void testGetLast()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        Course c2 = new Course( "MBI62a", "3D Computer Graphics", "3D" );

        courseJPADAO.add( c1 );
        courseJPADAO.add( c2 );

        Course c3 = courseJPADAO.getLast();

        assertNotNull( c3 );
        assertEquals( c2, c3 );
    }

    @Test
    public void testGetByCode()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getByCode( c1.getCode() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

    @Test
    public void testGetByName()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getByName( c1.getName() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

    @Test
    public void testGetByShortName()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getByShortName( c1.getShortName() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

}