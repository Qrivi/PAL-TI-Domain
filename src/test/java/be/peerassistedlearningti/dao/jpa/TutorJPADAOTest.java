package be.peerassistedlearningti.dao.jpa;


import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class TutorJPADAOTest {


    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static TutorJPADAO tutorJPADAO;
    private static Student s1, s2;
    private static Course c1,c2;
    private Set<Course> courses;

    @BeforeClass
    public static void init(){

        entityManagerFactory = Persistence.createEntityManagerFactory( "PAL" );
        entityManager = entityManagerFactory.createEntityManager();

        tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory(entityManagerFactory);
        StudentJPADAO studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory(entityManagerFactory);
        CourseJPADAO courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory(entityManagerFactory);

        //populate db
        s1 = new Student( "Koen", "passwoord", "koen1992@hotmail.com", true );
        s2 = new Student( "Jan", "secret", "jan2016@hotmail.com",true);
        s2 = studentJPADAO.add(s2);
        s1 = studentJPADAO.add(s1);
        assertNotNull(s1.getId());
        assertNotNull(s2.getId());

        c1 = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        c2 = new Course( "MBI81x", ".Communicatie in het Frans Deel 3", "Frans 3" );
        c1 = courseJPADAO.add(c1);
        c2 = courseJPADAO.add(c2);
        assertNotNull(c1.getId());
        assertNotNull(c2.getId());
        Set<Course> courses = new HashSet<Course>();
        courses.add(c1);
    }

    @Test
    public void test1Add()throws Exception{
        Tutor t = new Tutor(s1,courses);
        tutorJPADAO.add(t);
        assertNotNull(t.getId());
        tutorJPADAO.remove(t);
    }
    @Test
    public void test2GetById()
    {
        Tutor t1 = new Tutor(s1,courses);
        tutorJPADAO.add(t1);
        Tutor t2 = tutorJPADAO.getById(t1.getId());
        assertNotNull( t1 );
        assertEquals( t1, t2 );

        tutorJPADAO.remove( t1 );
    }

    @Test
    public void test3Update()
    {
        Tutor t1 = new Tutor(s1,courses);
        tutorJPADAO.add(t1);
        assertNotNull( t1.getId() );
        t1.getCourses().add(c2);
        Tutor t2 = tutorJPADAO.update(t1);
        assertEquals(t1,t2);

        tutorJPADAO.remove(t1);
    }

    @Test
    public void test4Remove()
    {
        Tutor t = new Tutor(s1,courses);
        t = tutorJPADAO.add(t);
        tutorJPADAO.remove(t);

        assertNull(tutorJPADAO.getById(t.getId()));
    }

    @Test
    public void test5GetAll()
    {
        Tutor t1 = new Tutor(s1,courses);
        Tutor t2 = new Tutor(s2,courses);
        t1 = tutorJPADAO.add(t1);
        t2 = tutorJPADAO.add(t2);

        Collection<Tutor> list = tutorJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Test
    public void test6GetLast()
    {
        Tutor t1 = new Tutor(s1,courses);
        Tutor t2 = new Tutor(s2,courses);
        t1 = tutorJPADAO.add(t1);
        t2 = tutorJPADAO.add(t2);


        Tutor t3 = tutorJPADAO.getLast();

        assertNotNull( t3 );
        assertEquals( t2, t3 );
    }

}
