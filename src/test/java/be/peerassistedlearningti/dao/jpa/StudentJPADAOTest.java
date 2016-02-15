package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.UserType;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Class used to test StudentJPADAO
 */
public class StudentJPADAOTest extends JPADAOTest
{

    private StudentJPADAO studentJPADAO;

    /**
     * Constructor for StudentJPADAOTest
     */
    public StudentJPADAOTest()
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
        studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );
    }

    @Override
    public void testAdd()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s );

        assertNotNull( s.getId() );
    }

    @Override
    public void testUpdate()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s1 );

        s1.setName( "Cedric" );

        studentJPADAO.update( s1 );

        Student s2 = studentJPADAO.getById( s1.getId() );

        assertEquals( s2.getName(), "Cedric" );
    }

    @Override
    public void testRemove()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s );
        studentJPADAO.remove( s );

        s = studentJPADAO.getById( s.getId() );

        assertNull( s );
    }

    @Override
    public void testGetById()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s1 );

        Student s2 = studentJPADAO.getById( s1.getId() );

        assertNotNull( s2 );
        assertEquals( s1, s2 );
    }

    @Override
    public void testGetAll()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );
        Student s2 = new Student( "Cedric", "paswoord", "cedricopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s1 );
        studentJPADAO.add( s2 );

        Collection<Student> list = studentJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Override
    public void testGetLast()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );
        Student s2 = new Student( "Cedric", "paswoord", "cedricopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s1 );
        studentJPADAO.add( s2 );

        Student s3 = studentJPADAO.getLast();

        assertNotNull( s3 );
        assertEquals( s2, s3 );
    }

    @Test
    public void testGetByEmail() throws Exception
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );

        studentJPADAO.add( s1 );

        Student s2 = studentJPADAO.getByEmail( "davidopdebeeck@hotmail.com" );

        assertNotNull( s2 );
        assertEquals( s1, s2 );
    }

}