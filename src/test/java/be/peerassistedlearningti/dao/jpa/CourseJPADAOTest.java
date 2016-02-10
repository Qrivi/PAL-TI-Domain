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

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class CourseJPADAOTest
{

    private static CourseJPADAO courseJPADAO;

    @BeforeClass
    public static void beforeClass()
    {
        // Get the entity manager factory from persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "PAL" );
        // Assign the factory to the dao
        courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );
    }

    @Test
    public void test1Add()
    {
        Course c = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c = courseJPADAO.add( c );

        assertNotNull( c.getId() );

        courseJPADAO.remove( c );
    }

    @Test
    public void test2GetById()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c1 = courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getById( c1.getId() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );

        courseJPADAO.remove( c1 );
    }

    @Test
    public void test3Update()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c1 = courseJPADAO.add( c1 );

        c1.setName( ".NET Programmeren in Visual Studio" );

        c1 = courseJPADAO.update( c1 );

        Course c2 = courseJPADAO.getById( c1.getId() );

        assertEquals( c2.getName(), ".NET Programmeren in Visual Studio" );

        courseJPADAO.remove( c1 );
    }

    @Test
    public void test4Remove()
    {
        Course c = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c = courseJPADAO.add( c );

        courseJPADAO.remove( c );

        c = courseJPADAO.getById( c.getId() );

        assertNull( c );
    }

    @Test
    public void test5GetAll()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        Course c2 = new Course( "MBI62a", "3D Computer Graphics", "3D" );

        courseJPADAO.add( c1 );
        courseJPADAO.add( c2 );

        Collection<Course> list = courseJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );

        courseJPADAO.remove( c1 );
        courseJPADAO.remove( c2 );
    }

    @Test
    public void test6GetLast()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        Course c2 = new Course( "MBI62a", "3D Computer Graphics", "3D" );

        courseJPADAO.add( c1 );
        courseJPADAO.add( c2 );

        Course c3 = courseJPADAO.getLast();

        assertNotNull( c3 );
        assertEquals( c2, c3 );

        courseJPADAO.remove( c1 );
        courseJPADAO.remove( c2 );
    }

    @Test
    public void test7GetByCode()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c1 = courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getByCode( c1.getCode() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );

        courseJPADAO.remove( c1 );
    }

    @Test
    public void test8GetByName()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c1 = courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getByName( c1.getName() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );

        courseJPADAO.remove( c1 );
    }

    @Test
    public void test9GetByShortName()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        c1 = courseJPADAO.add( c1 );

        Course c2 = courseJPADAO.getByShortName( c1.getShortName() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );

        courseJPADAO.remove( c1 );
    }

}