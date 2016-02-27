package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import be.peerassistedlearningti.model.UserType;
import be.peerassistedlearningti.util.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class TutorRepositoryTest implements RepositoryTest
{

    private Student s1, s2;
    private Course c2;
    private Set<Course> courses;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void before()
    {
        s1 = new Student( "Koen", "passwoord", "koen1992@hotmail.com", "koen", UserType.ADMIN );
        s2 = new Student( "Jan", "secret", "jan2016@hotmail.com", "jan", UserType.ADMIN );

        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", "TI", 3 );
        c2 = new Course( "MBI81x", "Communicatie in het Frans Deel 3", "Frans 3", "TI", 3 );

        s1 = studentRepository.save( s1 );
        s2 = studentRepository.save( s2 );

        assertNotNull( s1.getId() );
        assertNotNull( s2.getId() );

        c1 = courseRepository.save( c1 );
        c2 = courseRepository.save( c2 );

        assertNotNull( c1.getId() );
        assertNotNull( c2.getId() );

        courses = new HashSet<Course>();
        courses.add( c1 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Tutor t = new Tutor( s1, courses );

        tutorRepository.save( t );

        assertNotNull( t.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Tutor t1 = new Tutor( s1, courses );

        tutorRepository.save( t1 );

        t1.addCourse( c2 );

        tutorRepository.save( t1 );

        Tutor t2 = tutorRepository.findOne( t1.getId() );

        assertEquals( t1, t2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Tutor t = new Tutor( s1, courses );

        tutorRepository.save( t );
        tutorRepository.delete( t );

        assertNull( tutorRepository.findOne( t.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Tutor t1 = new Tutor( s1, courses );

        tutorRepository.save( t1 );

        Tutor t2 = tutorRepository.findOne( t1.getId() );

        assertNotNull( t1 );
        assertEquals( t1, t2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Tutor t1 = new Tutor( s1, courses );
        Tutor t2 = new Tutor( s2, courses );

        tutorRepository.save( t1 );
        tutorRepository.save( t2 );

        Collection<Tutor> list = Utils.makeCollection( tutorRepository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}
