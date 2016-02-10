package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.Student;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class StudentJPADAOTest
{

    private static StudentJPADAO studentJPADAO;

    @BeforeClass
    public static void beforeClass()
    {
        // Get the entity manager factory from persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "PAL" );
        // Assign the factory to the dao
        studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );
    }

    @Test
    public void test1Add()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );

        s = studentJPADAO.add( s );

        assertNotNull( s.getId() );

        studentJPADAO.remove( s );
    }

    @Test
    public void test2GetById()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );

        s1 = studentJPADAO.add( s1 );

        Student s2 = studentJPADAO.getById( s1.getId() );

        assertNotNull( s2 );
        assertEquals( s1, s2 );

        studentJPADAO.remove( s1 );
    }

    @Test
    public void test3Update()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );

        s1 = studentJPADAO.add( s1 );

        s1.setName( "Cedric" );

        s1 = studentJPADAO.update( s1 );

        Student s2 = studentJPADAO.getById( s1.getId() );

        assertEquals( s2.getName(), "Cedric" );

        studentJPADAO.remove( s1 );
    }

    @Test
    public void test4Remove()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );

        s = studentJPADAO.add( s );

        studentJPADAO.remove( s );

        s = studentJPADAO.getById( s.getId() );

        assertNull( s );
    }

    @Test
    public void test5GetAll()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );
        Student s2 = new Student( "Cedric", "paswoord", "cedricopdebeeck@hotmail.com", true );

        studentJPADAO.add( s1 );
        studentJPADAO.add( s2 );

        Collection<Student> list = studentJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );

        studentJPADAO.remove( s1 );
        studentJPADAO.remove( s2 );
    }

    @Test
    public void test6GetLast()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );
        Student s2 = new Student( "Cedric", "paswoord", "cedricopdebeeck@hotmail.com", true );

        studentJPADAO.add( s1 );
        studentJPADAO.add( s2 );

        Student s3 = studentJPADAO.getLast();

        assertNotNull( s3 );
        assertEquals( s2, s3 );

        studentJPADAO.remove( s1 );
        studentJPADAO.remove( s2 );
    }

    @Test
    public void test7GetByEmail() throws Exception
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", true );

        s1 = studentJPADAO.add( s1 );

        Student s2 = studentJPADAO.getByEmail( "davidopdebeeck@hotmail.com" );

        assertNotNull( s2 );
        assertEquals( s1, s2 );

        studentJPADAO.remove( s1 );
    }

}